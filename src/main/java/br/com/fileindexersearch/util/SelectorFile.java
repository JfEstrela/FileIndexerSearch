package br.com.fileindexersearch.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class SelectorFile {
	
	private SelectorFile() {};
	
	 public static File selectCsvFile() {
	        JFileChooser chooser = new JFileChooser();
	        chooser.setFileFilter(new FileNameExtensionFilter("csv file", "csv"));
	        int returnVal = chooser.showOpenDialog(null);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            return chooser.getSelectedFile();
	        }
	        return null;
	    }

}
