/**
 * 
 */
package com.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author XIZHONGHUAI
 *
 */

public class methods {

	/**
	 * ʮ�������ַ���
	 * 
	 * @param s
	 * @return
	 */
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			if (s4.length() == 1)
				s4 = "0" + s4;
			s4 = s4 + " ";
			str = str + s4;
		}
		return str;
	}

	/**
	 * ���÷�����ƻ�ȡ�࣬������ʵ��
	 * 
	 * @param className
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Object getObjNewInstance(String className)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {

		Class<?> class1 = null;
		Object object = null;
		class1 = Class.forName(className);

		object = class1.newInstance();

		return object;
	}

	/**
	 * @date 2018��7��10��
	 * @readme ��ȡ��������ע����Ϣ
	 * @param className
	 * @return HandlerClassInfo
	 * @throws ClassNotFoundException
	 */
	public static HandlerClassInfo getHandlerClassInfo(String className) throws ClassNotFoundException {

		Class<?> classE = null;
		classE = Class.forName(className);
		if (classE.isAnnotationPresent(ClassMsgAnnotation.class)) {
			HandlerClassInfo classInfo = new HandlerClassInfo();
			ClassMsgAnnotation cma = classE.getAnnotation(ClassMsgAnnotation.class);

			classInfo.setMark(cma.mark());
			classInfo.setVersion(cma.version());
			classInfo.setPutStorageMethod(cma.putStorageMethod());
			classInfo.setDataModelClassName(cma.dataModelClassName());

			return classInfo;
		}

		return null;

	}

	/**
	 * @date 2018��7��11��
	 * @readme ��ȡmodel���Ա������Ϣ
	 * @param className
	 * @return List<String>
	 * @throws ClassNotFoundException
	 */
	public static List<String> getDataModelClassFields(String className) throws ClassNotFoundException {

		List<String> fieldList = new ArrayList<String>();

		if (className.length() <= 0)
			return fieldList;

		Class<?> classE = null;
		classE = Class.forName(className);

		Field[] fields = classE.getDeclaredFields();

		@SuppressWarnings("unused")
		Field field = null;

		for (Field f : fields) {

			if (f.isAnnotationPresent(DataModelFieldAnnotation.class)) {

				DataModelFieldAnnotation annotation = f.getAnnotation(DataModelFieldAnnotation.class);

				fieldList.add(f.getName() + "_" + annotation.mark() + "_" + annotation.dataType());

			}

		}

		return fieldList;
	}

	/**
	 * @date 2018��7��20��
	 * @readme ִ��ָ������ָ������
	 * @param instance
	 *            ִ�е���
	 * @param annotationClass
	 *            ִ�����з����ϵ�ע����
	 * @param annotationField
	 *            ע������ָ������
	 * @param annotationValue
	 *            ע������ָ�����Ե�ֵ
	 * @param params
	 *            ִ�����з����Ĳ���
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Object invoke(Object instance, Class<? extends Annotation> annotationClass, String annotationField,
			Object annotationValue, Object params)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException,
			InstantiationException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		
 
		Class<?> classE = instance.getClass();

		Method[] methods = classE.getMethods();

		for (Method method : methods) {

			// �жϸ÷����Ƿ����annotationClassע��
			if (method.isAnnotationPresent(annotationClass)) {

				// ʵ���÷����ϵ�ע����
				Object annotationClassInstance = method.getAnnotation(annotationClass);

				// ��ȡע������ָ������(annotationField)����
				// Field field =
				// annotationClass.getDeclaredField(annotationField);

				Method annotationM = annotationClass.getDeclaredMethod(annotationField);

				// ���Ʒ�װ ʵ����setAccessible�����úͽ��÷��ʰ�ȫ���Ŀ���,������Ϊtrue���ܷ���Ϊfalse�Ͳ��ܷ���
				// ����JDK�İ�ȫ����ʱ�϶�.����ͨ��setAccessible(true)�ķ�ʽ�رհ�ȫ���Ϳ��Դﵽ���������ٶȵ�Ŀ��
				annotationM.setAccessible(true);

				// �ж�annotationClassInstanceʵ����ע������annotationField���Ե�ֵ�Ƿ����annotationValue

				if (annotationM.invoke(annotationClassInstance).equals(annotationValue)) {
					method.setAccessible(true);
					return method.invoke(instance, params);
				}

			}

		}

		return null;

	}

	/**
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	public static String asXML(String xml) throws DocumentException {
		// ����SAXReader����
		SAXReader reader = new SAXReader();
		// ��ȡ�ļ� ת����Document
		Document document = reader.read(new File(methods.class.getClassLoader().getResource(xml).getPath()));
		return document.asXML();
	}

	/**
	 * @param xmlStr
	 * @param xml
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void strToXML(String xmlStr, String xml) throws IOException, DocumentException {

		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(
				new FileOutputStream(new File(methods.class.getClassLoader().getResource(xml).getPath())), format);

		System.out.println(methods.class.getClassLoader().getResource(xml).getPath());

		// ���ò��Զ�����ת��
		writer.setEscapeText(false);
		writer.write(DocumentHelper.parseText(xmlStr));
		writer.close();
	}

	public static boolean checServiceIdRepetition(String id) throws Exception {

		String xmlStr;
		xmlStr = asXML("SpringMVC.xml");
		return xmlStr.contains("id=\"" + id + "\"");
	}

	/**
	 * @param sp
	 * @return
	 */

	public static String createServiceXml(ServiceProperty sp) {

		StringBuffer sb = new StringBuffer();

		sb.append("<!--\r\n  ˵��:\r\n  �������´��룬��SpringMVC.xml�ļ���ճ����������������\r\n  -->\r\n");

		if (sp.getTransport().equals("com.transportlayer.Tcp")) {
			sb.append("<bean id=\"" + sp.getServiceId() + "\" class=\"com.transportlayer.Tcp\">");
			sb.append("\r\n");
			sb.append("	<property name=\"serviceId\" value=\"" + sp.getServiceId() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"Handler\" value=\"" + sp.getHandler() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"port\">");
			sb.append("\r\n");
			sb.append("		<list>");
			sb.append("\r\n");
			String portarray[] = sp.getPort().split(",");
			for (int i = 0; i < portarray.length; i++) {
				sb.append("			<value>" + portarray[i] + "</value>");
				sb.append("\r\n");
			}
			sb.append("		</list>");
			sb.append("\r\n");
			sb.append("	</property>");
			sb.append("\r\n");
			sb.append("	<property name=\"idle\" value=\"" + sp.getIdle() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"decodecharset\" value=\"" + sp.getDecodecharset() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"encodecharset\" value=\"" + sp.getEncodecharset() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"pushUrl\" value=\"" + sp.getPushUrl() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"pushflag\">");
			sb.append("\r\n");
			sb.append("		<value>" + sp.isPushflag() + "</value>");
			sb.append("\r\n");
			sb.append("	</property>");
			sb.append("\r\n");
			sb.append("	<property name=\"debugflag\">");
			sb.append("\r\n");
			sb.append("		<value>" + sp.isDebugflag() + "</value>");
			sb.append("\r\n");
			sb.append("	</property>");
			sb.append("\r\n");
			sb.append("	<property name=\"toServiceId\" value=\"" + sp.getServiceId() + "\"></property>");
			sb.append("\r\n");
			sb.append("</bean>");

		} else if (sp.getTransport().equals("com.transportlayer.Udp")) {

			sb.append("<bean id=\"" + sp.getServiceId() + "\" class=\"com.transportlayer.Udp\">");
			sb.append("\r\n");
			sb.append("	<property name=\"serviceId\" value=\"" + sp.getServiceId() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"Handler\" value=\"" + sp.getHandler() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"port\">");
			sb.append("\r\n");
			sb.append("		<list>");
			sb.append("\r\n");
			String portarray[] = sp.getPort().split(",");
			for (int i = 0; i < portarray.length; i++) {
				sb.append("			<value>" + portarray[i] + "</value>");
				sb.append("\r\n");
			}
			sb.append("		</list>");
			sb.append("\r\n");
			sb.append("	</property>");
			sb.append("\r\n");
			sb.append("	<property name=\"decodecharset\" value=\"" + sp.getDecodecharset() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"encodecharset\" value=\"" + sp.getEncodecharset() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"pushUrl\" value=\"" + sp.getPushUrl() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"pushflag\">");
			sb.append("\r\n");
			sb.append("		<value>" + sp.isPushflag() + "</value>");
			sb.append("\r\n");
			sb.append("	</property>");
			sb.append("\r\n");
			sb.append("	<property name=\"debugflag\">");
			sb.append("\r\n");
			sb.append("		<value>" + sp.isDebugflag() + "</value>");
			sb.append("\r\n");
			sb.append("	</property>");
			sb.append("\r\n");
			sb.append("	<property name=\"toServiceId\" value=\"" + sp.getServiceId() + "\"></property>");
			sb.append("\r\n");
			sb.append("</bean>");

		} else if (sp.getTransport().equals("com.transportlayer.Usart")) {

			sb.append("<bean id=\"" + sp.getServiceId() + "\" class=\"com.transportlayer.Usart\">");
			sb.append("\r\n");
			sb.append("	<property name=\"serviceId\" value=\"" + sp.getServiceId() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"handler\" value=\"" + sp.getHandler() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"comName\" value=\"" + sp.getPort() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"baud\" value=\"" + sp.getBaud() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"decodecharset\" value=\"" + sp.getDecodecharset() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"encodecharset\" value=\"" + sp.getEncodecharset() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"pushUrl\" value=\"" + sp.getPushUrl() + "\"></property>");
			sb.append("\r\n");
			sb.append("	<property name=\"pushflag\">");
			sb.append("\r\n");
			sb.append("		<value>" + sp.isPushflag() + "</value>");
			sb.append("\r\n");
			sb.append("	</property>");
			sb.append("\r\n");
			sb.append("	<property name=\"debugflag\">");
			sb.append("\r\n");
			sb.append("		<value>" + sp.isDebugflag() + "</value>");
			sb.append("\r\n");
			sb.append("	</property>");
			sb.append("\r\n");
			sb.append("	<property name=\"toServiceId\" value=\"" + sp.getServiceId() + "\"></property>");
			sb.append("\r\n");
			sb.append("</bean>");

		} else if (sp.getTransport().equals("com.transportlayer.Mqtt")) {

		}
		return sb.toString();
	}

	/**
	 * @param sp
	 * @throws Exception
	 */

	/*
	 * public static void createService(ServiceProperty sp) throws Exception {
	 * 
	 * String xmlStr; StringBuffer sb = new StringBuffer();
	 * 
	 * xmlStr = asXML("SpringMVC.xml"); String springBeanUtils =
	 * "<bean id=\"springBeanUtils\" class=\"com.application.SpringBeanUtils\"/>"
	 * ; String transpond =
	 * "<bean id=\"transpond\" class=\"com.transpond.TranspondService\">";
	 * 
	 * int springBeanUtils_index = xmlStr.indexOf(springBeanUtils); int
	 * transpond_index = xmlStr.indexOf(transpond);
	 * 
	 * sb.append(xmlStr.substring(0, springBeanUtils_index +
	 * springBeanUtils.length()));
	 * sb.append(xmlStr.substring(springBeanUtils_index +
	 * springBeanUtils.length(), transpond_index));
	 * 
	 * sb.append(createServiceXml(sp));
	 * 
	 * sb.append(xmlStr.substring(transpond_index));
	 * 
	 * 
	 * methods.strToXML(sb.toString(), "SpringMVC.xml");
	 * 
	 * }
	 */

	/**
	 * ׷�ӿո�
	 * 
	 * @param str
	 * @return
	 */
	public static String putSpace(String str) {
		String ret = "";

		if (str.length() < 16) {

			int k = 16 - str.length();

			for (int i = 0; i < k; i++) {
				ret = ret + " ";
			}
			ret = str + ret;
		}

		return ret;
	}

	/**
	 * @param msg
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeGBK(String msg) throws UnsupportedEncodingException {
		byte[] array = msg.getBytes("GBK");
		return new String(array, "ISO-8859-1");
	}

	/**
	 * @date 2018��8��30��
	 * @readme �ַ���תBCD��
	 * @param s
	 * @return
	 */
	public static String toBCDString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			if (s4.length() == 1)
				s4 = "0" + s4;
			str = str + s4;
		}
		return str;
	}

	/**
	 * @date 2018��8��30��
	 * @readme �ж��ļ��Ƿ���ڣ����������Զ�����
	 * @param fileName
	 * @throws IOException
	 */
	public static void newFile(String fileName) throws IOException {

		File file = new File(fileName);
		File fileParent = file.getParentFile();
		if (!fileParent.exists()) {
			fileParent.mkdirs();
		}
		file.createNewFile();

	}

	/**
	 *@date 2018��8��31��
	 *@readme �ж��ļ��Ƿ����
	 *@param fileName
	 *@return
	 */
	public static boolean fileExists(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			return true;
		}
		return false;
	}

}
