/**
 * 
 */
package com.protocol.safemon;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.common.methods;
import com.core.mina.MinaSys;
import com.protocol.database.PutStorageDao;
import com.protocol.safemon.postmsg.ImageData;
import com.protocol.safemon.postmsg.PostService;
import com.protocol.safemon.postmsg.RainfallData;
import com.protocol.safemon.postmsg.Value1;
import com.protocol.safemon.postmsg.WaterlevelData;
import com.protocol.safemon.sys.CqswMethods;
import com.protocol.safemon.sys.FindPar;
import com.protocol.safemon.sys.ImageProcess;
import com.protocol.safemon.sys.ParamsInit;
import com.protocol.safemon.sys.LED;
import com.protocol.safemon.sys.LED_Realtime;
import com.protocol.safemon.sys.SafeMonMapper;
import com.protocol.safemon.sys.Siteinfo;
import com.protocol.safemon.sys.SystemPar;

/**
 * @author xizhonghuai
 * @date 2018��8��29��
 * @readme
 */
@Component("SwProcess")
public class SwProcess {

	private Logger logger = Logger.getLogger(SwProcess.class);

	@Autowired
	private SafeMonMapper safeMonMapper;

	@Autowired
	private PutStorageDao putStorageDao;

	/**
	 * @date 2018��8��29��
	 * @readme
	 * @param session
	 * @param msg
	 * @throws IOException
	 */

