package com.br.gerador.prjgeradorservico.model.services;

import org.springframework.stereotype.Service;

import com.br.gerador.prjgeradorservico.enums.ConstantesEnum;
import com.br.gerador.prjgeradorservico.enums.DiretorioArquivosEnum;
import com.br.gerador.prjgeradorservico.model.domain.Dominio;
import com.br.gerador.prjgeradorservico.model.services.fachada.GeradorArquivoRepositoryServiceFachada;

/**
 * @author alan.franco
 *
 */
@Service
public class GeradorArquivoRepositoryService extends BaseService implements GeradorArquivoRepositoryServiceFachada {

	@Override
	public void gerarArquivoRepository(Dominio dominio) {
		try {

			/* 1 - Gerar nome dos arquivos que serão criados em disco */
			String nomeArquivoRepository = dominio.getDescricaoDominio()
					+ ConstantesEnum.SUFIXO_REPOSITORY.getDescricao() + ConstantesEnum.EXTENSAO_JAVA.getDescricao();

			/* 2 - Gerar string equivalente do arquivo */
			String arquivoRepository = gerarClasseRepository(dominio);

			/* 3 - Salvar arquivo em disco */
			super.gerarArquivo(DiretorioArquivosEnum.DIRETORIO_REPOSITORY.getDiretorioArquivo(), nomeArquivoRepository,
					arquivoRepository);

		} catch (Exception e) {
			LOGGER.error(
					"Ocorreu um erro de execução GeradorArquivoDomainService >> gerarArquivoDomain: " + e.getMessage());
		}
	}

	public String gerarClasseRepository(Dominio dominio) throws Exception {
		try {
			StringBuilder buffer = new StringBuilder();			
			
			buffer.append("package com.br.gerador.prjservicosgerados.model.repository;                             \n");
            buffer.append("                                                                                        \n");
			buffer.append("import java.io.Serializable;                                                            \n");
            buffer.append("                                                                                        \n");
			buffer.append("import org.springframework.data.jpa.repository.JpaRepository;                           \n");
			buffer.append("import org.springframework.stereotype.Repository;                                       \n");
            buffer.append("                                                                                        \n");
			buffer.append("import com.br.gerador.prjservicosgerados.model.domain."+dominio.getDescricaoDominio().trim()+";                             \n");
            buffer.append("                                                                                        \n");
			buffer.append("/**                                                                                     \n");
			buffer.append(" * @author alan.franco                                                                  \n");
			buffer.append(" *                                                                                      \n");
			buffer.append(" *         Classe de acesso aos dados de faixas de registro.                            \n");
			buffer.append(" *                                                                                      \n");
			buffer.append(" */                                                                                     \n");
			buffer.append("@Repository                                                                             \n");
			buffer.append("public interface " + dominio.getDescricaoDominio().trim() + "Repository extends JpaRepository<"+dominio.getDescricaoDominio().trim()+", Long>, Serializable {       \n");
            buffer.append("                                                                                        \n");
			buffer.append("}                                                                                       \n");			

			return buffer.toString();
		} catch (Exception e) {
			throw e;
		}
	}
}
