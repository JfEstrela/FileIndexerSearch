package br.com.fileindexersearch;

import java.io.IOException;

import br.com.fileindexersearch.enums.Delimiter;
import br.com.fileindexersearch.service.FileReaderCSVService;
import br.com.fileindexersearch.view.ConsoleView;

public class PrincipalClass {
	

	public static void main(String[] args) throws IOException {
		new ConsoleView(FileReaderCSVService.class.getName(),Delimiter.COMMA).run();
	}

}
