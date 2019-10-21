package com.exampl.demo.faceidentify;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64.Encoder;


/**
 * 将图片转换为Base64
 * 将base64编码字符串解码成img图片
 * @创建时间 2018-10-16 10:20
 */
public class Img2Base64Util {

 /**
 * @Description： 图片转化成base64字符串
 * @param:    path
 * @Return:
 */
public static String GetImageStr(String path)
{
    //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
    //待处理的图片
    String imgFile = path;
    InputStream in = null;
    byte[] data = null;
    //读取图片字节数组
    try
    {
        in = new FileInputStream(imgFile);
        data = new byte[in.available()];
        in.read(data);
        in.close();
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
    //对字节数组Base64编码
    Encoder encoder = java.util.Base64.getEncoder();
    //返回Base64编码过的字节数组字符串
    return encoder.encodeToString(data);
}
}