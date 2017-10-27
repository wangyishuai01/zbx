package com.cn.zbx.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.zbx.commons.Constants;

public class WriteLogUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WriteLogUtil.class);
	
	
	/**
	 * 测试使用的main方法
	 */
	public static void main(String[] args) {
//		String property = System.getProperty("catalina.home");
//		String rootPath = "D:\\logs\\" ;
//		// getFileName();
//		String testString = "写日志：9F79C21D14D8C09905BC52CF1C8249B12F2EDE5AC3C8FAF2FD8A686E";
//		System.out.println(writeLogMsgToFile(testString, rootPath, "ccc"));
		// delOldFile();
	}	
	
	
	
	/**
	 * 写入订单操作日志
	 * @param msg
	 * @return
	 */
	public static boolean writeLogMsgToFile_order(String msg){
		String tomcatRootPath = WriteLogUtil.getTomcatRootPath();
		String log_text = "[" + msg + "] :" + msg;
		return writeLogMsgToFile(log_text, tomcatRootPath, "");
	}
	
	/**
	 * 将信息写到文件中()
	 * @param msg			日志信息(需自定义格式,自动换行)
	 * @param rootPath		文件创建位置(自动根据天创建文件夹)
	 * @param fileName		文件名称(自动加后缀名 .log)
	 */
	public static boolean writeLogMsgToFile(String msg, String rootPath, String fileName) {
		// 删除之前的文件
		// delOldFile();
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(getFileName(rootPath + PathUtil.getFilePathByDay().toString(),fileName + ".log"), true);
			fileWriter.write(msg + "\r\n");
			fileWriter.flush();
		} catch (IOException e) {
			LOGGER.error("流程日志写入失败 msg:{},e:{}",msg,e.toString());
		} finally {
			try {
				fileWriter.close();
				return true;
			} catch (IOException e) {
				LOGGER.error("流程日志写入失败 msg:{},e:{}",msg,e.toString());
			}
		}
		return false;
	}

	/**
	 * 删除之前的日志文件
	 */
	public static void delOldFile(String rootPath) {
		String delPath = rootPath + "\\";
		File folder = new File(delPath);
		if (folder.exists()) {
			File[] files = folder.listFiles();
			for (int i = 0; i < files.length; i++) {
				files[i].delete();
			}
		}
	}

	/**
	 * 获取要保存的文件
	 * 
	 * @return fileName
	 */
	private static String getFileName(String rootPath, String fileName) {
		// 创建目录
		File folder = new File(rootPath + "\\");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		// 创建文件
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				LOGGER.error("流程日志写入失败 rootPath:{},fileName:{},e:{}",rootPath,fileName,e.toString());
			}
		}
		fileName = rootPath + "\\" + fileName;
		return fileName;
	}

	public static String getTomcatRootPath(){
		String property = System.getProperty("catalina.home");
		return property;
	}
}