
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpMethod;
import okio.BufferedSink;

/**
 * Hello world!
 *
 */
public class OkHttpUtils {
	
	private static OkHttpClient client = new OkHttpClient();
	
	public static void main(String[] args) throws IOException {
		String text1 = get("https://www.cnblogs.com");
		System.out.println(text1);
		
		String text2 = method("https://www.baidu.com", "GET", null);
		System.out.println(text2);
	}
	
	/**
	 * HTTP通用请求方法，简单封装，不完善
	 * @author 杨雄辉
	 * update: 2020年11月17日 下午5:36:01
	 * @param url
	 * @param method
	 * @param json
	 * @return String
	 * @throws IOException
	 */
	public static String method(String url, String method, String json) throws IOException {
		RequestBody body = null;
		if(HttpMethod.permitsRequestBody(method)) {
			MediaType JSON = MediaType.parse("application/json; charset=utf-8");
			body = RequestBody.create(JSON, json);
		}
		
		Request request = new Request.Builder()
				.url(url)
				.method(method, body)
				.build();
		Call call = client.newCall(request);
		// 同步调用,返回Response,会抛出IO异常
		Response response = call.execute();
		return response.body().string();
	}

	/**
	 * GET请求
	 * @author 杨雄辉
	 * update: 2020年11月17日 下午5:16:45
	 * @param url
	 * @return String
	 * @throws IOException
	 */
	public static String get(String url) throws IOException {
		Request request = new Request.Builder()
				.url(url)
				.get()
				.build();
		Call call = client.newCall(request);
		// 同步调用,返回Response,会抛出IO异常
		Response response = call.execute();
		return response.body().string();
	}
	
	/**
	 *  异步GET请求
	 * @author 杨雄辉
	 * update: 2020年11月17日 下午5:17:50
	 * @param url
	 * @return void
	 * @throws IOException
	 */
	public static void getAsync(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(url)
				.get()
				.build();
		Call call = client.newCall(request);

		//异步调用,并设置回调函数
		call.enqueue(new Callback() {
			
			public void onResponse(Call call, Response response) throws IOException {
				System.out.println(response.body().string());
			}
			
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}
		});
		client.dispatcher().executorService().shutdown();
	}
	
	/**
	 * POST请求
	 * @author 杨雄辉
	 * update: 2020年11月17日 下午5:19:57
	 * @param url
	 * @param json
	 * @return String
	 * @throws IOException
	 */
	public static String post(String url, String json) throws IOException {
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		Call call = client.newCall(request);
		// 同步调用,返回Response,会抛出IO异常
		Response response = call.execute();
		return response.body().string();
	}
	
	/**
	 * put请求
	 * @author 杨雄辉
	 * update: 2020年11月17日 下午5:20:16
	 * @param url
	 * @param json
	 * @return String
	 * @throws IOException
	 */
	public static String put(String url, String json) throws IOException {
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder()
				.url(url)
				.put(body)
				.build();
		Call call = client.newCall(request);
		// 同步调用,返回Response,会抛出IO异常
		Response response = call.execute();
		return response.body().string();
	}
	
	/**
	 * delete请求
	 * @author 杨雄辉
	 * update: 2020年11月17日 下午5:20:22
	 * @param url
	 * @param json
	 * @return String
	 * @throws IOException
	 */
	public static String delete(String url, String json) throws IOException {
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder()
				.url(url)
				.delete(body)
				.build();
		Call call = client.newCall(request);
		// 同步调用,返回Response,会抛出IO异常
		Response response = call.execute();
		return response.body().string();
	}
	
}
