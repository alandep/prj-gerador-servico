package com.br.gerador.prjservicosgerados.model.services.fachada;

import java.util.List;

import com.br.gerador.prjservicosgerados.model.domain.Alan;

/**
 * @author alan.franco
 *
 */
public interface AlanServiceFachada {

	/**
	 * @return
	 */
	List<Alan> listarTodos() throws Exception;

	/**
	 * @param id
	 * @return
	 */
	Alan buscarPorId(Long id) throws Exception;

	/**
	 * @param gerador
	 * @return
	 */
	Alan salvar(Alan alan) throws Exception;

	/**
	 * @param id
	 * @return
	 */
	Alan editar(Alan alan) throws Exception;

	/**
	 * @param id
	 * @return
	 */
	void deletar(Long id) throws Exception;

}
