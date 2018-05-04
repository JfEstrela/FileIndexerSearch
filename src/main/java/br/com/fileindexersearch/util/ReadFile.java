package br.com.fileindexersearch.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ReadFile {
	
	private static final Logger LOGGER =  Logger.getLogger(ReadFile.class.getName());
	
	public ReadFile() {};
	
	public static String[] readLineFile(String path,String delimiter){
		String line = "";
		String[] lineData = null;
		 try (BufferedReader br = new BufferedReader(new FileReader(path))) {
	            if ((line = br.readLine()) != null) {
	                lineData = line.split(delimiter);
	            }

	        } catch (IOException e) {
	        	LOGGER.log(Level.SEVERE, e.getMessage());
	        }
		 return lineData;

	}
	public static List<String[]>readLineFileStartIn(String path,String delimiter,int startIndex,String encode){
		String line = "";
		List<String[]>listLineData = new ArrayList<>();
		int lineIndex = 0;
		 try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), encode))) {
	            while ((line = br.readLine()) != null ) {
	            	if(lineIndex++ >=startIndex) {            		
	            		listLineData.add(line.split(delimiter));
	            	}
	            	
	            }

	        } catch (IOException e) {
	        	LOGGER.log(Level.SEVERE, e.getMessage());
	        }
		 return listLineData;

	}

}
