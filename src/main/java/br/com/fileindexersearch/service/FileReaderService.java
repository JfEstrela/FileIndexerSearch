package br.com.fileindexersearch.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.fileindexersearch.enums.Delimiter;
import br.com.fileindexersearch.enums.Extension;
import br.com.fileindexersearch.exception.FileIndexerSearchException;
import br.com.fileindexersearch.util.FilenameUtils;

public abstract class FileReaderService {
	
	private Map<String,List<String>> fileDate = new HashMap<>();
	private Map<Integer,String> fileHeader = new HashMap<>();
	private List<Map<String,List<Integer>>> listColunmLineValues = new ArrayList<>();
	private List<String[]> lines = new ArrayList<>();
	private File file;
	private Delimiter fileDelimiter;
	private Extension fileExtension;
	private Boolean searchUperCase;
	
	public abstract void indexFile();
	public abstract int countLinesFile();
	public abstract int countDistinctColunm(String colunm);
	public abstract Map<Integer,String> filterByColunmByValue(String colunm,String value);
	
	public FileReaderService() {}
	public FileReaderService(File file,Delimiter fileDelimiter,Extension fileExtension,Boolean searchUperCase)throws FileIndexerSearchException {
		this.setFile(file);
		this.setFileDelimiter(fileDelimiter);
		this.setFileExtension(fileExtension);
		this.isFileValid();
		this.setSearchUperCase(searchUperCase);
	}
	

	private void isFileValid() throws FileIndexerSearchException {
		if(!this.getFile().exists()) {
			throw new FileIndexerSearchException("O arquivo '"+this.getFile().getAbsolutePath()+ "' não foi encontrado.");
		}if(!this.getFile().isFile()) {
			throw new FileIndexerSearchException("O diretorio '"+this.getFile().getAbsolutePath()+"' informado não contem um arquivo valido.");
		}else if(!FilenameUtils.getFileExtension(this.getFile()).equalsIgnoreCase(this.getFileExtension().name())) {
			throw new FileIndexerSearchException("Extensão do arquivo invalida. Extensão valida: ".concat(this.getFileExtension().name()));
		}
		
	}
	public Map<String, List<String>> getFileDate() {
		return fileDate;
	}

	public void setFileDate(Map<String, List<String>> fileDate) {
		this.fileDate = fileDate;
	}

	public Map<Integer, String> getFileHeader() {
		return fileHeader;
	}

	public void setFileHeader(Map<Integer, String> fileHeader) {
		this.fileHeader = fileHeader;
	}

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	public List<Map<String,List<Integer>>> getListColunmLineValues() {
		return listColunmLineValues;
	}
	
	public void setListColunmLineValues(List<Map<String,List<Integer>>> listColunmLineValues) {
		this.listColunmLineValues = listColunmLineValues;
	}
	
	public List<String[]> getLines() {
		return lines;
	}
	
	public void setLines(List<String[]> lines) {
		this.lines = lines;
	}
	
	public Delimiter getFileDelimiter() {
		return fileDelimiter;
	}
	
	public void setFileDelimiter(Delimiter fileDelimiter) {
		this.fileDelimiter = fileDelimiter;
	}
	
	public Extension getFileExtension() {
		return fileExtension;
	}
	
	public void setFileExtension(Extension fileExtension) {
		this.fileExtension = fileExtension;
	}
	public Boolean isSearchUperCase() {
		return searchUperCase;
	}
	public void setSearchUperCase(Boolean searchUperCase) {
		this.searchUperCase = searchUperCase;
	}
	
	
	
	
	
}
