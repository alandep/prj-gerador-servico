package com.br.gerador.prjgeradorservico.model.services;

import org.springframework.stereotype.Service;

import com.br.gerador.prjgeradorservico.enums.ConstantesEnum;
import com.br.gerador.prjgeradorservico.enums.DiretorioArquivosEnum;
import com.br.gerador.prjgeradorservico.model.domain.Dominio;
import com.br.gerador.prjgeradorservico.model.services.fachada.IGeradorArquivoControllerServiceFachada;

/**
 * @author alan.franco
 *
 */
@Service
public class GeradorArquivoControllerService extends BaseService implements IGeradorArquivoControllerServiceFachada {

	@Override
	public void gerarArquivoController(Dominio dominio) {
		try {

			/* 1 - Gerar nome dos arquivos que serão criados em disco */
			String nomeArquivoController = dominio.getDescricaoDominio()
					+ ConstantesEnum.SUFIXO_CONTROLLER.getDescricao() + ConstantesEnum.EXTENSAO_JAVA.getDescricao();

			/* 2 - Gerar string equivalente do arquivo */
			String arquivoController = gerarClasseController(dominio);
			
			/* 3 - Salvar arquivo em disco */
			super.gerarArquivo(DiretorioArquivosEnum.DIRETORIO_CONTROLLER.getDiretorioArquivo(), nomeArquivoController,
					arquivoController);

		} catch (Exception e) {
			LOGGER.error(
					"Ocorreu um erro de execução GeradorArquivoControllerService >> gerarArquivoController: " + e.getMessage());
		}
	}
	
	
	public String gerarClasseController(Dominio dominio) throws Exception {
		try {
			StringBuilder buffer = new StringBuilder();
			
			buffer.append("package com.br.gerador.prjservicosgerados.controller;                                                                    \n");
            buffer.append("                                                                                                                         \n");
			buffer.append("import org.springframework.beans.factory.annotation.Autowired;                                                           \n");
			buffer.append("import org.springframework.web.bind.annotation.PathVariable;                                                             \n");
			buffer.append("import org.springframework.web.bind.annotation.RequestBody;                                                              \n");
			buffer.append("import org.springframework.web.bind.annotation.RequestMapping;                                                           \n");
			buffer.append("import org.springframework.web.bind.annotation.RequestMethod;                                                            \n");
			buffer.append("import org.springframework.web.bind.annotation.RestController;                                                           \n");
            buffer.append("                                                                                                                         \n");
			buffer.append("import com.br.gerador.prjservicosgerados.model.domain."+dominio.getDescricaoDominio().trim()+";                                                              \n");
			buffer.append("import com.br.gerador.prjservicosgerados.model.domain.RetornoException;                                                  \n");
			buffer.append("import com.br.gerador.prjservicosgerados.model.services.fachada."+dominio.getDescricaoDominio().trim()+"ServiceFachada;                                      \n");
			buffer.append("import com.google.gson.Gson;                                                                                             \n");
            buffer.append("                                                                                                                         \n");
			buffer.append("import io.swagger.annotations.ApiOperation;                                                                              \n");
            buffer.append("                                                                                                                         \n");
			buffer.append("/**                                                                                                                      \n");
			buffer.append(" * @author alan.franco                                                                                                   \n");
			buffer.append(" *                                                                                                                       \n");
			buffer.append(" */                                                                                                                      \n");
			buffer.append("@RestController                                                                                                          \n");
			buffer.append("@RequestMapping(\"/"+dominio.getDescricaoDominio().trim().toLowerCase()+"\")                                                                                                 \n");
			buffer.append("public class "+dominio.getDescricaoDominio().trim()+"Controller extends BaseController {                                                                     \n");
            buffer.append("                                                                                                                         \n");
			buffer.append("	@Autowired                                                                                                              \n");
			buffer.append("	private "+dominio.getDescricaoDominio().trim()+"ServiceFachada "+dominio.getDescricaoDominio().trim().toLowerCase()+"ServiceFachada;                                                                          \n");
            buffer.append("                                                                                                                         \n");
			buffer.append("	private Gson gson;                                                                                                      \n");
            buffer.append("                                                                                                                         \n");
			buffer.append("	public "+dominio.getDescricaoDominio().trim()+"Controller() {                                                                                               \n");
			buffer.append("		super();                                                                                                            \n");
			buffer.append("		this.gson = new Gson();                                                                                             \n");
			buffer.append("	}                                                                                                                       \n");
            buffer.append("                                                                                                                         \n");
			buffer.append("	@ApiOperation(value = \"Recuperar e listar todos os registos.\")                                                          \n");
			buffer.append("	@RequestMapping(value = \"/listar-todos\", method = RequestMethod.GET, produces = \"application/json; charset=utf-8\")      \n");
			buffer.append("	public String listarTodos() {                                                                                           \n");
			buffer.append("		try {                                                                                                               \n");
			buffer.append("			return this.gson.toJson("+dominio.getDescricaoDominio().trim().toLowerCase()+"ServiceFachada.listarTodos());                                                      \n");
			buffer.append("		} catch (Exception e) {                                                                                             \n");
			buffer.append("			return gson.toJson(e.getMessage());                                                                             \n");
			buffer.append("		}                                                                                                                   \n");
			buffer.append("	}                                                                                                                       \n");
            buffer.append("                                                                                                                         \n");
			buffer.append("	@ApiOperation(value = \"Recuperar um registro de acordo com id passado como parâmetro.\")                                 \n");
			buffer.append("	@RequestMapping(value = \"/buscar-por-id\", method = RequestMethod.GET, produces = \"application/json; charset=utf-8\")     \n");
			buffer.append("	public String buscarPorId(Long id) {                                                                                    \n");
			buffer.append("		try {                                                                                                               \n");
			buffer.append("			return this.gson.toJson("+dominio.getDescricaoDominio().trim().toLowerCase()+"ServiceFachada.buscarPorId(id));                                                    \n");
			buffer.append("		} catch (Exception e) {                                                                                             \n");
			buffer.append("			return gson.toJson(e.getMessage());                                                                             \n");
			buffer.append("		}                                                                                                                   \n");
			buffer.append("	}                                                                                                                       \n");
            buffer.append("                                                                                                                         \n");
			buffer.append("	@ApiOperation(value = \"Salvar um novo registro no banco.\")                                                              \n");
			buffer.append("	@RequestMapping(value = \"/salvar\", method = RequestMethod.POST, produces = \"application/json; charset=utf-8\")           \n");
			buffer.append("	public String salvar("+dominio.getDescricaoDominio().trim()+" "+dominio.getDescricaoDominio().trim().toLowerCase()+") {                                                                                       \n");
			buffer.append("		try {                                                                                                               \n");
			buffer.append("			return gson.toJson("+dominio.getDescricaoDominio().trim().toLowerCase()+"ServiceFachada.salvar("+dominio.getDescricaoDominio().trim().toLowerCase()+"));                                                            \n");
			buffer.append("		} catch (Exception e) {                                                                                             \n");
			buffer.append("			return gson.toJson(e.getMessage());                                                                             \n");
			buffer.append("		}                                                                                                                   \n");
			buffer.append("	}                                                                                                                       \n");
            buffer.append("                                                                                                                         \n");
			buffer.append("	@ApiOperation(value = \"Editar um registro de acordo com o id informado na requisição.\")                                 \n");
			buffer.append("	@RequestMapping(value = \"/editar/{id}\", method = RequestMethod.PUT, produces = \"application/json; charset=utf-8\")       \n");
			buffer.append("	public String editar(@PathVariable(\"id\") Long id, @RequestBody "+dominio.getDescricaoDominio().trim()+" "+dominio.getDescricaoDominio().trim().toLowerCase()+") {                                             \n");
			buffer.append("		try {                                                                                                               \n");
			buffer.append("			"+dominio.getDescricaoDominio().trim().toLowerCase()+".setId(id);                                                                                                 \n");
			buffer.append("			return gson.toJson("+dominio.getDescricaoDominio().trim().toLowerCase()+"ServiceFachada.editar("+dominio.getDescricaoDominio().trim().toLowerCase()+"));                                                            \n");
			buffer.append("		} catch (Exception e) {                                                                                             \n");
			buffer.append("			return gson.toJson(e.getMessage());                                                                             \n");
			buffer.append("		}                                                                                                                   \n");
			buffer.append("	}                                                                                                                       \n");
            buffer.append("                                                                                                                         \n");
			buffer.append("	@ApiOperation(value = \"Deletar um registro de acordo com o id informado na requisição.\")                                \n");
			buffer.append("	@RequestMapping(value = \"/deletar/{id}\", method = RequestMethod.DELETE, produces = \"application/json; charset=utf-8\")   \n");
			buffer.append("	public String deletar(@PathVariable(\"id\") Long id) {                                                                    \n");
			buffer.append("		try {                                                                                                               \n");
			buffer.append("			"+dominio.getDescricaoDominio().trim().toLowerCase()+"ServiceFachada.deletar(id);                                                                                 \n");
			buffer.append("			return gson.toJson(new RetornoException(200, \"200\", \"Deleção ocorreu com sucesso!\"));                           \n");
			buffer.append("		} catch (Exception e) {                                                                                             \n");
			buffer.append("			return gson.toJson(e.getMessage());                                                                             \n");
			buffer.append("		}                                                                                                                   \n");
			buffer.append("	}                                                                                                                       \n");
            buffer.append("                                                                                                                         \n");
			buffer.append("}                                                                                                                        \n");
			

			return buffer.toString();
		} catch (Exception e) {
			throw e;
		}
	}
}
