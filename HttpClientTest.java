import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.StyleSheet.ListPainter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

/**
 * @author Iver3oN Zhang
 * @date 2016��3��23��
 * @email grepzwb@qq.com HttpClientTest.java Impossible is nothing
 */
public class HttpClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// httpClientGet();
		// httpClientPost();
		//httpurlconget();
		httpurlconpost();
	}

	// httpClient ��ʽ����get����
	public static void httpClientGet() {
		System.out.println("--------------");
		String urlAddress = "http://192.168.1.101:8080/ServletTest/servlet/ServletAndHttpClient";
		String username = "zwb";
		String password = "qwerty";
		String getUrl = urlAddress + "?username=" + username + "&password="
				+ password;
		HttpGet httpGet = new HttpGet(getUrl);
		HttpParams hp = httpGet.getParams();
		hp.getParameter("true");
		// hp.
		// httpGet.setp

		HttpClient hc = new DefaultHttpClient();
		try {
			HttpResponse ht = hc.execute(httpGet);
			System.out.println("xaxaxa");
			System.out.println(ht.getStatusLine().getStatusCode());
			if (ht.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				System.out.println("OK");
				HttpEntity he = ht.getEntity();
				InputStream is = he.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, "UTF-8"));
				String response = "";
				String readLine = null;
				while ((readLine = br.readLine()) != null) {
					// response = br.readLine();
					response = response + readLine;
				}
				is.close();
				br.close();
				// String str = EntityUtils.toString(he);
				System.out.println("=========" + response);
			} else {
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// HttpClient ��POST��ʽ
	public static void httpClientPost() {
		String urlAddress = "http://192.168.1.101:8080/ServletTest/servlet/ServletAndHttpClient";

		HttpPost httpPost = new HttpPost(urlAddress);
		List paras = new ArrayList<>();
		NameValuePair pair1 = new BasicNameValuePair("username", "hls");
		NameValuePair pair2 = new BasicNameValuePair("password", "sasadad");
		paras.add(pair1);
		paras.add(pair2);
		try {
			HttpEntity entity = new UrlEncodedFormEntity(paras, "gbk");
			httpPost.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			try {
				HttpResponse ht = client.execute(httpPost);
				// ���ӳɹ�
				if (ht.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity het = ht.getEntity();
					InputStream is = het.getContent();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(is, "UTF-8"));
					String response = "";
					String readLine = null;
					while ((readLine = br.readLine()) != null) {
						// response = br.readLine();
						response = response + readLine;
					}
					is.close();
					br.close();

					// String str = EntityUtils.toString(he);
					System.out.println("=========&&" + response);
				} else {
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void httpurlconget() {
		//IP��ַ���ܻ��  ������������ ��Ҫ������ �ſ���ʹ��
		String urlAddress = "http://192.168.1.105:8080/ServletTest/servlet/ServletAndHttpClient";
		String username = "zwb";
		String password = "qwerty";
		String getUrl = urlAddress + "?username=" + username + "&password="
				+ password;
		try {
			URL url = new URL(getUrl);
			HttpURLConnection uRLConnection = (HttpURLConnection) url
					.openConnection();
			//uRLConnection.connect();
			InputStream is = uRLConnection.getInputStream();
			 // ��ȡ������Ӧͷ�ֶ�  
            Map<String, List<String>> map = uRLConnection.getHeaderFields();  
            // �������е���Ӧͷ�ֶ�  
            for (String key : map.keySet()) {  
                System.out.println(key + "--->" + map.get(key));  
            }  
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			String response = "";
			String readLine = null;
			while ((readLine = br.readLine()) != null) {
				// response = br.readLine();
				response = response + readLine;	
			}
			System.out.println(response);
			is.close();
			br.close();
			uRLConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void httpurlconpost() {
		String urlAddress = "http://192.168.1.105:8080/ServletTest/servlet/ServletAndHttpClient";
		String username = "zzzzzzzzzzzzzzz";
		String password = "qwerty";
		// post��ʽҪ�������������out���д���
		try {
			URL url = new URL(urlAddress);
			HttpURLConnection uRLConnection = (HttpURLConnection) url
					.openConnection();
			uRLConnection = (HttpURLConnection) url.openConnection();
			uRLConnection.setDoInput(true);
			uRLConnection.setDoOutput(true);
			uRLConnection.setRequestMethod("POST");
			uRLConnection.setUseCaches(false);
			uRLConnection.setInstanceFollowRedirects(false);
			uRLConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			uRLConnection.connect();
			/* // ��ȡURLConnection�����Ӧ�������  
            out = new PrintWriter(conn.getOutputStream());  
            // �����������  
            out.print(param);  
            // flush������Ļ���  
            out.flush(); */ 
			DataOutputStream out = new DataOutputStream(
					uRLConnection.getOutputStream());
			//�������ص� HTTPURLCONNECTION ��POST��ʽ
			String content = "username=" + username + "&password=" + password;
			out.writeBytes(content);
			out.flush();
			out.close();

			InputStream is = uRLConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			String response = "";
			String readLine = null;
			while ((readLine = br.readLine()) != null) {
				// response = br.readLine();
				response = response + readLine;
			}
			System.out.println(response);
			is.close();
			br.close();
			uRLConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void httpurlcon() {
		// TODO Auto-generated method stub
		try {
			URL url = new URL("https://www.baidu.com");
			// �˴���urlConnection����ʵ�����Ǹ���URL��
			// ����Э��(�˴���http)���ɵ�URLConnection��
			// ������HttpURLConnection,�ʴ˴���ý���ת��
			// ΪHttpURLConnection���͵Ķ���,�Ա��õ�
			// HttpURLConnection�����API.����:
			URLConnection con = url.openConnection();
			HttpURLConnection urlConn = (HttpURLConnection) con;
			// �����Ƿ���httpUrlConnection�������Ϊ�����post���󣬲���Ҫ����
			// http�����ڣ������Ҫ��Ϊtrue, Ĭ���������false;
			urlConn.setDoOutput(true);
			urlConn.setRequestProperty("contentType", "GBK");
			// �����Ƿ��httpUrlConnection���룬Ĭ���������true;
			// urlConn.setDoInput(true);
			String s1 = urlConn.getContentEncoding();
			System.out.println(s1);

			// �趨���͵����������ǿ����л���java����
			// (����������,�ڴ������л�����ʱ,��WEB����Ĭ�ϵĲ�����������ʱ������java.io.EOFException)
			urlConn.setRequestProperty("Content-type",
					"application/x-java-serialized-object");

			// �趨����ķ���Ϊ"POST"��Ĭ����GET
			urlConn.setRequestMethod("GET");
			// ���ӣ������urlConn���������ñ���Ҫ��connect֮ǰ��ɣ�
			urlConn.connect();
			// �˴�getOutputStream�������Ľ���connect (������ͬ���������connect()������
			// �����ڿ����в�����������connect()Ҳ����)��
			InputStream outStrm = urlConn.getInputStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					outStrm));
			String s = "";
			while ((s = bf.readLine()) != null) {
				System.out.println(s);
			}

			urlConn.disconnect();
			// BufferedOutputStream bo = new BufferedOutputStream(outStrm);

			/*
			 * byte[] b = new byte[1024]; String tem = ""; bo.write(b,
			 * 0,b.length);
			 */

			/*
			 * // ����ͨ����������󹹽����������������ʵ����������л��Ķ��� ObjectOutputStream oos = new
			 * ObjectOutputStream(outStrm);
			 * 
			 * // ����������д�����ݣ���Щ���ݽ��浽�ڴ滺������ oos.writeObject(new
			 * String("���ǲ�������"));
			 * 
			 * // ˢ�¶�������������κ��ֽڶ�д��Ǳ�ڵ����У�Щ��ΪObjectOutputStream�� oos.flush();
			 * 
			 * // �ر������󡣴�ʱ������������������д���κ����ݣ���ǰд������ݴ������ڴ滺������, //
			 * �ٵ����±ߵ�getInputStream()����ʱ�Ű�׼���õ�http������ʽ���͵������� oos.close();
			 */
			// ����HttpURLConnection���Ӷ����getInputStream()����,
			/*
			 * // ���ڴ滺�����з�װ�õ�������HTTP������ķ��͵�����ˡ� InputStream inStrm =
			 * urlConn.getInputStream(); // <===ע�⣬ʵ�ʷ�������Ĵ���ξ�������
			 * 
			 * //----------------------------------
			 * 
			 * Post���εķ���
			 * 
			 * OutputStream os = urlConn.getOutputStream(); String param = new
			 * String(); param = "CorpID=123&LoginName=qqq&name=" +
			 * URLEncoder.encode("����","GBK"); ; os.write(param.getBytes());
			 * 
			 * //----------------------------------
			 * 
			 * ��ʱ���ã���ֹ �����쳣������£����ܻᵼ�³�����������������ִ��
			 * 
			 * 
			 * //JDK 1.5��ǰ�İ汾��ֻ��ͨ������������ϵͳ�������������糬ʱ: //���������ĳ�ʱʱ�䣨��λ�����룩
			 * System.setProperty("sun.net.client.defaultConnectTimeout",
			 * "30000"); //��������ȡ���ݵĳ�ʱʱ�䣨��λ�����룩
			 * System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			 * 
			 * //��JDK 1.5�Ժ�������������ó�ʱʱ�� HttpURLConnection urlCon =
			 * (HttpURLConnection)url.openConnection();
			 * urlCon.setConnectTimeout(30000); urlCon.setReadTimeout(30000);
			 */

			// ----------------------------------
			/*
			 * �ܽ᣺
			 * HttpURLConnection��connect()������ʵ����ֻ�ǽ�����һ�����������tcp���ӣ���û��ʵ�ʷ���http����
			 * ������post����get
			 * ��http����ʵ����ֱ��HttpURLConnection��getInputStream()��������������ʽ���ͳ�ȥ��
			 * 
			 * ��HttpURLConnection�����һ�����ö�����Ҫ��connect()����ִ��֮ǰ��ɡ�
			 * ����outputStream��д�������ֱ���Ҫ��inputStream�Ķ�����֮ǰ�� ��Щ˳��ʵ��������http����ĸ�ʽ�����ġ�
			 * 
			 * ��httpͷ��������ŵ���http��������ģ����ĵ�������ͨ��outputStream��д��ģ�
			 * ʵ����outputStream����һ�����������������Ǹ��ַ�������������д��Ķ��������������͵����磬
			 * ���Ǵ������ڴ滺�����У���outputStream���ر�ʱ�������������������http���ġ�
			 * ���ˣ�http����Ķ����Ѿ�ȫ��׼����������getInputStream()�������õ�ʱ�򣬾ͻ��׼���õ�http����
			 * ��ʽ���͵��������ˣ�Ȼ�󷵻�һ�������������ڶ�ȡ���������ڴ˴�http����ķ�����Ϣ������http
			 * ������getInputStream��ʱ���Ѿ����ͳ�ȥ�ˣ�����httpͷ�����ģ��������getInputStream()����
			 * ֮���connection����������ã���httpͷ����Ϣ�����޸ģ�����д��outputStream�������Ľ����޸ģ�
			 * ����û��������ˣ�ִ����Щ�����ᵼ���쳣�ķ�����
			 */

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
