package com.br.gerador.prjgeradorservico.model.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class DbChangeLogRepository implements IDbChangeLogRepositoryFachada {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Integer findMaxIdChangeLog() {
		Integer resultado = 0;
		try {
			Query q = em.createNativeQuery("select max(ifnull(id,0)) from databasechangelog");
			resultado = Integer.valueOf((String)q.getSingleResult());
			return resultado;
		} catch (Exception e) {
			return resultado;
		}
	}
}
