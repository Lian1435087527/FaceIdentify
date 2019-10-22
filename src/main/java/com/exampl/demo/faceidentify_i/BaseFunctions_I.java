package com.exampl.demo.faceidentify_i;

public interface BaseFunctions_I {
	/**
	 * 检测图片是否有人脸
	 * @param imageB64 输入图片的BASE64字符串
	 * @return 1 检测成功
	 *         0 未检测到人脸
	 *         -1 未知错误
	 */
	public int ISExistFace(String imageB64) ;
}
