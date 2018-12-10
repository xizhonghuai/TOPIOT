/**
 * 
 */
package com.protocol.safemon.postmsg;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * @author xizhonghuai
 * @date 2018年7月23日
 * @readme
 */
public class PostService extends Thread {

	private Logger logger = Logger.getLogger(PostService.class);

	private String url;
	private String msg;
	private Map<String, Object> params = new ConcurrentHashMap<String, Object>();

	/**
	 * @param url
	 * @param msg
	 */
	public PostService(String url, String msg) {
		super();
		this.url = url;
		this.msg = msg;
		
		logger.info("提交数据："+msg);    

		params.put("data", msg);

	}

	@SuppressWarnings("unused")
	private String postCall() throws IOException {

		URL url = new URL(this.url);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Encoding", "UTF-8");
		connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		connection.connect();
		// POST请求
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		out.write(this.msg.getBytes("UTF-8"));

		out.flush();
		out.close();
		// 读取响应
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String lines;
		StringBuffer sb = new StringBuffer("");
		while ((lines = reader.readLine()) != null) {
			lines = new String(lines.getBytes(), "utf-8");
			sb.append(lines);
		}

		reader.close();
		// 断开连接
		connection.disconnect();

		return sb.toString();
	}

	@SuppressWarnings("unused")
	private static String Undecode(String unicodeStr) {
		if (unicodeStr == null) {
			return null;
		}
		StringBuffer retBuf = new StringBuffer();
		int maxLoop = unicodeStr.length();
		for (int i = 0; i < maxLoop; i++) {
			if (unicodeStr.charAt(i) == '\\') {
				if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
					try {
						retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
						i += 5;
					} catch (NumberFormatException localNumberFormatException) {
						retBuf.append(unicodeStr.charAt(i));
					}
				else
					retBuf.append(unicodeStr.charAt(i));
			} else {
				retBuf.append(unicodeStr.charAt(i));
			}
		}
		return retBuf.toString();
	}

	private String postCallStr() throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(this.url);

		Set<Map.Entry<String, Object>> sets = this.params.entrySet();
		Iterator<Map.Entry<String, Object>> it = sets.iterator();

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			nameValuePairs.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
		}
//		post.addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		post.addHeader("content-type", "application/x-www-form-urlencoded");
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

		String conResult = "";
		HttpResponse response = httpClient.execute(post);
		if (response.getStatusLine().getStatusCode() == 200) {
			// 读取返回数据
	//		conResult = Undecode(EntityUtils.toString(response.getEntity()));
			conResult = EntityUtils.toString(response.getEntity());
		}

		return conResult;
	}

	public void run() {

		try {

			// String ret = postCall();
			String ret = postCallStr();
			// String ret = postMsg();
			logger.info("请求响应：" + ret);

		} catch (Exception e) {
			// TODO Auto-generated catch block

			logger.info(e.toString());
		}
	}

}
