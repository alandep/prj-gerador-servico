package com.br.gerador.prjgeradorservico.enums;

/**
 * @author alan.franco
 *
 */
public enum DiretorioArquivosEnum {

	DIRETORIO_CONTROLLER("projeto-api-gerado/prj-servicos-gerados/src/main/java/com/br/gerador/prjservicosgerados/controller/"), 
	DIRETORIO_DOMAIN("projeto-api-gerado/prj-servicos-gerados/src/main/java/com/br/gerador/prjservicosgerados/model/domain/"), 
	DIRETORIO_REPOSITORY("projeto-api-gerado/prj-servicos-gerados/src/main/java/com/br/gerador/prjservicosgerados/model/repository/"),
	DIRETORIO_SERVICES("projeto-api-gerado/prj-servicos-gerados/src/main/java/com/br/gerador/prjservicosgerados/model/services/"),
	DIRETORIO_LIQUIBASE("projeto-api-gerado/prj-servicos-gerados/src/main/resources/db/changelog/"),	                     
	DIRETORIO_SERVICES_FACHADA("projeto-api-gerado/prj-servicos-gerados/src/main/java/com/br/gerador/prjservicosgerados/model/services/fachada/");

	private String diretorioArquivo;

	DiretorioArquivosEnum(String diretorioArquivo) {
		this.diretorioArquivo = diretorioArquivo;
	}

	public String getDiretorioArquivo() {
		return diretorioArquivo;
	}
	
}
