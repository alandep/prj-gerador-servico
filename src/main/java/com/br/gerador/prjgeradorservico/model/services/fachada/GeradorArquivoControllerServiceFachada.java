package com.br.gerador.prjgeradorservico.model.services.fachada;

import com.br.gerador.prjgeradorservico.model.domain.Dominio;

/**
 * @author alan.franco
 *
 */
public interface GeradorArquivoControllerServiceFachada {

	/**
	 * @param dominio
	 */
	public void gerarArquivoController(Dominio dominio);
}
