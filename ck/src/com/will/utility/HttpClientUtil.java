/**
 * 2010(c) Copyright Oceansoft Information System Co.,LTD. All rights reserved.
 * <p>
 * Compile: JDK 1.6+
 * <p>
 * 版权所有(C)：江苏欧索软件有限公司
 * <p>
 * 公司名称：江苏欧索软件有限公司
 * <p>
 * 公司地址：中国苏州科技城青山路1号
 * <p>
 * 网址: http://www.oceansoft.com.cn
 * <p>
 * 作者: 090922(陈伟)
 * <p>
 * 文件名: com.oceansoft.mobile.econsole.common.util.HttpClient.java 
 * <p>
 * 类产生时间: 2014-7-9 0009 下午 15:03
 * <p>
 * 负责人: 090922(陈伟)
 * <p>
 * Email:javacspring@gmail.com
 * <p>
 * 所在组 : 掌上公安应用平台
 * <p>
 * 所在部门: 开发部--手持技术部
 * <p>
 * <p>
 */
package com.will.utility;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * HttpClient请求工具类
 *
 * @author: chenw
 * @time: 2014-7-9 0009 下午 15:03
 */
public class HttpClientUtil {

	private static final Logger log = Logger.getLogger(HttpClientUtil.class);
    public static final String CHARSET = "UTF-8";
    private static final CloseableHttpClient httpClient;

    public static CloseableHttpClient getClient() {
        return httpClient;
    }

    /**
     * 执行GET请求
     *
     * @param url GET请求URL字符串
     * @return 响应字符串
     * @throws HttpException 传入参数或返回状态码非200时抛出运行时错误
     */
    public static String doGet(String url) throws IOException, IllegalArgumentException {
        if (null == url || url.trim().length() == 0) {
            throw new HttpException("URL地址错误");
        }
        String result = null;
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
                release(response, httpGet);
                throw new HttpException("HttpGet error,status code:" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
        } finally {
            release(response, httpGet);
        }
        return result;
    }

    /**
     * 执行带参数Get请求
     *
     * @param url    请求URL地址
     * @param params 请求参数，参数个数必须为偶数且数据类型为String,Char,int，否则产生错误<br>如："name","Tom","age",12
     * @return 响应内容
     * @throws IOException
     */
    public static String doGet(String url, Object... params) throws IOException, IllegalArgumentException {
        return doGet(processParams(url, params));
    }

