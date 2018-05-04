package br.com.fileindexersearch;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.fileindexersearch.enums.Delimiter;
import br.com.fileindexersearch.exception.FileIndexerSearchException;
import br.com.fileindexersearch.service.FileReaderCSVService;
import br.com.fileindexersearch.service.FileReaderService;

public class FileCSVTest {
	
	private FileReaderService fileReaderService;
	private File file;
	private static final Integer ALL_LINES = 5565;
	private static final Integer ALL_UF = 27;
	
	
	@Before
	public void initTest() throws FileIndexerSearchException {
		file = new File("src/test/resources/cidades.csv");
		fileReaderService = new FileReaderCSVService(file, Delimiter.COMMA, Boolean.TRUE);
		fileReaderService.indexFile();
	}
	
	@Test
	public void testCountAllLines() {
		assertTrue(this.fileReaderService.countLinesFile() == ALL_LINES);
	}
	
	@Test
	public void testCountDistinctUF() {
		assertTrue(this.fileReaderService.countDistinctColunm("uf") == ALL_UF);
	}
	
	@Test
	public void testFilterByColunmByValue() {
		assertTrue(this.prossecesLines(this.fileReaderService.filterByColunmByValue("name", "cabixi")));
	}

	private boolean prossecesLines(Map<Integer, String> filterByColunmByValue) {
		return filterByColunmByValue.keySet().contains(1) && filterByColunmByValue.keySet().contains(4);
	}
}
