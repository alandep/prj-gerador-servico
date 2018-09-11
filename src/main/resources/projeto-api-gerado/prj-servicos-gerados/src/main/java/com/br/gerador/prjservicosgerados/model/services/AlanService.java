package com.br.gerador.prjservicosgerados.model.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.gerador.prjservicosgerados.model.domain.Alan;
import com.br.gerador.prjservicosgerados.model.domain.RetornoException;
import com.br.gerador.prjservicosgerados.model.repository.AlanRepository;
import com.br.gerador.prjservicosgerados.model.services.fachada.AlanServiceFachada;
import com.google.gson.Gson;

/**
 * @author alan.franco
 *
 */
@Service
public class AlanService extends BaseService implements AlanServiceFachada {

	/**
	 * Injetando as dependências da interface de abstração do spring data
	 * responsável pelo acesso aos dados deste objeto
	 */
	@Autowired
	private AlanRepository geradorRepository;

	private Gson gson;

	private RetornoException retornoException;

	public AlanService() {
		super();
		this.gson = new Gson();
		this.retornoException = new RetornoException(500, "500", "Desculpe o transtorno! Ocorreu um erro interno.");
	}

	@Override
	public List<Alan> listarTodos() throws Exception {
		List<Alan> retorno = new ArrayList<Alan>();
		try {
			retorno = this.geradorRepository.findAll();
		} catch (Exception e) {
			super.LOGGER.error("Ocorreu um erro na classe: AlanService >> listarTodos(): " + e.getMessage());
			throw new Exception(gson.toJson(retornoException));
		}
		return retorno;
	}

	@Override
	public Alan buscarPorId(Long id) throws Exception {
		Optional<Alan> retorno = null;
		try {
			retorno = this.geradorRepository.findById(id);
		} catch (Exception e) {
			super.LOGGER.error("Ocorreu um erro na classe: AlanService >> buscarPorId(Long id): " + e.getMessage());
			throw new Exception(gson.toJson(retornoException));
		}
		return retorno.get();
	}

	@Transactional
	@Override
	public Alan salvar(Alan alan) throws Exception {
		Alan retorno = new Alan();
		try {
			validarCamposObrigatorioSalvar(alan);
			retorno = this.geradorRepository.save(alan);
		} catch (Exception e) {
			super.LOGGER.error(
					"Ocorreu um erro na classe: AlanService >> salvar(ObjetoDominio objetoDominio): " + e.getMessage());
			throw new Exception(gson.toJson(retornoException));
		}
		return retorno;
	}

	/**
	 * @param alan
	 * 
	 *             Método responsável por validar se todos os campos obrigatórios
	 *             foram informados antes da operação de salva
	 * @throws Exception
	 */
	private void validarCamposObrigatorioSalvar(Alan alan) throws Exception {
		StringBuilder camposNaoInformados = new StringBuilder();

		camposNaoInformados.append("[ ");
		if (StringUtils.isBlank(alan.getUrl())) {
			camposNaoInformados.append("url");
		}

		if (StringUtils.isBlank(alan.getCnpj())) {
			camposNaoInformados.append(",");
			camposNaoInformados.append("cnpj");
		}
		camposNaoInformados.append(" ]");

		if (camposNaoInformados.toString().trim().length() > 4) {
			RetornoException retorno = new RetornoException(201, "201",
					MessageFormat.format("Campos obrigatórios não informados: {0}.", camposNaoInformados.toString()));
			throw new Exception(gson.toJson(retorno));
		}
	}

	@Transactional
	@Modifying
	@Override
	public Alan editar(Alan alan) throws Exception {
		Alan retorno = new Alan();
		try {
			retorno = this.geradorRepository.save(alan);
		} catch (Exception e) {
			super.LOGGER.error(
					"Ocorreu um erro na classe: AlanService >> editar(ObjetoDominio objetoDominio): " + e.getMessage());
			throw new Exception(gson.toJson(retornoException));
		}
		return retorno;
	}

	@Transactional
	@Override
	public void deletar(Long id) throws Exception {
		try {
			this.geradorRepository.deleteById(id);
		} catch (Exception e) {
			super.LOGGER.error("Ocorreu um erro na classe: AlanService >> (Long id): " + e.getMessage());
			throw new Exception(gson.toJson(retornoException));
		}
	}

}
