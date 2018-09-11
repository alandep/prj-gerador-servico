package com.br.gerador.prjgeradorservico.model.services.fachada;

import com.br.gerador.prjgeradorservico.model.domain.Dominio;

/**
 * @author alan.franco
 *
 */
public interface GeradorArquivoLiquibaseServiceFachada {

	/**
	 * @param dominio
	 */
	public void gerarArquivoLiquibase(Dominio dominio, Integer ultimoIdLiquibase);

}
