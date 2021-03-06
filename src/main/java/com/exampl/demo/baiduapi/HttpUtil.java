package com.exampl.demo.baiduapi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * http 工具�?
 */
public class HttpUtil {

    public static String post(String requestUrl, String accessToken, String params)
            throws Exception {
        String contentType = "application/x-www-form-urlencoded";
        return HttpUtil.post(requestUrl, accessToken, contentType, params);
    }
    /**
     * 向指定url发送post包
     * @param requestUrl 目标url
     * @param authorization token 或者 key
     * @param contentType 包类型
     * @param params 输入参数（将json写为string格式）
     * @return 返回json的string格式
     * @throws Exception
     */
    public static String post(String requestUrl, String authorization, String contentType, String params)
             {
        String encoding = "UTF-8";
        if (requestUrl.contains("nlp")) {
            encoding = "GBK";
        }
       
        return HttpUtil.postGeneralUrl(requestUrl, authorization, contentType, params, encoding);
        
    }


    public static String postGeneralUrl(String generalUrl,String authorization, String contentType, String params, String encoding)
             {
    	String result = "";
    	String statecode="";
    	try {
        URL url = new URL(generalUrl);
        // 打开和URL之间的连�?
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        // 设置通用的请求属�?
        connection.setRequestProperty("Content-Type", contentType);
        if(authorization!=null)connection.setRequestProperty("Authorization", authorization);
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // 得到请求的输出流对象
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.write(params.getBytes(encoding));
        out.flush();
        out.close();

        // 建立实际的连�?
        connection.connect();
        statecode+=connection.getResponseCode();
        // 获取�?有响应头字段
        Map<String, List<String>> headers = connection.getHeaderFields();
        // 遍历�?有的响应头字�?
        for (String key : headers.keySet()) {
            System.err.println(key + "--->" + headers.get(key));
        }
        // 定义 BufferedReader输入流来读取URL的响�?
        BufferedReader in = null;
        in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), encoding));
        String getLine;
        while ((getLine = in.readLine()) != null) {
            result += getLine;
        }
        in.close();
        
        System.err.println("result:" + result);
    	}catch(Exception e)
    	{
    		return "{\"errorcode\":\""+statecode+"\"}";
    	}
        return result;
    }
}
