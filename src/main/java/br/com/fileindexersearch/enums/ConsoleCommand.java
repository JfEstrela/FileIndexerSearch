package br.com.fileindexersearch.enums;

public enum ConsoleCommand {
	COUNT_ALL("count*","escreve no console a contagem total de registros importados (não considera a linha de cabeçalho)."),//
	COUNT_DISTINCT("count distinct[colunm]","escreve no console o total de valores distintos da propriedade (coluna) enviada."),//
	FILTER_KEY_VALUE("filter [colunm][value]","escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada possua o valor enviado."),
	EXIT("exit","Sair.");
	
	private String command;
	private String description;
	
	private ConsoleCommand(String command,String description) {
		this.setCommand(command);
		this.setDescription(description);
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static ConsoleCommand getByCommand(String command) {
		for(ConsoleCommand cmd :ConsoleCommand.values()) {
			if(cmd.getCommand().equals(command)) {
				return cmd;
			}
		}
		return null;
	}

	
}
