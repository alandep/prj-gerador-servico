package com.br.gerador.prjgeradorservico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.gerador.prjgeradorservico.model.domain.Dominio;
import com.br.gerador.prjgeradorservico.model.services.fachada.GeradorServiceFachada;
import com.google.gson.Gson;

/**
 * @author alan.franco
 *
 */
@RestController
@RequestMapping("/gerador")
public class GeradorController extends BaseController {

	@Autowired
	private GeradorServiceFachada geradorServiceFachada;

	@RequestMapping(value = "/gerar-servico", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String gerarServico(@RequestBody Dominio dominio) {
		Gson gson = new Gson();
		try {
			return gson.toJson(geradorServiceFachada.gerarServico(dominio));
		} catch (Exception e) {
			return gson.toJson(e.getMessage());
		}
	}
}