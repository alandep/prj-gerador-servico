package com.br.gerador.prjgeradorservico.model.services.fachada;

import com.br.gerador.prjgeradorservico.model.domain.Dominio;

/**
 * @author alan.franco
 *
 */
public interface IGeradorServiceFachada {

	/**
	 * @param dominio
	 * @return
	 */
	String gerarServico(Dominio dominio);
}
