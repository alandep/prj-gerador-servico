package com.br.gerador.prjgeradorservico.model.services.fachada;

import com.br.gerador.prjgeradorservico.model.domain.Dominio;

/**
 * @author alan.franco
 *
 */
public interface IGeradorArquivoDomainServiceFachada {

	/**
	 * @param dominio
	 */
	public void gerarArquivoDomain(Dominio dominio);
}
