package net.cctv3.BaijiaJiangtan.x;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpClientUtils {
    /**
     * HttpClient 的 Get 请求方式
     * 使用 GetMethod 来访问一个URL对应的网页实现步骤：
     * 1. 生成一个 HttpClient 对象并设置相应的参数；
     * 2. 生成一个 GetMethod 对象并设置响应的参数；
     * 3. 用 HttpClient 生成的对象来执行 GetMethod 生成的 Get 方法；
     * 4. 处理响应状态码；
     * 5. 若响应正常，处理 HTTP 响应内容；
     * 6. 释放连接。
     *
     * @param url
     * @param charset
     * @return
     */
    public static String doGet(String url, String charset) {
        // 1. 生成 HttpClient 对象并设置参数
        HttpClient httpClient = new HttpClient();
        // 设置 Http 连接超时为5秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
        // 2. 生成GetMethod 对象并设置参数
        GetMethod getMethod = new GetMethod(url);
        // 设置 Get 请求超时为5秒
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        // 设置请求重试处理，用的是默认的重试处理：请求三次
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        String response = "";
        // 3. 执行HTTP GET 请求
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            // 4. 判断访问的状态码
            if (statusCode == HttpStatus.SC_OK) {
                // 5. 处理 HTTP 响应内容
                // HTTP响应头部信息，这里简单打印
                // Header[] headers = getMethod.getResponseHeaders();
                // 读取 HTTP 响应内容，这里简单打印网页内容
                // 读取为字节数组
                // byte[] responseBody = getMethod.getResponseBody();
                // response = new String(responseBody, charset);
                // 读取为InputStream，在网页内容数据量大时候推荐使用
                InputStream is = getMethod.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = "";
                StringBuilder builder = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    builder.append(line + "\n");
                }
                response = builder.toString();
            }
        } catch (HttpException e) {
            // 发生致命的异常，可能是协议不对或者返回的内容有问题
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6. 释放连接
            getMethod.releaseConnection();
        }
        return response;
    }

    /**
     * Post请求
     *
     * @param url
     * @param json
     * @return
     */
    public static String doPost(String url, JSONObject json) {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);

        postMethod.addRequestHeader("accept", "*/*");
        postMethod.addRequestHeader("connection", "Keep-Alive");
        // 设置 JSON 格式传送
        postMethod.addRequestHeader("Content-Type", "application/json;charset=GBK");
        // 必须设置下面这个 Header
        postMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        // 添加请求参数
        postMethod.addParameter("commentId", json.getString("commentId"));
        String res = "";
        try {
            int code = httpClient.executeMethod(postMethod);
            if (code == 200) {
                res = postMethod.getResponseBodyAsString();
                System.out.println(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
