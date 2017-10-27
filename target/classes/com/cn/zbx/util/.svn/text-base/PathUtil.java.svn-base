package com.cn.zbx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

public class PathUtil {
	
	/**
	 * 上传文件
	 * @param file		路径
	 * @param fileName	文件名
	 */
	public static void uploadFile(File file, String fileName) {
		try {
			if (!file.exists()) { // 如果文件的路径不存在就创建路径
				file.getParentFile().mkdirs();
			}
			uploadFile(new FileInputStream(file), fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件上传
	 * @param file 			上传的文件
	 * @param uploadPath  	上传的文件路径
	 * @param fileName  	上传的文件名称
	 */
	public static void uploadFile(File file, String uploadPath, String fileName) {
		try {
			File uploadFile = new File(uploadPath);
			if (!uploadFile.exists()) { // 如果文件的路径不存在就创建路径
				uploadFile.mkdirs();
			}
			InputStream bis = new FileInputStream(file);
			uploadFile(bis, uploadPath + File.separator + fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根绝项目内文件名称获取路径，并根据时间创建目录
	 * @param request
	 * @param fileName
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static File getPathByProjectPath(HttpServletRequest request, String folderName){
		String filePath =request.getSession().getServletContext().getRealPath(folderName);
		if(filePath == null){
			filePath = request.getRealPath(folderName);  
		}
		File uploadDir = new File(filePath + File.separator + getFilePath());
		if(mkdirs(uploadDir)){
			return uploadDir;
		}else{
			return null;
		}
	}
	
	
	
	@SuppressWarnings("deprecation")
	public static File getPathByProjectPath(HttpServletRequest request, String folderName, String fileName){
		String filePath =request.getSession().getServletContext().getRealPath(folderName);
		if(filePath == null){
			filePath = request.getRealPath(folderName);  
		}
		//TODO 替换getFilePathByDay()实现年月日时分秒文件夹
		File uploadDir = new File(filePath + File.separator + getFilePathByDay() + File.separator +fileName);
		if(mkdirs(uploadDir)){
			return uploadDir;
		}else{
			return null;
		}
	}
	
	public static File getPathByCatalinaHomePath(String fileName){
		File uploadDir = new File(System.getProperty("catalina.home") + File.separator + "files" + File.separator + getFilePathByDay() + File.separator + fileName);
		if(mkdirs(uploadDir)){
			return uploadDir;
		}else{
			return null;
		}
	}
	
	/**
	 * 指定父级目录，根据时间创建文件路径
	 * @param filePath
	 * @return
	 */
	public static File getPathByFilePath(String filePath){
		File uploadDir = new File(filePath + File.separator + getFilePath());
		if(mkdirs(uploadDir)){
			return uploadDir;
		}else{
			return null;
		}
	}
	
	/**
	 * 获取上传文件的目录病创建,指定父级文件,根据文件大小选择系统根目录路径
	 * @param filePath	//父级文件夹
	 * @param size		//文件大小
	 * @return 完整的本地路径
	 */
	public static File getPathByFilePathAndSizeAutoRootPath(String filePath, Long size){
		File uploadDir = new File(getRootPath(size) + File.separator + filePath + File.separator + getFilePath());
		if(mkdirs(uploadDir)){
			return uploadDir;
		}else{
			return null;
		}
	}
	
	/**
	 * 获取项目网络根路径（ip地址:端口号/项目名称）
	 * @param request
	 * @return
	 */
	public static String getUrl(HttpServletRequest request){
		String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		return url;
	}
	
	// 清空文件夹以及文件夹里面的所有文件
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取文件的大小
	 * @param f
	 * @return String 返回类型
	 */
	public static String floatFormart(File f) {
		String str = "0K";
		try {
			NumberFormat numFormat = NumberFormat.getNumberInstance();
			numFormat.setMaximumFractionDigits(2);
			str = numFormat.format(Float.parseFloat(String.valueOf(getFileSizes(f))) / 1024 / 1024) + "M";
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * @param fileName  文件的名称
	 * @return 文件的后缀名(即格式名称)
	 */
    public static String getSuffix(String fileName) {
		if (fileName == null || "".equals(fileName)) {
			return "";
		}
		if (fileName.contains(".")) {
			String[] temp = fileName.split("\\.");
			return temp[temp.length - 1];
		}
		return null;
	}
	
    
    
    
//-----------------------------工具类-------------------------------------------------------------------------------------------------------------------------------------------------	
	
	/**
	 * 获取系统根目录
	 * @param size		//上传文件的大小
	 * @return
	 */
    private static File getRootPath(Long size){
    	
    	size += 1*1024*1024;
    	
        // get system version
        String osName = System.getProperties().getProperty("os.name");
        if(osName == null || osName.length() <=0){
            return null;
        }
        File[] roots = null;
        File path = null ;
        //deal with windows
        if(osName.toLowerCase().indexOf( "windows" ) >= 0){
            roots = File.listRoots(); 
            if(roots.length == 1){
                path = roots[0];
            }else{
                for (File file : roots) {
                	if(!"C".equals(file.getPath().substring(0, 1))){
						System.out.println("file => "+file);
						if(file.getUsableSpace() > size){
							path = file;
							break;
						}
					}
                }
            }
        //deal with linux/unix
        }else if(osName.toLowerCase().indexOf( "linux" ) >= 0 || osName.toLowerCase().indexOf( "unix" ) >= 0){
            roots = File.listRoots();
            if( roots[0].getUsableSpace() > size ){
                path = File.listRoots()[0];
            }
        //unknown system    
        }else{
            System.out.println( "Unkown System Version" );
        }
        return path;
    }
	
	
	/**
	 * 根据时间创建路径
	 * @return
	 */
    @SuppressWarnings("unused")
	private static File getFilePath(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);//获取年份
		int month=cal.get(Calendar.MONTH)+1;//获取月份
		int day=cal.get(Calendar.DATE);//获取日
		int hour=cal.get(Calendar.HOUR_OF_DAY);//小时
		int minute=cal.get(Calendar.MINUTE)%10;//分           
		int second=cal.get(Calendar.SECOND);//秒
		int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK)-1;//周几
		//目录
		File uploadDir = new File(File.separator+"year"+year+File.separator+"month"+month+File.separator+"Week"+WeekOfYear+File.separator+"day"+day+File.separator+"hour"+hour+File.separator+"minute_10_"+minute);
		
		return uploadDir;
	}
    /**
	 * 根据时间创建路径,对应年月日
	 * @return
	 */
    public static File getFilePathByDay(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);//获取年份
		int month=cal.get(Calendar.MONTH)+1;//获取月份
		int day=cal.get(Calendar.DATE);//获取日
		//目录
		File uploadDir = new File(File.separator + "year" + year + File.separator + "month" + month + File.separator + "day" + day);
		
		return uploadDir;
	}
	
	/**
	 * 根据路径创建文件夹
	 * @param uploadDir
	 * @return
	 */
    public static boolean mkdirs(File uploadDir){
		boolean bool = false;
		try {
			if(!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			bool = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	/**
	 * 上传文件
	 * 
	 * @Title: uploadFile
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param in
	 * @param fileName
	 * @return void 返回类型
	 * @author 郝鹏
	 * @date 2013-9-26 下午3:53:33
	 */
    private static void uploadFile(InputStream in, String fileName) {
		if (in == null || fileName == null || fileName.equals("")) {
			return;
		}
		try {
			File uploadFile = new File(fileName);
			if (!uploadFile.exists()) { // 如果文件的路径不存在就创建路径
				uploadFile.getParentFile().mkdirs();
			}
			OutputStream out = new FileOutputStream(fileName);
			byte[] buffer = new byte[2048];
			int temp = 0;
			while ((temp = in.read(buffer)) != -1) {
				out.write(buffer, 0, temp);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除文件夹及下边所有文件和文件夹
	 * @param path
	 * @return
	 */
    private static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 获取文件大小
	 * @param f
	 * @return
	 */
	@SuppressWarnings("resource")
	private static long getFileSizes(File f) throws Exception {// 取得文件大小
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		} else {
			f.createNewFile();
			System.out.println("文件不存在");
		}
		return s;
	}
	
}
