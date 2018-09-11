package com.br.gerador.prjgeradorservico.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author alan.franco
 *
 *         Filtro criado para mitigar cross domain:
 *
 *         Este filtro não reduz o nível de segurança desta aplicação pois os
 *         serviços expostos possuem controle de requisição por token JST
 */
public class CORSFilter extends OncePerRequestFilter {

	private final Logger LOG = LoggerFactory.getLogger(CORSFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		LOG.info("Adding CORS Headers ........................");
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		res.setHeader("Access-Control-Allow-Headers", "*");
		res.setHeader("Access-Control-Max-Age", "3600");
		chain.doFilter(req, res);
	}
}
