package com.exampl.demo.faceidentify_i;

public interface Normalimage_I {
	/**
	 * ��ǳ���ͨRGBͼƬ�������򣬲����ر�Ǻ�ͼƬ��BASE64���룬Ĭ������
	 * @param imageB64 ����ͼƬBASE64����
	 * @return ��ǳ�������ͼƬ��BASE64����
	 */
	public String SignFace(String imageB64) ;
	/**
	 * ��ǳ���ͨRGBͼƬ�������򣬲����ر�Ǻ�ͼƬ��BASE64����
	 * @param imageB64 ����ͼƬBASE64����
	* @param num �����������
	 * @return ��ǳ�������ͼƬ��BASE64���룬�������0˵�����ִ���
	 */
	public String SignFace(String imageB64,int num) ;
	/**
	 * ����ǳ��������洢ΪͼƬ��Ĭ������
	 * @param imageB64 ����ͼƬBASE64����
	 * @param savepath �洢ͼƬ·��
	 * @return ���سɹ����ص�������
	 */
	public int CutoutFace(String imageB64,String savepath);
	public int CutoutFace(String imageB64, String savepath,int num);
	
}