    /**
     * POST请求StringEntity数据
     *
     * @param url  请求URL地址
     * @param data 请求JSON数据
     * @return String 响应JSON字符串
     * @throws IOException
     */
    public static String post(String url, JSONObject data) throws IOException {
        if (null == url || url.trim().length() == 0) {
            throw new HttpException("URL地址错误");
        }
        String result = null;
        HttpEntity httpEntity;
        HttpPost httpPost = null;
        CloseableHttpResponse response = null;
        try {
            httpEntity = new StringEntity(data.toJSONString(), ContentType.APPLICATION_JSON);
            httpPost = new HttpPost(url);
            httpPost.setEntity(httpEntity);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != statusCode  ) {
               System.out.println("statusCode::::::"+statusCode);
            }
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
        } finally {
            release(response, httpPost);
        }
        return result;
    }

    /**
     * 模拟StringEntity表单提交
     *
     * @return 返回结果
     */
    public static Result doGAUrlPost(String url, JSONObject data) throws IOException {
        if (null == url || url.trim().length() == 0) {
            throw new HttpException("URL地址错误");
        }
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            HttpEntity httpEntity = new StringEntity(data.toJSONString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(httpEntity);
            response = httpClient.execute(httpPost);
            httpEntity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != statusCode) {
                return new Result(Result.FAILURE, statusCode, "请求失败");
            }
            if (null != httpEntity) {
                JSONObject obj = JSONObject.parseObject(EntityUtils.toString(httpEntity));
                Result rt = new Result("True".equalsIgnoreCase(obj.getString("succ")), obj.getString("msg"));
                EntityUtils.consume(httpEntity);
                return rt;
            } else {
                return new Result(Result.FAILURE, "返回值为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Result.FAILURE, "提交失败");
        } finally {
            release(response, httpPost);
        }
    }

    public static String doPostString(String url, Object data) {
    	log.info(url+"    ,      "+JSON.toJSONString(data));
    	System.out.println("url:  "+ url);
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(JSON.toJSONString(data), ContentType.APPLICATION_JSON));
            
         //   httpClient = new HttpClientUtil()
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println("HttpEntity=====>" + entity);
                if (null != entity) {
                    return EntityUtils.toString(entity);
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                release(response, httpPost);
            }
        } catch (IOException e) {
            log.error("调用云学堂错误信息:   ",e);
            return null;
        }
    }

    public static CloseableHttpResponse getHttpResponse(String url, Map<String, String> params) {
        if (!StringUtils.hasText(url)) {
            return null;
        }
        try {
            url = processGetParams(url, params, CHARSET);
            HttpGet httpGet = new HttpGet(url);
            System.out.println("URL===>" + url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * HTTP Post 获取内容
     *
     * @param url     请求的url地址 ?之前的地址
     * @param params  请求的参数
     * @param charset 编码格式
     * @return 页面内容
     */
    public static String doPost(String url, Map<String, String> params, String charset) {
        if (!StringUtils.hasText(url)) {
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if (params != null && !params.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 处理GET参数
     *
     * @param url     请求的url地址 ?之前的地址
     * @param params  请求参数
     * @param charset 请求参数字符集
     * @return String
     * @throws IOException
     */
    private static String processGetParams(String url, Map<String, String> params, String charset) throws IOException {
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
            url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
        }
        return url;
    }

    /**
     * 处理请求参数
     * <br> 请求参数以可变参数形式传入，其中奇数位参数为key，偶数位参数为value.
     * <br>如：processParams("http://172.17.100.139/econsole/api/profile","name","tom","age",12,"height",188)
     *
     * @param baseUrl 资源地址
     * @param params  请求参数
     * @return String 完整请求URL 如：http://172.17.100.139/econsole/api/profile?name=tom&age=12&height=188
     * @throws IllegalArgumentException
     */
    private static String processParams(String baseUrl, Object... params) throws IllegalArgumentException {
        int len = params.length;
        StringBuilder rt = new StringBuilder(baseUrl);
        if (len > 1 && 0 == len % 2) {
            for (int i = 0; i < len; i++) {
                Object obj = params[i];
                if (obj instanceof Integer || obj instanceof String || obj instanceof Character) {
                    if (i == 0) {
                        rt.append("?");
                    } else {
                        rt.append("&");
                    }
                    rt.append(params[i]).append("=");
                    i++;
                    rt.append(params[i]);
                } else {
                    throw new IllegalArgumentException("参数类型错误，仅允许String,Character,Integer类型参数");
                }
            }
        } else if (len == 0) {
            return baseUrl;
        } else {
            throw new IllegalArgumentException("参数不正确，传入参数量必须为偶数");
        }
        return rt.toString();
    }

    /**
     * 释放链接资源
     *
     * @param response   响应流
     * @param httpMethod 执行请求方法
     */
    private static void release(Closeable response, HttpRequestBase httpMethod) {
        if (null != response) {
            try {
                response.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            httpMethod.abort();
            httpMethod.releaseConnection();
        }
    }
    
    public static String postHttpJsonRequest(String url,
			String params) {

		String responseBody = null;

		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();

			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestMethod("POST");
			conn.setConnectTimeout(100000);
			conn.setReadTimeout(10000);

			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("accept", "application/json");
			conn.setRequestProperty("Content-Type",
					"application/json;charset=UTF-8");
			conn.setRequestProperty("Connection", "keep-alive");

			conn.connect();

			DataOutputStream requestOut = new DataOutputStream(
					conn.getOutputStream());
			
			requestOut.writeBytes(params);
			requestOut.flush();
			requestOut.close();

			// 请求失败，跳出
			int res = conn.getResponseCode();
			if (res != HttpURLConnection.HTTP_OK) {
				System.out.println("Request [" + url + "] failed:" + res);
				return "";
			}

			// 请求成功
			String encoding = conn.getContentEncoding();
			InputStream is = null;
			ByteArrayOutputStream out = null;

			is = conn.getInputStream();

			// 写入内存
			out = new ByteArrayOutputStream();
			int read = -1;
			while ((read = is.read()) != -1) {
				out.write(read);
			}
			byte[] data = out.toByteArray();

			out.close();

			// 转换编码
			if (encoding != null) {
				responseBody = new String(data, encoding);
			} else {
				responseBody = new String(data);
			}

		} catch (IOException e) {
			System.out
					.println("Request [" + url + "] failed:" + e.getMessage());
		}

		return responseBody;

	}
    
    static {
        PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager();
        pcm.setMaxTotal(200);
        pcm.setDefaultMaxPerRoute(pcm.getMaxTotal());
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(120000).setSocketTimeout(150000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }
}
