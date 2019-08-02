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
                                                                                                                       
import com.br.gerador.prjservicosgerados.model.domain.PhoneOuvido;                                                            
import com.br.gerador.prjservicosgerados.model.domain.RetornoException;                                                
import com.br.gerador.prjservicosgerados.model.repository.PhoneOuvidoRepository;                                              
import com.br.gerador.prjservicosgerados.model.services.fachada.PhoneOuvidoServiceFachada;                                    
import com.google.gson.Gson;                                                                                           
                                                                                                                       
/**                                                                                                                    
 * @author alan.franco                                                                                                 
 *                                                                                                                     
 */                                                                                                                    
@Service                                                                                                               
public class PhoneOuvidoService extends BaseService implements PhoneOuvidoServiceFachada {                                           
                                                                                                                       
	/**                                                                                                                   
	 * Injetando as dependências da interface de abstração do spring data                                                 
	 * responsável pelo acesso aos dados deste objeto                                                                     
	 */                                                                                                                   
	@Autowired                                                                                                            
	private PhoneOuvidoRepository geradorRepository;                                                                             
                                                                                                                       
	private Gson gson;                                                                                                    
                                                                                                                       
	private RetornoException retornoException;                                                                            
                                                                                                                       
	public PhoneOuvidoService() {                                                                                                
		super();                                                                                                          
		this.gson = new Gson();                                                                                           
		this.retornoException = new RetornoException(500, "500", "Desculpe o transtorno! Ocorreu um erro interno.");      
	}                                                                                                                     
                                                                                                                       
	@Override                                                                                                             
	public List<PhoneOuvido> listarTodos() throws Exception {                                                                    
		List<PhoneOuvido> retorno = new ArrayList<PhoneOuvido>();                                                                       
		try {                                                                                                             
			retorno = this.geradorRepository.findAll();                                                                   
		} catch (Exception e) {                                                                                           
			super.LOGGER.error("Ocorreu um erro na classe: PhoneOuvidoService >> listarTodos(): " + e.getMessage());             
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
		return retorno;                                                                                                   
	}                                                                                                                     
                                                                                                                       
	@Override                                                                                                             
	public PhoneOuvido buscarPorId(Long id) throws Exception {                                                                   
		Optional<PhoneOuvido> retorno = null;                                                                                    
		try {                                                                                                             
			retorno = this.geradorRepository.findById(id);                                                                
		} catch (Exception e) {                                                                                           
			super.LOGGER.error("Ocorreu um erro na classe: PhoneOuvidoService >> buscarPorId(Long id): " + e.getMessage());      
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
		return retorno.get();                                                                                             
	}                                                                                                                     
                                                                                                                       
	@Transactional                                                                                                        
	@Override                                                                                                             
	public PhoneOuvido salvar(PhoneOuvido phoneouvido) throws Exception {                                                                      
		PhoneOuvido retorno = new PhoneOuvido();                                                                                        
		try {                                                                                                             
			validarCamposObrigatorioSalvar(phoneouvido);                                                                         
			retorno = this.geradorRepository.save(phoneouvido);                                                                  
		} catch (Exception e) {                                                                                           
			super.LOGGER.error(                                                                                           
					"Ocorreu um erro na classe: PhoneOuvidoService >> salvar(ObjetoDominio objetoDominio): " + e.getMessage());  
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
		return retorno;                                                                                                   
	}                                                                                                                     
                                                                                                                       
	/**                                                                                                                   
	 * @param phoneouvido                                                                                                        
	 *                                                                                                                    
	 *             Método responsável por validar se todos os campos obrigatórios                                         
	 *             foram informados antes da operação de salva                                                            
	 * @throws Exception                                                                                                  
	 */                                                                                                                   
	private void validarCamposObrigatorioSalvar(PhoneOuvido phoneouvido) throws Exception {                                             
		StringBuilder camposNaoInformados = new StringBuilder();                                                          
                                                                                                                       
		camposNaoInformados.append("[ ");                                                                                 
		if (phoneouvido.getPreco() <= 0) {                                                                         
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
	public PhoneOuvido editar(PhoneOuvido phoneouvido) throws Exception {                                                                      
		PhoneOuvido retorno = new PhoneOuvido();                                                                                        
		try {                                                                                                             
			retorno = this.geradorRepository.save(phoneouvido);                                                                  
		} catch (Exception e) {                                                                                           
			super.LOGGER.error(                                                                                           
					"Ocorreu um erro na classe: PhoneOuvidoService >> editar(ObjetoDominio objetoDominio): " + e.getMessage());  
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
			super.LOGGER.error("Ocorreu um erro na classe: PhoneOuvidoService >> (Long id): " + e.getMessage());               
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
	}                                                                                                                     
                                                                                                                       
}                                                                                                                      
