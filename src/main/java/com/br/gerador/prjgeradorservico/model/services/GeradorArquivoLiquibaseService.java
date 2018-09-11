package com.br.gerador.prjgeradorservico.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.gerador.prjgeradorservico.enums.ConstantesEnum;
import com.br.gerador.prjgeradorservico.enums.DiretorioArquivosEnum;
import com.br.gerador.prjgeradorservico.model.domain.Atributo;
import com.br.gerador.prjgeradorservico.model.domain.Dominio;
import com.br.gerador.prjgeradorservico.model.repository.DbChangeLogRepositoryFachada;
import com.br.gerador.prjgeradorservico.model.services.fachada.GeradorArquivoLiquibaseServiceFachada;
import com.br.gerador.prjgeradorservico.util.MetodosUteis;

/**
 * @author alan.franco
 *
 */
@Service
public class GeradorArquivoLiquibaseService extends BaseService implements GeradorArquivoLiquibaseServiceFachada {

	

	@Override
	public void gerarArquivoLiquibase(Dominio dominio, Integer ultimoIdLiquibase) {
		try {

			/* 1 - Gerar nome dos arquivos que serão criados em disco */
			String nomeArquivoLiquibase = ConstantesEnum.PREFIXO_LIQUIBASE.getDescricao()
					+ dominio.getDescricaoDominio().trim().toLowerCase() + ConstantesEnum.EXTENSAO_XML.getDescricao();

			/* 2 - Gerar string equivalente do arquivo */
			String arquivoLiquibase = gerarXMLLiquibase(dominio, ultimoIdLiquibase);

			/* 3 - Salvar arquivo em disco */
			super.gerarArquivo(DiretorioArquivosEnum.DIRETORIO_LIQUIBASE.getDiretorioArquivo(), nomeArquivoLiquibase,
					arquivoLiquibase);
			
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro de execução GeradorArquivoControllerService >> gerarArquivoController: "
					+ e.getMessage());
		}
	}

	public String gerarXMLLiquibase(Dominio dominio, Integer ultimoIdLiquibase) throws Exception {
		try {
			StringBuilder buffer = new StringBuilder();
			// DbChangeLogRepository dbChangeLogRepository = new DbChangeLogRepository();

			buffer.append(
					"<?xml version=\"1.0\" encoding=\"UTF-8\"?>                                                            \n");
			buffer.append(
					"<databaseChangeLog                                                                                \n");
			buffer.append(
					"	xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"                                              \n");
			buffer.append(
					"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"                                            \n");
			buffer.append(
					"	xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog                                  \n");
			buffer.append(
					"         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd\">                        \n");
			buffer.append(
					"                                                                                                  \n");
			buffer.append("	<changeSet id=\"" + (ultimoIdLiquibase + 1)
					+ "\" author=\"alan.franco\">                                                          \n");
			buffer.append("		<createTable tableName=\"tb_" + dominio.getDescricaoDominio().trim().toLowerCase()
					+ "\">                                                            \n");
			buffer.append(
					"                                                                                                  \n");
			buffer.append(
					"			<column name=\"id\" type=\"int\" autoIncrement=\"true\">                                       \n");
			buffer.append(
					"				<constraints primaryKey=\"true\" nullable=\"false\" />                                   \n");
			buffer.append(
					"			</column>                                                                                \n");
			buffer.append(
					"                                                                                                  \n");
			/*
			 * Método responsável por escrever as colunas na tabela de forma dinâmica
			 */
			escreverColunasDaTabela(dominio, buffer);

			buffer.append(
					"		</createTable>                                                                               \n");
			buffer.append(
					"	</changeSet>                                                                                     \n");
			buffer.append(
					"</databaseChangeLog>                                                                              \n");

			return buffer.toString();
		} catch (Exception e) {
			throw e;
		}
	}

	private void escreverColunasDaTabela(Dominio dominio, StringBuilder buffer) {
		for (Atributo atributo : dominio.getListaAtributo()) {
			if (!atributo.getDescricao().equalsIgnoreCase("id")) {
				switch (atributo.getTipoDeDadoEnum()) {
				case STRING: {

					buffer.append("			<column name=\"" + atributo.getDescricao() + "\" type=\"varchar("
							+ atributo.getQuantidadeDigitos()
							+ ")\" />                                                \n");

					break;
				}
				case DOUBLE: {
					buffer.append("			<column name=\"" + atributo.getDescricao()
							+ "\" type=\"numeric(18,3)\" />                                             \n");

					break;
				}
				case BOOLEAN: {
					buffer.append("			<column name=\"" + atributo.getDescricao()
							+ "\" type=\"BIT(1)\" />                                                    \n");

					break;
				}
				case INTEGER: {
					buffer.append("			<column name=\"" + atributo.getDescricao()
							+ "\" type=\"int\" />                                      \n");

					break;
				}
				case LONG: {
					buffer.append("			<column name=\"" + atributo.getDescricao()
							+ "\" type=\"int\" />                                      \n");

					break;
				}
				case DATE: {
					buffer.append("			<column name=\"" + atributo.getDescricao()
							+ "\" type=\"TIMESTAMP\" />                                             \n");

					break;
				}
				}
				buffer.append(
						"                                                                                                  \n");
			}
		}
	}

}
