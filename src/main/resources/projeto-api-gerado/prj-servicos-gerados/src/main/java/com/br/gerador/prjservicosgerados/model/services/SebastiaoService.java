package com.br.gerador.prjservicosgerados.model.services;                                                              
                                                                                                                       
import java.text.MessageFormat;                                                                                        
import java.util.ArrayList;                                                                                            
import java.util.List;                                                                                                 
import java.util.Optional;                                                                                             
                                                                                                                       
import org.apache.commons.lang3.StringUtils;                                                                           
import org.springframework.beans.factory.annotation.Autowired;                                                         
import org.springframework.data.jpa.repository.Modifying;                                                              
import org.springframework.stereotype.Service;                                                                         
import org.springframework.transaction.annotation.Transactional;                                                       
                                                                                                                       
import com.br.gerador.prjservicosgerados.model.domain.Sebastiao;                                                            
import com.br.gerador.prjservicosgerados.model.domain.RetornoException;                                                
import com.br.gerador.prjservicosgerados.model.repository.SebastiaoRepository;                                              
import com.br.gerador.prjservicosgerados.model.services.fachada.SebastiaoServiceFachada;                                    
import com.google.gson.Gson;                                                                                           
                                                                                                                       
/**                                                                                                                    
 * @author alan.franco                                                                                                 
 *                                                                                                                     
 */                                                                                                                    
@Service                                                                                                               
public class SebastiaoService extends BaseService implements SebastiaoServiceFachada {                                           
                                                                                                                       
	/**                                                                                                                   
	 * Injetando as dependências da interface de abstração do spring data                                                 
	 * responsável pelo acesso aos dados deste objeto                                                                     
	 */                                                                                                                   
	@Autowired                                                                                                            
	private SebastiaoRepository geradorRepository;                                                                             
                                                                                                                       
	private Gson gson;                                                                                                    
                                                                                                                       
	private RetornoException retornoException;                                                                            
                                                                                                                       
	public SebastiaoService() {                                                                                                
		super();                                                                                                          
		this.gson = new Gson();                                                                                           
		this.retornoException = new RetornoException(500, "500", "Desculpe o transtorno! Ocorreu um erro interno.");      
	}                                                                                                                     
                                                                                                                       
	@Override                                                                                                             
	public List<Sebastiao> listarTodos() throws Exception {                                                                    
		List<Sebastiao> retorno = new ArrayList<Sebastiao>();                                                                       
		try {                                                                                                             
			retorno = this.geradorRepository.findAll();                                                                   
		} catch (Exception e) {                                                                                           
			super.LOGGER.error("Ocorreu um erro na classe: SebastiaoService >> listarTodos(): " + e.getMessage());             
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
		return retorno;                                                                                                   
	}                                                                                                                     
                                                                                                                       
	@Override                                                                                                             
	public Sebastiao buscarPorId(Long id) throws Exception {                                                                   
		Optional<Sebastiao> retorno = null;                                                                                    
		try {                                                                                                             
			retorno = this.geradorRepository.findById(id);                                                                
		} catch (Exception e) {                                                                                           
			super.LOGGER.error("Ocorreu um erro na classe: SebastiaoService >> buscarPorId(Long id): " + e.getMessage());      
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
		return retorno.get();                                                                                             
	}                                                                                                                     
                                                                                                                       
	@Transactional                                                                                                        
	@Override                                                                                                             
	public Sebastiao salvar(Sebastiao sebastiao) throws Exception {                                                                      
		Sebastiao retorno = new Sebastiao();                                                                                        
		try {                                                                                                             
			validarCamposObrigatorioSalvar(sebastiao);                                                                         
			retorno = this.geradorRepository.save(sebastiao);                                                                  
		} catch (Exception e) {                                                                                           
			super.LOGGER.error(                                                                                           
					"Ocorreu um erro na classe: SebastiaoService >> salvar(ObjetoDominio objetoDominio): " + e.getMessage());  
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
		return retorno;                                                                                                   
	}                                                                                                                     
                                                                                                                       
	/**                                                                                                                   
	 * @param sebastiao                                                                                                        
	 *                                                                                                                    
	 *             Método responsável por validar se todos os campos obrigatórios                                         
	 *             foram informados antes da operação de salva                                                            
	 * @throws Exception                                                                                                  
	 */                                                                                                                   
	private void validarCamposObrigatorioSalvar(Sebastiao sebastiao) throws Exception {                                             
		StringBuilder camposNaoInformados = new StringBuilder();                                                          
                                                                                                                       
		camposNaoInformados.append("[ ");                                                                                 
		if (sebastiao.getPreco() <= 0) {                                                                         
			camposNaoInformados.append("preco");                                                                            
         camposNaoInformados.append(",");                                                                                                                       
		}                                                                                                                 
		camposNaoInformados.append(" ]");                                                                                 
                                                                                                                       
		if (camposNaoInformados.toString().trim().length() > 4) {                                                         
			RetornoException retorno = new RetornoException(201, "201",                                                   
					MessageFormat.format("Campos obrigatórios não informados: {0}.", camposNaoInformados.toString()));    
			throw new Exception(gson.toJson(retorno));                                                                    
		}                                                                                                                 
	}                                                                                                                     
                                                                                                                       
	@Transactional                                                                                                        
	@Modifying                                                                                                            
	@Override                                                                                                             
	public Sebastiao editar(Sebastiao sebastiao) throws Exception {                                                                      
		Sebastiao retorno = new Sebastiao();                                                                                        
		try {                                                                                                             
			retorno = this.geradorRepository.save(sebastiao);                                                                  
		} catch (Exception e) {                                                                                           
			super.LOGGER.error(                                                                                           
					"Ocorreu um erro na classe: SebastiaoService >> editar(ObjetoDominio objetoDominio): " + e.getMessage());  
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
		return retorno;                                                                                                   
	}                                                                                                                     
                                                                                                                       
	@Transactional                                                                                                        
	@Override                                                                                                             
	public void deletar(Long id) throws Exception {                                                                       
		try {                                                                                                             
			this.geradorRepository.deleteById(id);                                                                        
		} catch (Exception e) {                                                                                           
			super.LOGGER.error("Ocorreu um erro na classe: SebastiaoService >> (Long id): " + e.getMessage());               
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
	}                                                                                                                     
                                                                                                                       
}                                                                                                                      
