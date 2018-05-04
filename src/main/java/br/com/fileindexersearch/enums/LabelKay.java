package br.com.fileindexersearch.enums;

public enum LabelKay {
	
	ARCHIVE_REPORT("label.informe.caminho.arquivo"),//
	COMMAND_INTERPRETED("label.informe.comando.ser.interpretado"),//
	TOTAL_LINE("label.total.linha"),//
	NOT_EXIST_COLUNM("label.coluna.nao.existe.aquivo"),//
	TOTAL_VALUE_COLUNM_DISTINCT("label.total.valores.diferente.coluna"),//
	TOTAL_LINE_COLUNM_VALUE("label.total.linhas.coluna.valor"),//
	ENDING_PROGRAM("label.finalizando.programa"),//
	COMAND_INVALID("label.comando.invalido"),//
	COLUNM_VALUE_NOT_EXIST("label.coluna.valor.nao.existe.arquivo"),//
	LINE("label.line"),//
	HEADER("label.header"),//
	PATH_NOT_NULL("label.caminho.arquivo.nao.pode.ser.nulo"),//
	SEARCH_SENSITIVE("label.pesquisa.sensiteve");
	 
	private String key;
	
	private LabelKay(String key) {
		this.setKey(key);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	

}
