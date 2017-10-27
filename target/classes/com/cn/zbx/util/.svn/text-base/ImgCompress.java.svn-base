package com.cn.zbx.util;  
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/** 
 * 图片压缩处理 
 * @author zyw
 */  
@SuppressWarnings("restriction")
public class ImgCompress {  
	
	/**
	 * 根据宽等比例缩放
	 * @param sourcePath
	 * @param fileSize
	 * @throws IOException
	 */
	public static void resizeByWidth(String path, int width) throws IOException{
		BufferedImage image = ImageIO.read(new File(path));
		if(null != image){
			resizeByWidth(image,width, pathAddStr(path, "_" + width));
		}
	}
	
	/**
	 * 根据高等比例缩放
	 * @param sourcePath
	 * @param fileSize
	 * @throws IOException
	 */
	public static void resizeByHeight(String path, int height) throws IOException{
		BufferedImage image = ImageIO.read(new File(path));
		if(null != image){
			resizeByHeight(image,height, pathAddStr(path, "_" + height));  
		}
	}
	
	/**
	 * 固定宽高的缩略图
	 * @param path
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	public static void resize(String path, int width, int height) throws IOException{
		BufferedImage image = ImageIO.read(new File(path));
		if(null != image){
			resize(image,width, height, pathAddStr(path, "_" + width + "_" + height));  
		}
	}
	
	/**
	 * 判断宽高,进行等比例缩放(此方法自动判断缩放,不推荐使用)
	 * @param path
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	public static void resizeFix(String path, int width, int height) throws IOException{
		BufferedImage image = ImageIO.read(new File(path));
		if(null != image){
			resizeFix(image,width, height, pathAddStr(path, "_" + width + "_" + height));  
		}
	}
	
    public static void main(String[] args) throws Exception { 
    	long a = System.currentTimeMillis();
        Image img = ImageIO.read(new File("D:\\2.jpg"));
        ImgCompress.resizeByWidth(img, 360, "D:\\3.jpg");  
        long b = System.currentTimeMillis();
        System.out.println(b - a);
    }  
    
    /** 
     * 按照宽度还是高度进行压缩 
     * @param w int 最大宽度 
     * @param h int 最大高度 
     */  
    private static void resizeFix(Image img, int w, int h, String savePath) throws IOException { 
        int width = img.getWidth(null);    // 得到源图宽  
        int height = img.getHeight(null);  // 得到源图长  
        if (width / height > w / h) {  
            resizeByWidth(img, w, savePath);  
        } else {  
            resizeByHeight(img, h, savePath);  
        }  
    }  
    /** 
     * 以宽度为基准，等比例放缩图片 
     * @param w int 新宽度 
     */  
    private static void resizeByWidth(Image img,int w, String savePath) throws IOException {  
    	int width = img.getWidth(null);    // 得到源图宽  
        int height = img.getHeight(null);  // 得到源图长  
        int h = (int) (height * w / width);  
        resize(img, w, h, savePath);  
    }  
    /** 
     * 以高度为基准，等比例缩放图片 
     * @param h int 新高度 
     */  
    private static void resizeByHeight(Image img ,int h, String savePath) throws IOException {
    	int width = img.getWidth(null);    // 得到源图宽  
        int height = img.getHeight(null);  // 得到源图长  
        int w = (int) (width * h / height);  
        resize(img,w, h, savePath);  
    }  
    /** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */  
    private static void resize(Image img ,int w, int h, String savePath) throws IOException {  
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
        File destFile = new File(savePath);  
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
        // 可以正常实现bmp、png、gif转jpg  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        encoder.encode(image); // JPEG编码  
        out.close();  
    }  
    public static String pathAddStr(String pathStr, String str){
		String savePath = pathStr.substring(0,pathStr.length()-4) + str + ".jpg";
    	return savePath;
    }
}  