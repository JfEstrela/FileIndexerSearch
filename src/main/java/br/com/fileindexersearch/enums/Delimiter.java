package br.com.fileindexersearch.enums;

public enum Delimiter {
	
	COMMA(",");
	
	String delimiter;
	
	
	private Delimiter(String delimiter) {
		this.setDelimiter(delimiter);
	}


	public String getDelimiter() {
		return delimiter;
	}


	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	
	
	

}
