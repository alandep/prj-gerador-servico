package com.br.gerador.prjgeradorservico.enums;

/**
 * @author alan.franco
 *
 */
public enum TipoDeDadoEnum {

	STRING("String"),
	INTEGER("Integer"),
	LONG("Long"),
	DOUBLE("Double"),
	BOOLEAN("Boolean"),
	DATE("Date");

	private String descricao;

	/**
	 * @param descricao
	 */
	TipoDeDadoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * @return
	 */
	public String getDescricao() {
		return descricao;
	}
}