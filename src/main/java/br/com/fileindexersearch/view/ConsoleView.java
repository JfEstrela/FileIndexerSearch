package br.com.fileindexersearch.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.fileindexersearch.enums.ConsoleCommand;
import br.com.fileindexersearch.enums.Delimiter;
import br.com.fileindexersearch.enums.LabelKay;
import br.com.fileindexersearch.exception.FileIndexerSearchException;
import br.com.fileindexersearch.factory.SingletonServiceFactory;
import br.com.fileindexersearch.i18n.I18nUtil;
import br.com.fileindexersearch.service.FileReaderService;

public class ConsoleView {
	
	private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
	private FileReaderService fileReaderService;
	private  String clazz;
	private Delimiter delimiter;
	private String path;
	private String colunm;
	private String value;
	
	public ConsoleView(String clazz,Delimiter delimiter) {
		this.setClazz(clazz);
		this.setDelimiter(delimiter);
	}

	public void run() {

		try {
			this.writeOptions();
			System.out.println(I18nUtil.getTextoInternacionalizado(LabelKay.ARCHIVE_REPORT.getKey()));
			this.readFile();
			this.readOptions();
		} catch (FileIndexerSearchException | IOException e) {
			System.out.println(e.getMessage());
			this.setPath(null);
			this.run();
		}
	}

	private void readOptions() throws IOException {
		String command = "";
		do {
			System.out.println(I18nUtil.getTextoInternacionalizado(LabelKay.COMMAND_INTERPRETED.getKey()));
			command = IN.readLine();
			
			command = this.extractValuesCommand(command);
			if(command == null || !this.validCommand(command)) {
				System.out.println(I18nUtil.getTextoInternacionalizado(LabelKay.COMAND_INVALID.getKey()) );
				this.writeOptions();
				continue;
			}
			this.runCommand(command);
			if(ConsoleCommand.EXIT.getCommand().equals(command)) {
				break;
			}
			this.writeOptions();
		}while(true);
	}

	private void runCommand(String command) {
		ConsoleCommand cmd = ConsoleCommand.getByCommand(command);		
		switch ( cmd) {
		case COUNT_ALL:
			System.out.println(I18nUtil.getTextoInternacionalizado(LabelKay.TOTAL_LINE.getKey(), fileReaderService.countLinesFile()+""));
			break;
		case COUNT_DISTINCT:
			int count = fileReaderService.countDistinctColunm(colunm);
			System.out.println( count== -1 ? I18nUtil.getTextoInternacionalizado(LabelKay.NOT_EXIST_COLUNM.getKey(), colunm,this.fileReaderService.getFile().getName()):I18nUtil.getTextoInternacionalizado(LabelKay.TOTAL_VALUE_COLUNM_DISTINCT.getKey(), colunm,count+"") );
			break;
		case FILTER_KEY_VALUE:			
			this.processaLines(fileReaderService.filterByColunmByValue(colunm,value));
			break;
		case EXIT:
			System.out.println(I18nUtil.getTextoInternacionalizado(LabelKay.ENDING_PROGRAM.getKey()));
			break;	
		default	:
			System.out.println(I18nUtil.getTextoInternacionalizado(LabelKay.COMAND_INVALID.getKey()) );
			break;
		}
		
	}

	private void processaLines(Map<Integer, String> filterByColunmByValue) {
		if(filterByColunmByValue.isEmpty() ) {
			System.out.println(I18nUtil.getTextoInternacionalizado(LabelKay.COLUNM_VALUE_NOT_EXIST.getKey(),colunm,value,this.fileReaderService.getFile().getName()));
			return;
		}
		System.out.println(I18nUtil.getTextoInternacionalizado(LabelKay.TOTAL_LINE_COLUNM_VALUE.getKey(), colunm,value));
		for(Map.Entry<Integer, String> entry : filterByColunmByValue.entrySet()) {
			if(entry.getKey().equals(1)) {
				System.out.println(I18nUtil.getTextoInternacionalizado(LabelKay.HEADER.getKey(), entry.getKey()+entry.getValue()));
				continue;
			}
			System.out.println(I18nUtil.getTextoInternacionalizado(LabelKay.LINE.getKey(),entry.getKey()+entry.getValue()) );
		}
	
	}

	private String extractValuesCommand(String command) {
		this.colunm = null;
		this.value = null;
		Pattern patter = Pattern.compile("\\[(.*?)\\]");
		Matcher matcher = patter.matcher(command);
		if (matcher.find()) {
			colunm = matcher.group(1);
		}
		if (matcher.find()) {
			value = matcher.group(1);
		}
		if(colunm != null) {
			command = command.replace(colunm, "colunm");
		}
		if(value != null) {
			command = command.replace(value, "value");
		}
		return command;
	}

	private boolean validCommand(String command) {
		if(ConsoleCommand.getByCommand(command) == null) {
			return false;
		}
		return true;
		
	}

	@SuppressWarnings("static-access")
	private void readFile() throws IOException, FileIndexerSearchException {
		File file = null;
		path = IN.readLine();
		if(path == null) {
			System.out.println(I18nUtil.getTextoInternacionalizado(LabelKay.PATH_NOT_NULL.getKey()));
			System.exit(0);
		}
		boolean caseSensitive = this.caseSensitiveSearch();
		file = new File(path);	
		fileReaderService =SingletonServiceFactory.getServiceFactory().getFileService(clazz, file, this.getDelimiter(),caseSensitive);
		fileReaderService.indexFile();
	}
	
	private boolean caseSensitiveSearch() throws IOException {
		System.out.println(I18nUtil.getTextoInternacionalizado(LabelKay.SEARCH_SENSITIVE.getKey()));
		return "S".equalsIgnoreCase(IN.readLine());
	}

	public void writeOptions() {
		System.out.println("____________________________________________________________________________________________________________");
		for(ConsoleCommand cmd : ConsoleCommand.values()) {
			System.out.println("|"+cmd.getCommand().concat(": ").concat(cmd.getDescription()));
		}
		System.out.println("|____________________________________________________________________________________________________________");
				
	}

	public  String getClazz() {
		return clazz;
	}
 
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public Delimiter getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(Delimiter delimiter) {
		this.delimiter = delimiter;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


}
