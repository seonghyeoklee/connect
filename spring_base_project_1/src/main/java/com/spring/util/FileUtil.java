package com.spring.util;

public class FileUtil {

	public final static String USER_PROFILE_IMAGE_FOLDER_PATH = "C:\\upload\\profileImage";

	public static boolean isJpg(byte[] b) {
		return (b[0]&0xff) == 0xff && (b[1]&0xff) == 0xd8 && (b[2]&0xff) == 0xff &&
				( (b[3]&0xff) == 0xe0 || (b[3]&0xff) == 0xe1 || (b[3]&0xff) == 0xe2 || (b[3]&0xff) == 0xe8 );
	}

	public static boolean isPng(byte[] b) {
		return (b[0]&0xff) == 0x89 && (b[1]&0xff) == 0x50 && (b[2]&0xff) == 0x4e && (b[3]&0xff) == 0x47;
	}

	public static boolean isGif(byte[] b) {
		return (b[0]&0xff) == 0x47 && (b[1]&0xff) == 0x49 && (b[2]&0xff) == 0x46 && (b[3]&0xff) == 0x38 &&
				( (b[4]&0xff) == 0x37 || (b[4]&0xff) == 0x39 ) && (b[5]&0xff) == 0x61;
	}
}
