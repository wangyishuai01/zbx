package com.cn.zbx.util;

import java.util.UUID;

public class UUIDUtil {
	public static String getUUID(){
		return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
	}
}
