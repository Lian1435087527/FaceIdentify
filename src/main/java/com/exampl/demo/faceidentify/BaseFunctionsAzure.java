package com.exampl.demo.faceidentify;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

import com.exampl.demo.faceidentify_i.BaseFunctions_I;

public class BaseFunctionsAzure implements BaseFunctions_I {
	private static AipFace client;
	private static JSONArray Detect_Azure;
	private static final String subscriptionKey = "e1707f2aa66a4302a579e546a18e241c";

	private static final String uriBase = "https://facetestface.cognitiveservices.azure.com/face/v1.0/detect";

	private static String faceAttributes = "";
	private CloseableHttpClient httpclient;

	public BaseFunctionsAzure() {
		// 初始化一个AipFace清楚
		httpclient = HttpClientBuilder.create().build();

		
	}

	@SuppressWarnings("finally")
	@Override
	public int ISExistFace(String imageB64) {
		// TODO Auto-generated method stub
		// 人脸检测
		try {
			URIBuilder builder = new URIBuilder(uriBase);

			// Request parameters. All of them are optional.
			builder.setParameter("returnFaceId", "true");
			builder.setParameter("returnFaceLandmarks", "false");
			builder.setParameter("returnFaceAttributes", faceAttributes);

			// Prepare the URI for the REST API call.
			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);

			// Request headers.
			request.setHeader("Content-Type", "application/octet-stream");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

			// Request body.将Base64编码转化为byte		
			byte[] imageByteArray= com.baidu.aip.util.Base64Util.decode(imageB64);
			ByteArrayEntity req = new ByteArrayEntity(imageByteArray);
			request.setEntity(req);

			// Execute the REST API call and get the response entity.
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
				// Format and display the JSON response.
				System.out.println("REST Response:\n");

				String jsonString = EntityUtils.toString(entity).trim();
				if (jsonString.charAt(0) == '[') {
					JSONArray jsonArray = new JSONArray(jsonString);
					Detect_Azure=jsonArray;
					return 1;
					
				} else if (jsonString.charAt(0) == '{') {
					JSONObject json=new JSONObject(jsonString);
					JSONArray temp=new JSONArray();
					temp.put(0, json);
					Detect_Azure = temp;
					return 1;
				} else {
					return 0;
				}
			}
			return -1;
		} catch (Exception e) {
			// Display error message.
			return -1;
		}		
	}

	public static AipFace getClient() {

		return client;
	}

	private int times = 0;

	/**
	 * 测试运行时间
	 */
	@SuppressWarnings("deprecation")
	private void Testtime() {
		Date date = new Date();
		times++;
		System.out.println(this.getClass().getName() + " " + new Integer(times).toString() + "->" + date.getMinutes()
				+ ":" + date.getSeconds());
	}

	/**
	 * 获得Baidu人脸检测返回结果
	 * 
	 * @return
	 */
	public static JSONArray getDetect_Azure() {
		return Detect_Azure;
	}
}
