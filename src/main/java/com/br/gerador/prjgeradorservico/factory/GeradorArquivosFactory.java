
package com.br.gerador.prjgeradorservico.factory;

import com.br.gerador.prjgeradorservico.model.domain.Dominio;
import com.br.gerador.prjgeradorservico.model.services.GeradorArquivoControllerService;
import com.br.gerador.prjgeradorservico.model.services.GeradorArquivoDomainService;
import com.br.gerador.prjgeradorservico.model.services.GeradorArquivoLiquibaseService;
import com.br.gerador.prjgeradorservico.model.services.GeradorArquivoRepositoryService;
import com.br.gerador.prjgeradorservico.model.services.GeradorArquivoService;

/**
 * @author alan.franco
 *
 */
public enum GeradorArquivosFactory {

	GERAR_DOMAIN("Domain") {
		@Override
		public void gerar() {
			try {
				GeradorArquivoDomainService geradorArquivoDomainService = new GeradorArquivoDomainService();
				geradorArquivoDomainService.gerarArquivoDomain(this.dominio);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	},

	GERAR_SCRIPT_LIQUIBASE("Liquibase") {
		@Override
		public void gerar() {
			try {
				GeradorArquivoLiquibaseService geradorArquivoLiquibaseService = new GeradorArquivoLiquibaseService();
				geradorArquivoLiquibaseService.gerarArquivoLiquibase(this.dominio, super.getUltimoIdLiquibase());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	},

	GERAR_REPOSITORY("Repository") {
		@Override
		public void gerar() {
			try {
				GeradorArquivoRepositoryService geradorArquivoRepositoryService = new GeradorArquivoRepositoryService();
				geradorArquivoRepositoryService.gerarArquivoRepository(this.dominio);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	},

	GERAR_SERVICE("Service") {
		@Override
		public void gerar() {
			try {
				GeradorArquivoService geradorArquivoService = new GeradorArquivoService();
				geradorArquivoService.gerarArquivoService(dominio);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	},

	GERAR_CONTROLLER("Controller") {
		@Override
		public void gerar() {
			try {
				GeradorArquivoControllerService geradorArquivoControllerService = new GeradorArquivoControllerService();
				geradorArquivoControllerService.gerarArquivoController(dominio);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	private String description;

	protected Dominio dominio;

	protected Integer ultimoIdLiquibase;

	/* Contrutor Padrão */
	private GeradorArquivosFactory(String description) {
		this.description = description;
	}

	/* Get's Set's */
	public String getDescription() {
		return description;
	}

	public Dominio getDominio() {
		return dominio;
	}

	public void setDominio(Dominio dominio) {
		this.dominio = dominio;
	}

	public Integer getUltimoIdLiquibase() {
		return ultimoIdLiquibase;
	}

	public void setUltimoIdLiquibase(Integer ultimoIdLiquibase) {
		this.ultimoIdLiquibase = ultimoIdLiquibase;
	}

	public static GeradorArquivosFactory parse(String name) {
		for (GeradorArquivosFactory type : GeradorArquivosFactory.values()) {
			if (type.name().equals(name)) {
				return type;
			}
		}
		return null;
	}

	public abstract void gerar();

}
