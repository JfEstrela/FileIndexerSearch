package br.com.fileindexersearch.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import br.com.fileindexersearch.enums.Delimiter;
import br.com.fileindexersearch.enums.Encode;
import br.com.fileindexersearch.enums.Extension;
import br.com.fileindexersearch.exception.FileIndexerSearchException;
import br.com.fileindexersearch.util.ReadFile;

public  class FileReaderCSVService extends FileReaderService{
	
	public FileReaderCSVService() {}
	
	public FileReaderCSVService(File file,Delimiter fileDelimiter,Boolean searchUperCase) throws FileIndexerSearchException {
		super(file, fileDelimiter,Extension.CSV,searchUperCase);
	}
	
	@Override
	public void indexFile() {
		this.readHeader();
		this.readBodyFile();	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int countLinesFile() {
		List<String> list = (List<String>) getFileDate().values().toArray()[0];
		return list.size();
		
	}
	
	@Override
	public int countDistinctColunm(String colunm) {
		List<String> valuesList = this.getFileDate().get(this.isSearchUperCase() ? colunm.toUpperCase() : colunm);
		if(valuesList == null) {
			return -1;
		}
		Set<String> valuesColunm = new HashSet<>(valuesList);
		return valuesColunm.size();
		
	}
	
	@Override
	public Map<Integer,String> filterByColunmByValue(String colunm, String value) {
		Map<Integer,String> mapLinhas = new HashMap<>();
		String keyColunmLineValues = this.isSearchUperCase() ? colunm.concat(value).toUpperCase() : colunm.concat(value);
		int indexColunm = this.getIndexColunm(this.isSearchUperCase() ? colunm.toUpperCase() : colunm);
		if(indexColunm == -1) {			
			return mapLinhas;
		}
		List<Integer> listLine = this.getListColunmLineValues().get(this.getIndexColunm(this.isSearchUperCase() ? colunm.toUpperCase() : colunm)).get(keyColunmLineValues);
		if(listLine == null) {			
			return mapLinhas;
		}
		mapLinhas.put(1, this.getFileHeader().values().toString());
		for(Integer line : listLine) {
			mapLinhas.put(line+2, Arrays.asList(this.getLines().get(line)).toString());
		}
		return mapLinhas;
	}

	private int getIndexColunm(String colunm) {
		 for (Entry<Integer, String> entry : this.getFileHeader().entrySet()) {
		        if (colunm.equals(this.isSearchUperCase() ? entry.getValue().toUpperCase() : entry.getValue())) {
		           return entry.getKey();
		        }
		    }
		return -1;
	}

	private void readBodyFile() {
		this.indexBody(ReadFile.readLineFileStartIn(getFile().getAbsolutePath(), this.getFileDelimiter().getDelimiter(), 1,Encode.UTF8.name()));	
	}

	private void indexBody(List<String[]> readLineFileStartIn) {
		int lineIndex = 0;
		for(String[] line : readLineFileStartIn) {
			this.organizeColumnHeader(line,lineIndex);
			this.getLines().add(line);
			lineIndex++;
		}		
	}

	private void organizeColumnHeader(String[] line,int lineIndex) {
		for(int i = 0 ; i < line.length ; i++) {
			if(this.getFileDate().get(this.isSearchUperCase() ? this.getFileHeader().get(i).toUpperCase() :this.getFileHeader().get(i) ) == null) {
				this.getFileDate().put(this.isSearchUperCase() ? this.getFileHeader().get(i).toUpperCase():this.getFileHeader().get(i), new ArrayList<>());
			}
			this.getFileDate().get(this.isSearchUperCase() ? this.getFileHeader().get(i).toUpperCase():this.getFileHeader().get(i)).add(line[i]);
			String keyColunmLineValues = this.getKeyColunmLineValues(line[i],i);
			if(this.getListColunmLineValues().get(i).get(this.isSearchUperCase() ? keyColunmLineValues.toUpperCase() :keyColunmLineValues) == null) {
				this.getListColunmLineValues().get(i).put(this.isSearchUperCase() ? keyColunmLineValues.toUpperCase() :keyColunmLineValues, new ArrayList<>());			
			}
			this.getListColunmLineValues().get(i).get(this.isSearchUperCase() ? keyColunmLineValues.toUpperCase() :keyColunmLineValues).add(lineIndex);
		}
	}
	
	private String getKeyColunmLineValues(String value, Integer colunmIndex) {
		return this.getFileHeader().get(colunmIndex).concat(value);
	}

	private void readHeader() {
		List<String> line = Arrays.asList(ReadFile.readLineFile(getFile().getAbsolutePath(),this.getFileDelimiter().getDelimiter()));
		int index = 0;
		Map<Integer,String> mapHeader = new HashMap<Integer,String>();
		for(String column :line) {
			mapHeader.put(index++, column);
			this.getListColunmLineValues().add(new HashMap<>());
		}
		this.setFileHeader(mapHeader);		
	}

}
