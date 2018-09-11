package com.br.gerador.prjservicosgerados.model.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author alan.franco
 *
 */
public abstract class BaseService {

	/**
	 * Constante criada para realizar a cobertura de log do código fonte.
	 */
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
}
