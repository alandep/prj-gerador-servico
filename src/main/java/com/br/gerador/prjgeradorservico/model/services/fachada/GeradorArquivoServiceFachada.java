package com.br.gerador.prjgeradorservico.model.services.fachada;

import com.br.gerador.prjgeradorservico.model.domain.Dominio;

/**
 * @author alan.franco
 *
 */
public interface GeradorArquivoServiceFachada {

	/**
	 * @param dominio
	 */
	public void gerarArquivoService(Dominio dominio);
}
