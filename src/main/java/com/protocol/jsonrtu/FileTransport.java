/**
 * 
 */
package com.protocol.jsonrtu;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;



/**
 * @author xizhonghuai
 * @date 2018��7��12��
 * @readme
 */
@Controller
public class FileTransport {

	@RequestMapping(value = "/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam("filename") String filename)
			throws Exception {
		// �����ļ�·��
		String path = "c:\\traingData\\";
		File file = new File(path + filename);
		HttpHeaders headers = new HttpHeaders();
		// ������ʾ���ļ������������������������
		String downloadFielName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
		// ֪ͨ�������attachment�����ط�ʽ����ͼƬ
		headers.setContentDispositionFormData("attachment", downloadFielName);
		// application/octet-stream �� �����������ݣ�������ļ����أ���
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
	}

	@RequestMapping("upload")
	@ResponseBody
	public String springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
		String path = "";
		// ����ǰ�����ĳ�ʼ���� CommonsMutipartResolver ���ಿ�ֽ�������
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// ���form���Ƿ���enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// ��request��ɶಿ��request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// ��ȡmultiRequest�����е��ļ���
			@SuppressWarnings("rawtypes")
			Iterator iter = multiRequest.getFileNames();
			// һ�α��������ļ�
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					path = "c:/upload/" + file.getOriginalFilename();
					// �ϴ�
					file.transferTo(new File(path));
				}

			}

		}

		System.out.println("�ļ�����·����" + path);

		return "OK";
	}

}
