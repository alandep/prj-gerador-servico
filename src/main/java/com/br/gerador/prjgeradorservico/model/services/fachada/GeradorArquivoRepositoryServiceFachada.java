package com.br.gerador.prjgeradorservico.model.services.fachada;

import com.br.gerador.prjgeradorservico.model.domain.Dominio;

/**
 * @author alan.franco
 *
 */
public interface GeradorArquivoRepositoryServiceFachada {

	/**
	 * @param dominio
	 */
	public void gerarArquivoRepository(Dominio dominio);
}
