package com.exampl.demo.faceidentify;


import org.json.JSONException;
import com.baidu.aip.face.AipFace;

import com.exampl.demo.faceidentify_i.BaseFunctions_I;

public class BaseFunctions implements BaseFunctions_I{
	private  AipFace client;
	public static final String APP_ID = "17500318";
	public static final String API_KEY = "aT4Z4jLrrxNNz86yOWGtwaA0";
	public static final String SECRET_KEY = "MaQC5FIEzCQTUs2XfifYn2FMisybXPby";
	public BaseFunctions() {
		// ��ʼ��һ��AipFace
		client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
	}
	
	@Override
	public int ISExistFace(String imageB64) {
		// TODO Auto-generated method stub
		// �������
		try {
			if(client.detect(imageB64, "BASE64", null).getInt("error_code")==0)
				return 1;
			else return 0;
			
		}catch(Exception e2) {
			return -1;
		}		
	}

	public static AipFace getClient() {
		
		return new AipFace(APP_ID, API_KEY, SECRET_KEY);
	}	
}
