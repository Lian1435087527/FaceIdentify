package com.exampl.demo.codeTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.exampl.demo.baiduapi.FileUtil;

import com.exampl.demo.faceidentify_i.BaseFunctions_I;
import com.exampl.demo.faceidentify_i.Normalimage_I;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionClient;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionManager;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.FaceDescription;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ImageAnalysis;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ImageCaption;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.VisualFeatureTypes;

public class TestComputerVision {
	/*public static void main(String[] arg0) {
		String image=Img2Base64Util.GetImageStr("E:\\��ѧ\\ѧϰ\\Ԧ��ʵϰ\\����ͼƬ\\2b365aedb6d48100.jpg");
		//BaseFunctions_I temp=new BaseFunctions();
		//System.out.println(temp.ISExistFace(image));
		Normalimage_I A=new Normalimage();
		System.out.println(A.CutoutFace(image,"E:\\��ѧ\\ѧϰ\\Ԧ��ʵϰ\\����ͼƬ\\"));
	}*/
	public static void main(String[] args) {
	    // Add your Computer Vision subscription key and endpoint to your environment
	    // variables.
	    // After setting, close and then re-open your command shell or project for the
	    // changes to take effect.
	    String subscriptionKey = System.getenv("COMPUTER_VISION_SUBSCRIPTION_KEY");
	    String endpoint = System.getenv("COMPUTER_VISION_ENDPOINT");
	    ComputerVisionClient compVisClient = ComputerVisionManager.authenticate(subscriptionKey).withEndpoint(endpoint);
	 // END - Create an authenticated Computer Vision client.

	 System.out.println("\nAzure Cognitive Services Computer Vision - Java Quickstart Sample");

	 // Analyze local and remote images
	 AnalyzeLocalImage(compVisClient);

	 // Recognize printed text with OCR for a local and remote (URL) image
	 //RecognizeTextOCRLocal(compVisClient);
	}

	@SuppressWarnings("deprecation")
	public static void AnalyzeLocalImage(ComputerVisionClient compVisClient) {
	    /*
	     * Analyze a local image:
	     *
	     * Set a string variable equal to the path of a local image. The image path
	     * below is a relative path.
	     */
	    String pathToLocalImage = "E:\\大学\\学习\\驭光实习\\测试图片\\2b365aedb6d48100.jpg";
	 // This list defines the features to be extracted from the image.
	    List<VisualFeatureTypes> featuresToExtractFromLocalImage = new ArrayList<>();
	    featuresToExtractFromLocalImage.add(VisualFeatureTypes.FACES);
	 // Need a byte array for analyzing a local image.
	    File rawImage = new File(pathToLocalImage);
	    byte[] imageByteArray;
		try {
			imageByteArray = Files.readAllBytes(rawImage.toPath());
			 // Call the Computer Vision service and tell it to analyze the loaded image.
			Date date = new Date();

			System.out.println("->" + date.getMinutes()
					+ ":" + date.getSeconds());
		    ImageAnalysis analysis = compVisClient.computerVision().analyzeImageInStream().withImage(imageByteArray)
		            .withVisualFeatures(featuresToExtractFromLocalImage).execute();
		    Date date2 = new Date();
		    System.out.println("->" + date2.getMinutes()
			+ ":" + date2.getSeconds());
		 // Display image captions and confidence values.
		    System.out.println("\nCaptions: ");
		    for (FaceDescription face : analysis.faces()) {
		        System.out.printf("\'%s\' of age %d at location (%d, %d), (%d, %d)\n", face.gender(), face.age(),
		                face.faceRectangle().left(), face.faceRectangle().top(),
		                face.faceRectangle().left() + face.faceRectangle().width(),
		                face.faceRectangle().top() + face.faceRectangle().height());
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   
	}
	private int times = 0;

	/**
	 * 测试运行时间 时间戳
	 */
	@SuppressWarnings("deprecation")
	private void Testtime() {
		Date date = new Date();
		times++;
		System.out.println(this.getClass().getName() + " " + new Integer(times).toString() + "->" + date.getMinutes()
				+ ":" + date.getSeconds());
	}
}
