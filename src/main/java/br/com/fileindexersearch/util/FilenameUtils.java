package br.com.fileindexersearch.util;

import java.io.File;

public final class FilenameUtils {
	
	private FilenameUtils() {};
	
	 public static String getFileExtension(File file) {
	        String fileName = file.getName();
	        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
	        return fileName.substring(fileName.lastIndexOf(".")+1);
	        else return "";
	    }

}
