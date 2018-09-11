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
                                                                                                                       
import com.br.gerador.prjservicosgerados.model.domain.Caneta;                                                            
import com.br.gerador.prjservicosgerados.model.domain.RetornoException;                                                
import com.br.gerador.prjservicosgerados.model.repository.CanetaRepository;                                              
import com.br.gerador.prjservicosgerados.model.services.fachada.CanetaServiceFachada;                                    
import com.google.gson.Gson;                                                                                           
                                                                                                                       
/**                                                                                                                    
 * @author alan.franco                                                                                                 
 *                                                                                                                     
 */                                                                                                                    
@Service                                                                                                               
public class CanetaService extends BaseService implements CanetaServiceFachada {                                           
                                                                                                                       
	/**                                                                                                                   
	 * Injetando as dependências da interface de abstração do spring data                                                 
	 * responsável pelo acesso aos dados deste objeto                                                                     
	 */                                                                                                                   
	@Autowired                                                                                                            
	private CanetaRepository geradorRepository;                                                                             
                                                                                                                       
	private Gson gson;                                                                                                    
                                                                                                                       
	private RetornoException retornoException;                                                                            
                                                                                                                       
	public CanetaService() {                                                                                                
		super();                                                                                                          
		this.gson = new Gson();                                                                                           
		this.retornoException = new RetornoException(500, "500", "Desculpe o transtorno! Ocorreu um erro interno.");      
	}                                                                                                                     
                                                                                                                       
	@Override                                                                                                             
	public List<Caneta> listarTodos() throws Exception {                                                                    
		List<Caneta> retorno = new ArrayList<Caneta>();                                                                       
		try {                                                                                                             
			retorno = this.geradorRepository.findAll();                                                                   
		} catch (Exception e) {                                                                                           
			super.LOGGER.error("Ocorreu um erro na classe: CanetaService >> listarTodos(): " + e.getMessage());             
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
		return retorno;                                                                                                   
	}                                                                                                                     
                                                                                                                       
	@Override                                                                                                             
	public Caneta buscarPorId(Long id) throws Exception {                                                                   
		Optional<Caneta> retorno = null;                                                                                    
		try {                                                                                                             
			retorno = this.geradorRepository.findById(id);                                                                
		} catch (Exception e) {                                                                                           
			super.LOGGER.error("Ocorreu um erro na classe: CanetaService >> buscarPorId(Long id): " + e.getMessage());      
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
		return retorno.get();                                                                                             
	}                                                                                                                     
                                                                                                                       
	@Transactional                                                                                                        
	@Override                                                                                                             
	public Caneta salvar(Caneta caneta) throws Exception {                                                                      
		Caneta retorno = new Caneta();                                                                                        
		try {                                                                                                             
			validarCamposObrigatorioSalvar(caneta);                                                                         
			retorno = this.geradorRepository.save(caneta);                                                                  
		} catch (Exception e) {                                                                                           
			super.LOGGER.error(                                                                                           
					"Ocorreu um erro na classe: CanetaService >> salvar(ObjetoDominio objetoDominio): " + e.getMessage());  
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
		return retorno;                                                                                                   
	}                                                                                                                     
                                                                                                                       
	/**                                                                                                                   
	 * @param caneta                                                                                                        
	 *                                                                                                                    
	 *             Método responsável por validar se todos os campos obrigatórios                                         
	 *             foram informados antes da operação de salva                                                            
	 * @throws Exception                                                                                                  
	 */                                                                                                                   
	private void validarCamposObrigatorioSalvar(Caneta caneta) throws Exception {                                             
		StringBuilder camposNaoInformados = new StringBuilder();                                                          
                                                                                                                       
		camposNaoInformados.append("[ ");                                                                                 
		if (caneta.getPreco() <= 0) {                                                                         
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
	public Caneta editar(Caneta caneta) throws Exception {                                                                      
		Caneta retorno = new Caneta();                                                                                        
		try {                                                                                                             
			retorno = this.geradorRepository.save(caneta);                                                                  
		} catch (Exception e) {                                                                                           
			super.LOGGER.error(                                                                                           
					"Ocorreu um erro na classe: CanetaService >> editar(ObjetoDominio objetoDominio): " + e.getMessage());  
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
			super.LOGGER.error("Ocorreu um erro na classe: CanetaService >> (Long id): " + e.getMessage());               
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
	}                                                                                                                     
                                                                                                                       
}                                                                                                                      
