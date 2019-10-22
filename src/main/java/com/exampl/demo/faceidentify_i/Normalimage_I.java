package com.exampl.demo.faceidentify_i;

public interface Normalimage_I {
	/**
	 * 标记出普通RGB图片的人脸框，并返回标记后图片的BASE64编码，默认两人
	 * @param imageB64 输入图片BASE64编码
	 * @return 标记出人脸后图片的BASE64编码
	 */
	public String SignFace(String imageB64) ;
	/**
	 * 标记出普通RGB图片的人脸框，并返回标记后图片的BASE64编码
	 * @param imageB64 输入图片BASE64编码
	* @param num 标记人数上限
	 * @return 标记出人脸后图片的BASE64编码，如果返回0说明出现错误
	 */
	public String SignFace(String imageB64,int num) ;
	/**
	 * 将标记出的人脸存储为图片，默认两人
	 * @param imageB64 输入图片BASE64编码
	 * @param savepath 存储图片路径
	 * @return 返回成功返回的人脸数
	 */
	public int CutoutFace(String imageB64,String savepath);
	public int CutoutFace(String imageB64, String savepath,int num);
	
}
