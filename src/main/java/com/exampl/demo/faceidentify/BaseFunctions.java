package com.exampl.demo.faceidentify;

import java.util.Date;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

import com.exampl.demo.faceidentify_i.BaseFunctions_I;

public class BaseFunctions implements BaseFunctions_I {
	private static AipFace client;
	private static JSONObject Detect_Baidu;
	public static final String APP_ID = "17500318";
	public static final String API_KEY = "aT4Z4jLrrxNNz86yOWGtwaA0";
	public static final String SECRET_KEY = "MaQC5FIEzCQTUs2XfifYn2FMisybXPby";

	public BaseFunctions() {
		// 初始化一个AipFace
		client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
	}

	@SuppressWarnings("finally")
	@Override
	public int ISExistFace(String imageB64) {
		// TODO Auto-generated method stub
		// 人脸检测
		int state=-2;
		try {
			Testtime();
			HashMap<String, String> map = new HashMap<>();
			map.put("max_face_num", new Integer(2).toString());
			Detect_Baidu=client.detect(imageB64, "BASE64", map);
			if (Detect_Baidu.getInt("error_code") == 0) {
				state = 1;
			} else {
				state = 0;
			}

		} catch (Exception e2) {
			state = -1;
		} finally {
			Testtime();
			return state;
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
	 * @return
	 */
	public static JSONObject getDetect_Baidu() {
		return Detect_Baidu;
	}
}
