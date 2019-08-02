package com.br.gerador.prjgeradorservico.model.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.br.gerador.prjgeradorservico.enums.ConstantesEnum;
import com.br.gerador.prjgeradorservico.enums.DiretorioArquivosEnum;
import com.br.gerador.prjgeradorservico.model.domain.Atributo;
import com.br.gerador.prjgeradorservico.model.domain.Dominio;
import com.br.gerador.prjgeradorservico.model.services.fachada.IGeradorArquivoServiceFachada;;

/**
 * @author alan.franco
 *
 */
@Service
public class GeradorArquivoService extends BaseService implements IGeradorArquivoServiceFachada {

	@Override
	public void gerarArquivoService(Dominio dominio) {
		try {
			
			/* 1 - Gerar nome dos arquivos que serão criados em disco*/
			String nomeArquivoServiceFachada = dominio.getDescricaoDominio() + ConstantesEnum.SUFIXO_SERVICE_FACHADA.getDescricao() + ConstantesEnum.EXTENSAO_JAVA.getDescricao();			
			String nomeArquivoService = dominio.getDescricaoDominio() + ConstantesEnum.SUFIXO_SERVICE.getDescricao() + ConstantesEnum.EXTENSAO_JAVA.getDescricao();

			/* 2 - Gerar arquivo da fachada de serviço*/
			String arquivoServiceFachada = gerarClasseServiceFachada(dominio);			
			String arquivoService= gerarClasseService(dominio);			

			/* 3 - Salvar arquivo em disco */
			super.gerarArquivo(DiretorioArquivosEnum.DIRETORIO_SERVICES_FACHADA.getDiretorioArquivo(), nomeArquivoServiceFachada, arquivoServiceFachada);			
			super.gerarArquivo(DiretorioArquivosEnum.DIRETORIO_SERVICES.getDiretorioArquivo(), nomeArquivoService, arquivoService);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro de execução GeradorArquivoDomainService >> gerarArquivoDomain: " + e.getMessage());
		}
	}
	
