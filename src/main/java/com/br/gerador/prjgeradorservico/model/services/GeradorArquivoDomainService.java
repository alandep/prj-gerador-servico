package com.br.gerador.prjgeradorservico.model.services;

import org.springframework.stereotype.Service;

import com.br.gerador.prjgeradorservico.enums.ConstantesEnum;
import com.br.gerador.prjgeradorservico.enums.DiretorioArquivosEnum;
import com.br.gerador.prjgeradorservico.model.domain.Atributo;
import com.br.gerador.prjgeradorservico.model.domain.Dominio;
import com.br.gerador.prjgeradorservico.model.services.fachada.GeradorArquivoDomainServiceFachada;

/**
 * @author alan.franco
 *
 */
@Service
public class GeradorArquivoDomainService extends BaseService implements GeradorArquivoDomainServiceFachada {

	@Override
	public void gerarArquivoDomain(Dominio dominio) {
		try {			
			String nomeArquivoScript = dominio.getDescricaoDominio() + ConstantesEnum.EXTENSAO_JAVA.getDescricao();

			// 1 - Gerar script banco para excluir parceiro da base do SITE
			String script = gerarClasseDominio(dominio);

			// 2 - Salvar arquivo em disco
			super.gerarArquivo(DiretorioArquivosEnum.DIRETORIO_DOMAIN.getDiretorioArquivo(), nomeArquivoScript, script);
		} catch (Exception e) {
			LOGGER.error(
					"Ocorreu um erro de execução GeradorArquivoDomainService >> gerarArquivoDomain: " + e.getMessage());
		}
	}

	private void escreverAtributosDoDominio(Dominio dominio, StringBuilder buffer) {
		for (Atributo atributo : dominio.getListaAtributo()) {
			if (!atributo.getDescricao().equalsIgnoreCase("id")) {
				switch (atributo.getTipoDeDadoEnum()) {
				case DOUBLE: {
					buffer.append("	@Column(name = \"" + atributo.getDescricao()
							+ "\", precision = 18, scale = 3)                             \n");

					break;
				}
				case DATE: {
					buffer.append("	@Column(name = \"" + atributo.getDescricao()
							+ "\")                                              \n");
					buffer.append(
							"	@Temporal(TemporalType.TIMESTAMP)                                                \n");
					break;
				}
				default: {
					buffer.append("	@Column(name = \"" + atributo.getDescricao()
							+ "\")                                              \n");
					break;
				}
				}
				buffer.append("	private " + atributo.getTipoDeDadoEnum().getDescricao() + " " + atributo.getDescricao()
						+ ";                                                   \n");
				buffer.append("                                                                                  \n");
			}
		}
	}

	public String gerarClasseDominio(Dominio dominio) throws Exception {
		try {
			StringBuilder buffer = new StringBuilder();

			buffer.append("package com.br.gerador.prjservicosgerados.model.domain;                           \n");
			buffer.append("                                                                                  \n");
			buffer.append("import java.util.Date;                                                            \n");
			buffer.append("                                                                                  \n");
			buffer.append("import javax.persistence.Column;                                                  \n");
			buffer.append("import javax.persistence.Entity;                                                  \n");
			buffer.append("import javax.persistence.GeneratedValue;                                          \n");
			buffer.append("import javax.persistence.GenerationType;                                          \n");
			buffer.append("import javax.persistence.Id;                                                      \n");
			buffer.append("import javax.persistence.Table;                                                   \n");
			buffer.append("import javax.persistence.Temporal;                                                \n");
			buffer.append("import javax.persistence.TemporalType;                                            \n");
			buffer.append("                                                                                  \n");
			buffer.append("import lombok.EqualsAndHashCode;                                                  \n");
			buffer.append("import lombok.Getter;                                                             \n");
			buffer.append("import lombok.NoArgsConstructor;                                                  \n");
			buffer.append("import lombok.Setter;                                                             \n");
			buffer.append("import lombok.ToString;                                                           \n");
			buffer.append("                                                                                  \n");
			buffer.append("@Getter                                                                           \n");
			buffer.append("@Setter                                                                           \n");
			buffer.append("@NoArgsConstructor                                                                \n");
			buffer.append("@EqualsAndHashCode(callSuper = false)                                             \n");
			buffer.append("@ToString                                                                         \n");
			buffer.append("@Entity                                                                           \n");
			buffer.append("@Table(name = \"tb_" + dominio.getDescricaoDominio().trim().toLowerCase() + "\")  \n");
			buffer.append("public class " + dominio.getDescricaoDominio().trim() + " extends BaseDomain {    \n");
			buffer.append("                                                                                  \n");
			buffer.append("	private static final long serialVersionUID = -9158814171554795891L;              \n");
			buffer.append("                                                                                  \n");
			buffer.append("	@Id                                                                              \n");
			buffer.append("	@GeneratedValue(strategy = GenerationType.IDENTITY)                              \n");
			buffer.append("	private Long id;                                                                 \n");
			buffer.append("                                                                                  \n");

			/*
			 * Método responsável por escrever os atributos e seus tipos de dados
			 * dinamicamente
			 */
			escreverAtributosDoDominio(dominio, buffer);

			buffer.append("}                                                                                 \n");

			return buffer.toString();
		} catch (Exception e) {
			throw e;
		}
	}

}
