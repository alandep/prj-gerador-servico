package com.br.gerador.prjgeradorservico.model.services.fachada;

import com.br.gerador.prjgeradorservico.model.domain.Dominio;

/**
 * @author alan.franco
 *
 */
public interface IGeradorArquivoRepositoryServiceFachada {

	/**
	 * @param dominio
	 */
	public void gerarArquivoRepository(Dominio dominio);
}
