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
 * @date 2016年3月23日
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

	// httpClient 方式发送get请求
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

	// HttpClient 的POST方式
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
				// 连接成功
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
		//IP地址可能会变  在无线网络下 需要有网络 才可以使用
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
			 // 获取所有响应头字段  
            Map<String, List<String>> map = uRLConnection.getHeaderFields();  
            // 遍历所有的响应头字段  
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
		// post方式要把请求参数加入out流中传递
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
			/* // 获取URLConnection对象对应的输出流  
            out = new PrintWriter(conn.getOutputStream());  
            // 发送请求参数  
            out.print(param);  
            // flush输出流的缓冲  
            out.flush(); */ 
			DataOutputStream out = new DataOutputStream(
					uRLConnection.getOutputStream());
			//这里是重点 HTTPURLCONNECTION 的POST方式
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
			// 此处的urlConnection对象实际上是根据URL的
			// 请求协议(此处是http)生成的URLConnection类
			// 的子类HttpURLConnection,故此处最好将其转化
			// 为HttpURLConnection类型的对象,以便用到
			// HttpURLConnection更多的API.如下:
			URLConnection con = url.openConnection();
			HttpURLConnection urlConn = (HttpURLConnection) con;
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true, 默认情况下是false;
			urlConn.setDoOutput(true);
			urlConn.setRequestProperty("contentType", "GBK");
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			// urlConn.setDoInput(true);
			String s1 = urlConn.getContentEncoding();
			System.out.println(s1);

			// 设定传送的内容类型是可序列化的java对象
			// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
			urlConn.setRequestProperty("Content-type",
					"application/x-java-serialized-object");

			// 设定请求的方法为"POST"，默认是GET
			urlConn.setRequestMethod("GET");
			// 连接，上面对urlConn的所有配置必须要在connect之前完成，
			urlConn.connect();
			// 此处getOutputStream会隐含的进行connect (即：如同调用上面的connect()方法，
			// 所以在开发中不调用上述的connect()也可以)。
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
			 * // 现在通过输出流对象构建对象输出流对象，以实现输出可序列化的对象。 ObjectOutputStream oos = new
			 * ObjectOutputStream(outStrm);
			 * 
			 * // 向对象输出流写出数据，这些数据将存到内存缓冲区中 oos.writeObject(new
			 * String("我是测试数据"));
			 * 
			 * // 刷新对象输出流，将任何字节都写入潜在的流中（些处为ObjectOutputStream） oos.flush();
			 * 
			 * // 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中, //
			 * 再调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器 oos.close();
			 */
			// 调用HttpURLConnection连接对象的getInputStream()函数,
			/*
			 * // 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。 InputStream inStrm =
			 * urlConn.getInputStream(); // <===注意，实际发送请求的代码段就在这里
			 * 
			 * //----------------------------------
			 * 
			 * Post传参的方法
			 * 
			 * OutputStream os = urlConn.getOutputStream(); String param = new
			 * String(); param = "CorpID=123&LoginName=qqq&name=" +
			 * URLEncoder.encode("汉字","GBK"); ; os.write(param.getBytes());
			 * 
			 * //----------------------------------
			 * 
			 * 超时设置，防止 网络异常的情况下，可能会导致程序僵死而不继续往下执行
			 * 
			 * 
			 * //JDK 1.5以前的版本，只能通过设置这两个系统属性来控制网络超时: //连接主机的超时时间（单位：毫秒）
			 * System.setProperty("sun.net.client.defaultConnectTimeout",
			 * "30000"); //从主机读取数据的超时时间（单位：毫秒）
			 * System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			 * 
			 * //在JDK 1.5以后可以这样来设置超时时间 HttpURLConnection urlCon =
			 * (HttpURLConnection)url.openConnection();
			 * urlCon.setConnectTimeout(30000); urlCon.setReadTimeout(30000);
			 */

			// ----------------------------------
			/*
			 * 总结：
			 * HttpURLConnection的connect()函数，实际上只是建立了一个与服务器的tcp连接，并没有实际发送http请求。
			 * 无论是post还是get
			 * ，http请求实际上直到HttpURLConnection的getInputStream()这个函数里面才正式发送出去。
			 * 
			 * 对HttpURLConnection对象的一切配置都必须要在connect()函数执行之前完成。
			 * 而对outputStream的写操作，又必须要在inputStream的读操作之前。 这些顺序实际上是由http请求的格式决定的。
			 * 
			 * 在http头后面紧跟着的是http请求的正文，正文的内容是通过outputStream流写入的，
			 * 实际上outputStream不是一个网络流，充其量是个字符串流，往里面写入的东西不会立即发送到网络，
			 * 而是存在于内存缓冲区中，待outputStream流关闭时，根据输入的内容生成http正文。
			 * 至此，http请求的东西已经全部准备就绪。在getInputStream()函数调用的时候，就会把准备好的http请求
			 * 正式发送到服务器了，然后返回一个输入流，用于读取服务器对于此次http请求的返回信息。由于http
			 * 请求在getInputStream的时候已经发送出去了（包括http头和正文），因此在getInputStream()函数
			 * 之后对connection对象进行设置（对http头的信息进行修改）或者写入outputStream（对正文进行修改）
			 * 都是没有意义的了，执行这些操作会导致异常的发生。
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