	@SuppressWarnings("deprecation")
	public void Controlmessage(IoSession session, String msg) throws IOException {

		String regId = (String) session.getAttribute("regId", null);
		int datType = getDataType(msg);

		String dataField = getDataField(msg);
		@SuppressWarnings("unused")
		String dataInfo = dataField;

		String dtuId = getRegId(msg);

		if ((regId == null) || (!dtuId.equals(regId))) {

			IoSession oldSession = MinaSys.regIdToSession(session.getService().getManagedSessions(), dtuId);
			if (oldSession != null) {
				oldSession.close();
			}
			session.setAttribute("regId", dtuId);
		 
			session.write(CqswMethods.calibrationTime(dtuId));

			// ����ز�����session

			FindPar par_JS = new FindPar();
			List<LED> ledlist_JS = new ArrayList<LED>();
			List<Siteinfo> Siteinfo = new ArrayList<Siteinfo>();

			par_JS.setBianhao(dtuId);
			par_JS.setCount(0);
			par_JS.setPage(0);

			try {

				ledlist_JS = safeMonMapper.getLED(par_JS); // ��ȡLED���豸��Ϣ��

				Siteinfo = safeMonMapper.getSiteinfo(par_JS); // ��ȡ��ˮվ�豸��Ϣ

			} catch (Exception e) {
				// TODO: handle exception
				logger.info("��ȡ��ˮվ�豸" + dtuId + "�����Ϣʧ��");
			}
			if (ledlist_JS.size() > 0) {
				session.setAttribute("LEDList", ledlist_JS);
			}

			if (Siteinfo.size() > 0) {
				session.setAttribute("Site", Siteinfo.get(0));
			}

			logger.info("�豸" + dtuId + session.getRemoteAddress() + "-----------------------------------ע��");
		}

		// DTU������
		if (datType == 0x01) {
			logger.info(dtuId + "��������������");
		
			return;
		}

		// ���ݽ���
		if (dataField.substring(0, 4).equals("AFAF") || dataField.substring(0, 4).equals("CSCS")) {
			CqswDataModel cqswDataModel = getAFAF(dataField);
			if (cqswDataModel != null) {
				cqswDataModel.setRegId(dtuId);
				logger.info(cqswDataModel.toString());

				Siteinfo site = (Siteinfo) session.getAttribute("Site", null);
				if (site != null) {
					float sw = cqswDataModel.getWaterlevel();
					sw = sw - site.getRevise();
					if (sw > 0) {
						cqswDataModel.setWaterlevel(sw);
					} else {
						cqswDataModel.setWaterlevel(0);
					}

					// ����������LED��
					@SuppressWarnings("unchecked")
					List<LED> LEDList = (List<LED>) session.getAttribute("LEDList", null);

					if (LEDList != null) {

						SystemPar systemPar = ParamsInit.SysPar;

						// if (systemPar == null) {
						//
						// logger.info("ϵͳ��������ʧ�ܣ�");
						//// return;
						// }

						sw = cqswDataModel.getWaterlevel();
						 
						int int_sw = (int) sw;

						for (int k = 0; k < LEDList.size(); k++) {
							LED led = LEDList.get(k);

							IoSession LEDSession = MinaSys.regIdToSession(session.getService().getManagedSessions(),
									led.getLED_STCD());

							String calltext = CqswMethods.getCallmsg(sw, site.getFenqu().intValue());

							if (LEDSession != null) {

								LEDSession.setAttribute("calltext", calltext);

							}

							if (led.getEA().intValue() == 1) { // ����ģʽ

								if (sw > systemPar.getLEDTh_on()) {
									try {

										MinaSys.SendMsg(LEDSession,
												methods.encodeGBK("(e_" + int_sw + "_" + calltext + ")"));

										if (calltext.equals("����ͨ��")) {
											MinaSys.SendMsg(LEDSession, methods.encodeGBK("(g_1_0)"));// �ر������
										} else {
											MinaSys.SendMsg(LEDSession, methods.encodeGBK("(g_1_1)"));// ���������
										}

									} catch (UnsupportedEncodingException e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
								} else if (sw < systemPar.getLEDTh_off()) {
									MinaSys.SendMsg(LEDSession, methods.encodeGBK("(g_0_0)"));// �ر���ʾ���������

								} else {

									// ��ȡLED��һ��״̬��Ϣ
									LED_Realtime led_realtime = (LED_Realtime) LEDSession.getAttribute("led_realtime",
											null);
									if (led_realtime != null) {
										String Screen_state = led_realtime.getScreen_state();
										if (Screen_state.equals("��")) {

											try {

												MinaSys.SendMsg(LEDSession,
														methods.encodeGBK("(e_" + int_sw + "_" + calltext + ")"));

												if (calltext.equals("����ͨ��")) {
													MinaSys.SendMsg(LEDSession, methods.encodeGBK("(g_1_0)"));// �ر������
												} else {
													MinaSys.SendMsg(LEDSession, methods.encodeGBK("(g_1_1)"));// ���������
												}

											} catch (UnsupportedEncodingException e) {
												// TODO
												// Auto-generated
												// catch
												// block
												e.printStackTrace();
											}

										}

									}

								}

							} else if (led.getEA().intValue() == 2) { // ����ģʽ

								MinaSys.SendMsg(LEDSession, methods.encodeGBK("(g_0_0)"));// �ر���ʾ���������

							} else if (led.getEA().intValue() == 3) { // ����ģʽ

								try {

									MinaSys.SendMsg(LEDSession,
											methods.encodeGBK("(e_" + int_sw + "_" + calltext + ")"));
									if (calltext.equals("����ͨ��")) {
										MinaSys.SendMsg(LEDSession, methods.encodeGBK("(g_1_0)"));// �ر������
									} else {
										MinaSys.SendMsg(LEDSession, methods.encodeGBK("(g_1_1)"));// ���������
									}
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
							}

						}

					}

					// ������⡢
					cqswDataModel.setId(1);// ��ˮվ
					putStorageDao.updateCqswRealtimeData(cqswDataModel);

					// ����ת�����ͻ���
					String debugMsg = "";

					debugMsg = "dtuId�� " + cqswDataModel.getRegId() + "\r\n" + "ˮλ��" + cqswDataModel.getWaterlevel()
							+ "\r\n" + "������" + cqswDataModel.getRainfall() + "\r\n" + "��ѹ��"
							+ cqswDataModel.getVoltage2() + "\r\n";

					debugHandler.sendMessage(regId + "*" + debugMsg);

					// �ύˮλ��������

					Value1 value = new Value1();

					WaterlevelData water = new WaterlevelData();
					value.setUnit("cm");
					value.setValue(cqswDataModel.getWaterlevel());
					water.setDeviceId(dtuId);

					SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					water.setDataTime(dt.format(cqswDataModel.getTm()));

					water.setValue(value);

					new PostService(CqswMethods.POSTURL + "api/submit/waterlevel", JSONObject.toJSONString(water))
							.start();

					RainfallData rain = new RainfallData();
					value.setUnit("mm");
					value.setValue(cqswDataModel.getRainfall());
					rain.setDeviceId(dtuId);
					rain.setDataTime(dt.format(cqswDataModel.getTm()));
					rain.setValue(value);

					new PostService(CqswMethods.POSTURL + "api/submit/rainfall", JSONObject.toJSONString(rain)).start();

				}

			}
			return;
		}

		// ���ݽ���
		if (dataField.substring(0, 4).equals("ABAB")) {

			String par = getABAB(dataField);

			// ����ת�����ͻ���

			debugHandler.sendMessage(regId + "*" + par);
			return;
		}

		// ��Ƭ����

		if ((dataField.charAt(0) == 0x68) && (dataField.charAt(dataField.length() - 1) == 0x16)) {

			String imageName = (String) session.getAttribute("imageName", null);

			int cameraId = dataField.charAt(1);
			int packIndex = dataField.charAt(3) * 256 + dataField.charAt(4);
			dataField = dataField.substring(13); // ���ڿ�ʼ

			String cameraTime = methods.toBCDString(dataField.substring(0, 5));

			dataField = dataField.substring(5); // ���ݿ�ʼ

			String imgData = dataField.substring(0, dataField.length() - 2);// ͼƬ��Ч����

			String imageTempName = CqswMethods.IMAGE_TEMP + dtuId + "_" + cameraId + "_" + cameraTime + ".jpg";

			if ((imageName == null) || (!imageName.equals(imageTempName))) {

				if ((imgData.charAt(0) == 0xff) && (imgData.charAt(1) == 0xd8)) {
					methods.newFile(imageTempName);
					session.setAttribute("imageName", imageTempName);
					logger.info(dtuId + "����������ʼ����ͼƬ����");
				}
			}

			if (methods.fileExists(imageTempName)) {

				byte[] imageByte = new byte[imgData.length()];
				for (int i = 0; i < imgData.length(); i++) {
					imageByte[i] = (byte) imgData.charAt(i);
				}
				FileOutputStream fos = new FileOutputStream(new File(imageTempName), true);
				fos.write(imageByte, 0, imageByte.length);
				fos.flush();
				fos.close();
				logger.info(dtuId + "���������յ�������" + packIndex + "��");

				if ((packIndex == 1) || (imageByte.length < 1024)) {

					SimpleDateFormat dt1 = new SimpleDateFormat("yyyyMMdd");
					SimpleDateFormat dt2 = new SimpleDateFormat("HHmmss");
					SimpleDateFormat dt3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = new Date();

					String path = CqswMethods.IMAGE + dtuId + "/" + dt1.format(date) + "/" + cameraId + "_"
							+ dt2.format(date) + ".jpg";

					ImageProcess img = new ImageProcess(imageTempName);

					Siteinfo site = (Siteinfo) session.getAttribute("Site", null);
					String str = dtuId + "_" + cameraId + "_" + dt3.format(date);
					if (site != null) {
						str = site.getSite_Name() + "_" + dtuId + "_" + cameraId + "_" + dt3.format(date);
					}

					img.drawString(20, 20, str, Color.red, new Font("����", Font.PLAIN, 15));

					img.save(path);

					session.removeAttribute("imageName");
					logger.info(imageTempName + " ͼƬ�ϴ��ɹ���");

					// �ύͼƬ����

					ImageData imgdata = new ImageData();
					imgdata.setDeviceId(dtuId);
					imgdata.setDataTime(dt3.format(date));
					imgdata.setValue(CqswMethods.IMAGESERVERURL + dtuId + "/" + dt1.format(date) + "/" + cameraId + "_"
							+ dt2.format(date) + ".jpg");

					new PostService(CqswMethods.POSTURL + "api/submit/image", JSONObject.toJSONString(imgdata)).start();

				}

			}

		}

	}

	/**
	 * @date 2018��8��29��
	 * @readme ��ȡ���ݰ�����
	 * @param msg
	 * @return
	 */
	private int getDataType(String msg) {

		return msg.charAt(1);
	}

	/**
	 * @date 2018��8��29��
	 * @readme ��ȡ��������Ϣ
	 * @param msg
	 * @return
	 */
	private String getDataField(String msg) {

		// ��ʼ�ַ�_1 ������_1 ������_2 ����_N �����ַ�_1

		return msg.substring(15, msg.length() - 1);
	}

	/**
	 * @date 2018��8��29��
	 * @readme ��ȡ�豸id
	 * @param msg
	 * @return �豸id
	 */
	private String getRegId(String msg) {

		String str = msg.substring(4, msg.length() - 1);

		return str.substring(0, 11);
	}

	/**
	 * @date 2018��8��29��
	 * @readme ��ȡ���Ʊ�����ʵʱ����
	 * @param msg
	 * @return
	 */
	private CqswDataModel getAFAF(String dataField) {

		CqswDataModel cqswDataModel = null;

		try {

			cqswDataModel = new CqswDataModel();
			String msg_temp = dataField.substring(26);

			float dat = 0;

			dat = Float.parseFloat(msg_temp.substring(0, 4)) / 100;
			cqswDataModel.setVoltage1(dat);
			msg_temp = msg_temp.substring(4);

			dat = Float.parseFloat(msg_temp.substring(0, 4)) / 100;
			cqswDataModel.setVoltage2(dat);
			msg_temp = msg_temp.substring(4);

			dat = Float.parseFloat(msg_temp.substring(0, 4));
			cqswDataModel.setWaterlevel(dat);
			msg_temp = msg_temp.substring(4);

			dat = Float.parseFloat(msg_temp.substring(0, 5));
			cqswDataModel.setRainfall(dat);

			cqswDataModel.setTm(new Date());

			return cqswDataModel;

		} catch (Exception e) {
			// TODO: handle exception

			return null;
		}
	}

	/**
	 * @date 2018��9��3��
	 * @readme ABAB
	 * @param dataField
	 * @return
	 */
	private String getABAB(String dataField) {

		/*
		 * try {
		 * 
		 * String par = "";
		 * 
		 * String msg_temp = dataField.substring(4);
		 * 
		 * par = msg_temp.substring(0, 14); msg_temp = msg_temp.substring(14);
		 * 
		 * par = par + "_" + msg_temp.substring(0, 8); msg_temp =
		 * msg_temp.substring(8);
		 * 
		 * par = par + "_" + msg_temp.substring(0, 3); msg_temp =
		 * msg_temp.substring(3);
		 * 
		 * par = par + "_" + msg_temp.substring(0, 6); msg_temp =
		 * msg_temp.substring(6);
		 * 
		 * par = par + "_" + msg_temp.substring(0, 3); msg_temp =
		 * msg_temp.substring(3);
		 * 
		 * par = par + "_" + msg_temp.substring(0, 4); msg_temp =
		 * msg_temp.substring(4);
		 * 
		 * par = par + "_" + msg_temp.substring(0, 4); msg_temp =
		 * msg_temp.substring(4);
		 * 
		 * par = par + "_" + msg_temp.substring(0, 3); msg_temp =
		 * msg_temp.substring(3); par = par + "_" + msg_temp.substring(0, 3);
		 * msg_temp = msg_temp.substring(3);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * return par;
		 * 
		 * } catch (Exception e) { // TODO: handle exception
		 * 
		 * return dataField; }
		 */

		return dataField;
	}

}