	public String gerarClasseServiceFachada(Dominio dominio) throws Exception {
		try {
			StringBuilder buffer = new StringBuilder();
			
			buffer.append("package com.br.gerador.prjservicosgerados.model.services.fachada;                 \n");
		    buffer.append("                                                                                  \n");
			buffer.append("import java.util.List;                                                            \n");
		    buffer.append("                                                                                  \n");
			buffer.append("import com.br.gerador.prjservicosgerados.model.domain."+ dominio.getDescricaoDominio().trim() +";                       \n");
		    buffer.append("                                                                                  \n");
			buffer.append("/**                                                                               \n");
			buffer.append(" * @author alan.franco                                                            \n");
			buffer.append(" *                                                                                \n");
			buffer.append(" */                                                                               \n");
			buffer.append("public interface " + dominio.getDescricaoDominio().trim() + "ServiceFachada {                                             \n");
		    buffer.append("                                                                                  \n");
			buffer.append("	/**                                                                              \n");
			buffer.append("	 * @return                                                                       \n");
			buffer.append("	 */                                                                              \n");
			buffer.append("	List<"+ dominio.getDescricaoDominio().trim() +"> listarTodos() throws Exception;                                       \n");
		    buffer.append("                                                                                  \n");
			buffer.append("	/**                                                                              \n");
			buffer.append("	 * @param id                                                                     \n");
			buffer.append("	 * @return                                                                       \n");
			buffer.append("	 */                                                                              \n");
			buffer.append("	"+ dominio.getDescricaoDominio().trim() +" buscarPorId(Long id) throws Exception;                                      \n");
		    buffer.append("                                                                                  \n");
			buffer.append("	/**                                                                              \n");
			buffer.append("	 * @param gerador                                                                \n");
			buffer.append("	 * @return                                                                       \n");
			buffer.append("	 */                                                                              \n");
			buffer.append("	"+ dominio.getDescricaoDominio().trim() +" salvar("+ dominio.getDescricaoDominio().trim() +" "+dominio.getDescricaoDominio().trim().toLowerCase()+") throws Exception;                                         \n");
		    buffer.append("                                                                                  \n");
			buffer.append("	/**                                                                              \n");
			buffer.append("	 * @param id                                                                     \n");
			buffer.append("	 * @return                                                                       \n");
			buffer.append("	 */                                                                              \n");
			buffer.append("	"+ dominio.getDescricaoDominio().trim() +" editar("+ dominio.getDescricaoDominio().trim() +" "+dominio.getDescricaoDominio().trim().toLowerCase()+") throws Exception;                                         \n");
		    buffer.append("                                                                                  \n");
			buffer.append("	/**                                                                              \n");
			buffer.append("	 * @param id                                                                     \n");
			buffer.append("	 * @return                                                                       \n");
			buffer.append("	 */                                                                              \n");
			buffer.append("	void deletar(Long id) throws Exception;                                          \n");
		    buffer.append("                                                                                  \n");
			buffer.append("}                                                                                 \n");
			
			return buffer.toString();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public String gerarClasseService(Dominio dominio) throws Exception {
		try {
			StringBuilder buffer = new StringBuilder();
			
			buffer.append("package com.br.gerador.prjservicosgerados.model.services;                                                              \n");    
		    buffer.append("                                                                                                                       \n");
			buffer.append("import java.text.MessageFormat;                                                                                        \n");
			buffer.append("import java.util.ArrayList;                                                                                            \n");
			buffer.append("import java.util.List;                                                                                                 \n");
			buffer.append("import java.util.Optional;                                                                                             \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("import org.apache.commons.lang3.StringUtils;                                                                           \n");
			buffer.append("import org.springframework.beans.factory.annotation.Autowired;                                                         \n");
			buffer.append("import org.springframework.data.jpa.repository.Modifying;                                                              \n");
			buffer.append("import org.springframework.stereotype.Service;                                                                         \n");
			buffer.append("import org.springframework.transaction.annotation.Transactional;                                                       \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("import com.br.gerador.prjservicosgerados.model.domain."+dominio.getDescricaoDominio().trim()+";                                                            \n");
			buffer.append("import com.br.gerador.prjservicosgerados.model.domain.RetornoException;                                                \n");
			buffer.append("import com.br.gerador.prjservicosgerados.model.repository."+dominio.getDescricaoDominio().trim()+"Repository;                                              \n");
			buffer.append("import com.br.gerador.prjservicosgerados.model.services.fachada."+dominio.getDescricaoDominio().trim()+"ServiceFachada;                                    \n");
			buffer.append("import com.google.gson.Gson;                                                                                           \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("/**                                                                                                                    \n");
			buffer.append(" * @author alan.franco                                                                                                 \n");
			buffer.append(" *                                                                                                                     \n");
			buffer.append(" */                                                                                                                    \n");
			buffer.append("@Service                                                                                                               \n");
			buffer.append("public class "+dominio.getDescricaoDominio().trim()+"Service extends BaseService implements "+dominio.getDescricaoDominio().trim()+"ServiceFachada {                                           \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("	/**                                                                                                                   \n");
			buffer.append("	 * Injetando as dependências da interface de abstração do spring data                                                 \n");
			buffer.append("	 * responsável pelo acesso aos dados deste objeto                                                                     \n");
			buffer.append("	 */                                                                                                                   \n");
			buffer.append("	@Autowired                                                                                                            \n");
			buffer.append("	private "+dominio.getDescricaoDominio().trim()+"Repository geradorRepository;                                                                             \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("	private Gson gson;                                                                                                    \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("	private RetornoException retornoException;                                                                            \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("	public "+dominio.getDescricaoDominio().trim()+"Service() {                                                                                                \n");
			buffer.append("		super();                                                                                                          \n");
			buffer.append("		this.gson = new Gson();                                                                                           \n");
			buffer.append("		this.retornoException = new RetornoException(500, \"500\", \"Desculpe o transtorno! Ocorreu um erro interno.\");      \n");
			buffer.append("	}                                                                                                                     \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("	@Override                                                                                                             \n");
			buffer.append("	public List<"+dominio.getDescricaoDominio().trim()+"> listarTodos() throws Exception {                                                                    \n");
			buffer.append("		List<"+dominio.getDescricaoDominio().trim()+"> retorno = new ArrayList<"+dominio.getDescricaoDominio().trim()+">();                                                                       \n");
			buffer.append("		try {                                                                                                             \n");
			buffer.append("			retorno = this.geradorRepository.findAll();                                                                   \n");
			buffer.append("		} catch (Exception e) {                                                                                           \n");
			buffer.append("			super.LOGGER.error(\"Ocorreu um erro na classe: "+dominio.getDescricaoDominio().trim()+"Service >> listarTodos(): \" + e.getMessage());             \n");
			buffer.append("			throw new Exception(gson.toJson(retornoException));                                                           \n");
			buffer.append("		}                                                                                                                 \n");
			buffer.append("		return retorno;                                                                                                   \n");
			buffer.append("	}                                                                                                                     \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("	@Override                                                                                                             \n");
			buffer.append("	public "+dominio.getDescricaoDominio().trim()+" buscarPorId(Long id) throws Exception {                                                                   \n");
			buffer.append("		Optional<"+dominio.getDescricaoDominio().trim()+"> retorno = null;                                                                                    \n");
			buffer.append("		try {                                                                                                             \n");
			buffer.append("			retorno = this.geradorRepository.findById(id);                                                                \n");
			buffer.append("		} catch (Exception e) {                                                                                           \n");
			buffer.append("			super.LOGGER.error(\"Ocorreu um erro na classe: "+dominio.getDescricaoDominio().trim()+"Service >> buscarPorId(Long id): \" + e.getMessage());      \n");
			buffer.append("			throw new Exception(gson.toJson(retornoException));                                                           \n");
			buffer.append("		}                                                                                                                 \n");
			buffer.append("		return retorno.get();                                                                                             \n");
			buffer.append("	}                                                                                                                     \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("	@Transactional                                                                                                        \n");
			buffer.append("	@Override                                                                                                             \n");
			buffer.append("	public "+dominio.getDescricaoDominio().trim()+" salvar("+dominio.getDescricaoDominio().trim()+" "+dominio.getDescricaoDominio().trim().toLowerCase()+") throws Exception {                                                                      \n");
			buffer.append("		"+dominio.getDescricaoDominio().trim()+" retorno = new "+dominio.getDescricaoDominio().trim()+"();                                                                                        \n");
			buffer.append("		try {                                                                                                             \n");
			buffer.append("			validarCamposObrigatorioSalvar("+dominio.getDescricaoDominio().trim().toLowerCase()+");                                                                         \n");
			buffer.append("			retorno = this.geradorRepository.save("+dominio.getDescricaoDominio().trim().toLowerCase()+");                                                                  \n");
			buffer.append("		} catch (Exception e) {                                                                                           \n");
			buffer.append("			super.LOGGER.error(                                                                                           \n");
			buffer.append("					\"Ocorreu um erro na classe: "+dominio.getDescricaoDominio().trim()+"Service >> salvar(ObjetoDominio objetoDominio): \" + e.getMessage());  \n");
			buffer.append("			throw new Exception(gson.toJson(retornoException));                                                           \n");
			buffer.append("		}                                                                                                                 \n");
			buffer.append("		return retorno;                                                                                                   \n");
			buffer.append("	}                                                                                                                     \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("	/**                                                                                                                   \n");
			buffer.append("	 * @param "+dominio.getDescricaoDominio().trim().toLowerCase()+"                                                                                                        \n");
			buffer.append("	 *                                                                                                                    \n");
			buffer.append("	 *             Método responsável por validar se todos os campos obrigatórios                                         \n");
			buffer.append("	 *             foram informados antes da operação de salva                                                            \n");
			buffer.append("	 * @throws Exception                                                                                                  \n");
			buffer.append("	 */                                                                                                                   \n");
			buffer.append("	private void validarCamposObrigatorioSalvar("+dominio.getDescricaoDominio().trim()+" "+dominio.getDescricaoDominio().trim().toLowerCase()+") throws Exception {                                             \n");
			buffer.append("		StringBuilder camposNaoInformados = new StringBuilder();                                                          \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("		camposNaoInformados.append(\"[ \");                                                                                 \n");
			
			/*Inserir validação para campos obrigatórios*/
			escreverValidacaoCamposObrigatorios(dominio, buffer);
			
			buffer.append("		camposNaoInformados.append(\" ]\");                                                                                 \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("		if (camposNaoInformados.toString().trim().length() > 4) {                                                         \n");
			buffer.append("			RetornoException retorno = new RetornoException(201, \"201\",                                                   \n");
			buffer.append("					MessageFormat.format(\"Campos obrigatórios não informados: {0}.\", camposNaoInformados.toString()));    \n");
			buffer.append("			throw new Exception(gson.toJson(retorno));                                                                    \n");
			buffer.append("		}                                                                                                                 \n");
			buffer.append("	}                                                                                                                     \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("	@Transactional                                                                                                        \n");
			buffer.append("	@Modifying                                                                                                            \n");
			buffer.append("	@Override                                                                                                             \n");
			buffer.append("	public "+dominio.getDescricaoDominio().trim()+" editar("+dominio.getDescricaoDominio().trim()+" "+dominio.getDescricaoDominio().trim().toLowerCase()+") throws Exception {                                                                      \n");
			buffer.append("		"+dominio.getDescricaoDominio().trim()+" retorno = new "+dominio.getDescricaoDominio().trim()+"();                                                                                        \n");
			buffer.append("		try {                                                                                                             \n");
			buffer.append("			retorno = this.geradorRepository.save("+dominio.getDescricaoDominio().trim().toLowerCase()+");                                                                  \n");
			buffer.append("		} catch (Exception e) {                                                                                           \n");
			buffer.append("			super.LOGGER.error(                                                                                           \n");
			buffer.append("					\"Ocorreu um erro na classe: "+dominio.getDescricaoDominio().trim()+"Service >> editar(ObjetoDominio objetoDominio): \" + e.getMessage());  \n");
			buffer.append("			throw new Exception(gson.toJson(retornoException));                                                           \n");
			buffer.append("		}                                                                                                                 \n");
			buffer.append("		return retorno;                                                                                                   \n");
			buffer.append("	}                                                                                                                     \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("	@Transactional                                                                                                        \n");
			buffer.append("	@Override                                                                                                             \n");
			buffer.append("	public void deletar(Long id) throws Exception {                                                                       \n");
			buffer.append("		try {                                                                                                             \n");
			buffer.append("			this.geradorRepository.deleteById(id);                                                                        \n");
			buffer.append("		} catch (Exception e) {                                                                                           \n");
			buffer.append("			super.LOGGER.error(\"Ocorreu um erro na classe: "+dominio.getDescricaoDominio().trim()+"Service >> (Long id): \" + e.getMessage());               \n");
			buffer.append("			throw new Exception(gson.toJson(retornoException));                                                           \n");
			buffer.append("		}                                                                                                                 \n");
			buffer.append("	}                                                                                                                     \n");
		    buffer.append("                                                                                                                       \n");
			buffer.append("}                                                                                                                      \n");
			
			return buffer.toString();
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void escreverValidacaoCamposObrigatorios(Dominio dominio, StringBuilder buffer) {
		for (Atributo atributo : dominio.getListaAtributo()) {
			if (!atributo.getDescricao().equalsIgnoreCase("id")) {
				if(atributo.getIsObrigatorio()) {
					
					switch (atributo.getTipoDeDadoEnum()) {
					case STRING: {
						
						buffer.append("		if (StringUtils.isBlank("+dominio.getDescricaoDominio().trim().toLowerCase()+".get"+this.colocarPrimeiraLetraMaiuscula(atributo.getDescricao().trim())+"())) {                                                                         \n");
						buffer.append("			camposNaoInformados.append(\""+atributo.getDescricao().trim()+"\");                                                                            \n");
						buffer.append("         camposNaoInformados.append(\",\");                                                                                                                       \n");
						buffer.append("		}                                                                                                                 \n");

						break;
					}
					case DOUBLE: {
						buffer.append("		if ("+dominio.getDescricaoDominio().trim().toLowerCase()+".get"+this.colocarPrimeiraLetraMaiuscula(atributo.getDescricao().trim())+"() <= 0) {                                                                         \n");
						buffer.append("			camposNaoInformados.append(\""+atributo.getDescricao().trim()+"\");                                                                            \n");
						buffer.append("         camposNaoInformados.append(\",\");                                                                                                                       \n");
						buffer.append("		}                                                                                                                 \n");

						break;
					}
					case INTEGER: {
						buffer.append("		if ("+dominio.getDescricaoDominio().trim().toLowerCase()+".get"+this.colocarPrimeiraLetraMaiuscula(atributo.getDescricao().trim())+"() <= 0) {                                                                         \n");
						buffer.append("			camposNaoInformados.append(\""+atributo.getDescricao().trim()+"\");                                                                            \n");
						buffer.append("         camposNaoInformados.append(\",\");                                                                                                                       \n");
						buffer.append("		}                                                                                                                 \n");

						break;
					}				
					case LONG: {
						buffer.append("		if ("+dominio.getDescricaoDominio().trim().toLowerCase()+".get"+this.colocarPrimeiraLetraMaiuscula(atributo.getDescricao().trim())+"() <= 0) {                                                                         \n");
						buffer.append("			camposNaoInformados.append(\""+atributo.getDescricao().trim()+"\");                                                                            \n");
						buffer.append("         camposNaoInformados.append(\",\");                                                                                                                       \n");
						buffer.append("		}                                                                                                                 \n");

						break;
					}
					case DATE: {
						buffer.append("		if ("+dominio.getDescricaoDominio().trim().toLowerCase()+".get"+this.colocarPrimeiraLetraMaiuscula(atributo.getDescricao().trim())+"() == null) {                                                                         \n");
						buffer.append("			camposNaoInformados.append(\""+atributo.getDescricao().trim()+"\");                                                                            \n");
						buffer.append("         camposNaoInformados.append(\",\");                                                                                                                       \n");
						buffer.append("		}                                                                                                                 \n");						
						break;
					}
					}					
				}				
			}
		}
	}
	
	private String colocarPrimeiraLetraMaiuscula(String texto) {
		if(!StringUtils.isBlank(texto)) {
			return texto.substring(0,1).toUpperCase().concat(texto.substring(1));
		}
		return StringUtils.EMPTY;
	}
	
}
