package com.exampl.demo.codeTest;
//This sample uses Apache HttpComponents:

//http://hc.apache.org/httpcomponents-core-ga/httpcore/apidocs/
//https://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestAzureFace {
	// Replace <Subscription Key> with your valid subscription key.
	private static final String subscriptionKey = "e1707f2aa66a4302a579e546a18e241c";

	private static final String uriBase = "https://facetestface.cognitiveservices.azure.com/face/v1.0/detect";

	private static final String faceAttributes = "";

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		HttpClient httpclient = HttpClientBuilder.create().build();

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

			// Request body.
			String pathToLocalImage = "E:\\大学\\学习\\驭光实习\\测试图片\\结构光图真人.jpg";
			byte[] imageByteArray;
			File rawImage = new File(pathToLocalImage);
			imageByteArray = Files.readAllBytes(rawImage.toPath());
			ByteArrayEntity req = new ByteArrayEntity(imageByteArray);
			request.setEntity(req);

			Date date = new Date();
			System.out.println("->" + date.getMinutes() + ":" + date.getSeconds());
			// Execute the REST API call and get the response entity.
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			Date date2 = new Date();
			System.out.println("->" + date2.getMinutes() + ":" + date2.getSeconds());
			if (entity != null) {
				// Format and display the JSON response.
				System.out.println("REST Response:\n");

				String jsonString = EntityUtils.toString(entity).trim();
				if (jsonString.charAt(0) == '[') {
					JSONArray jsonArray = new JSONArray(jsonString);
					System.out.println(jsonArray.toString(2));
				} else if (jsonString.charAt(0) == '{') {
					JSONObject jsonObject = new JSONObject(jsonString);
					System.out.println(jsonObject.toString(2));
				} else {
					System.out.println(jsonString);
				}
			}
		} catch (Exception e) {
			// Display error message.
			System.out.println(e.getMessage());
		}
	}
}
