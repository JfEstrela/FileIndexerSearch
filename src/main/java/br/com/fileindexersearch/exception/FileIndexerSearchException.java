package br.com.fileindexersearch.exception;

public class FileIndexerSearchException extends Exception {

	private static final long serialVersionUID = 8442497277999660300L;

	public FileIndexerSearchException() {
		super();
	}

	public FileIndexerSearchException(Exception e) {
		super(e);
	}

	public FileIndexerSearchException(String msg,Exception e) {
		super(msg, e);
	}

	public FileIndexerSearchException(String msg) {
		super(msg);
	}

}
