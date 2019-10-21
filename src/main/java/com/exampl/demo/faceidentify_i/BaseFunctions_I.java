package com.exampl.demo.faceidentify_i;

public interface BaseFunctions_I {
	/**
	 * ���ͼƬ�Ƿ�������
	 * @param imageB64 ����ͼƬ��BASE64�ַ���
	 * @return 1 ���ɹ�
	 *         0 δ��⵽����
	 *         -1 δ֪����
	 */
	public int ISExistFace(String imageB64) ;
}
