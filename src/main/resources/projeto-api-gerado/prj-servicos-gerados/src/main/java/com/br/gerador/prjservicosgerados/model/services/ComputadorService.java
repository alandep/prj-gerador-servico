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
                                                                                                                       
import com.br.gerador.prjservicosgerados.model.domain.Computador;                                                            
import com.br.gerador.prjservicosgerados.model.domain.RetornoException;                                                
import com.br.gerador.prjservicosgerados.model.repository.ComputadorRepository;                                              
import com.br.gerador.prjservicosgerados.model.services.fachada.ComputadorServiceFachada;                                    
import com.google.gson.Gson;                                                                                           
                                                                                                                       
/**                                                                                                                    
 * @author alan.franco                                                                                                 
 *                                                                                                                     
 */                                                                                                                    
@Service                                                                                                               
public class ComputadorService extends BaseService implements ComputadorServiceFachada {                                           
                                                                                                                       
	/**                                                                                                                   
	 * Injetando as dependências da interface de abstração do spring data                                                 
	 * responsável pelo acesso aos dados deste objeto                                                                     
	 */                                                                                                                   
	@Autowired                                                                                                            
	private ComputadorRepository geradorRepository;                                                                             
                                                                                                                       
	private Gson gson;                                                                                                    
                                                                                                                       
	private RetornoException retornoException;                                                                            
                                                                                                                       
	public ComputadorService() {                                                                                                
		super();                                                                                                          
		this.gson = new Gson();                                                                                           
		this.retornoException = new RetornoException(500, "500", "Desculpe o transtorno! Ocorreu um erro interno.");      
	}                                                                                                                     
                                                                                                                       
	@Override                                                                                                             
	public List<Computador> listarTodos() throws Exception {                                                                    
		List<Computador> retorno = new ArrayList<Computador>();                                                                       
		try {                                                                                                             
			retorno = this.geradorRepository.findAll();                                                                   
		} catch (Exception e) {                                                                                           
			super.LOGGER.error("Ocorreu um erro na classe: ComputadorService >> listarTodos(): " + e.getMessage());             
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
		return retorno;                                                                                                   
	}                                                                                                                     
                                                                                                                       
	@Override                                                                                                             
	public Computador buscarPorId(Long id) throws Exception {                                                                   
		Optional<Computador> retorno = null;                                                                                    
		try {                                                                                                             
			retorno = this.geradorRepository.findById(id);                                                                
		} catch (Exception e) {                                                                                           
			super.LOGGER.error("Ocorreu um erro na classe: ComputadorService >> buscarPorId(Long id): " + e.getMessage());      
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
		return retorno.get();                                                                                             
	}                                                                                                                     
                                                                                                                       
	@Transactional                                                                                                        
	@Override                                                                                                             
	public Computador salvar(Computador computador) throws Exception {                                                                      
		Computador retorno = new Computador();                                                                                        
		try {                                                                                                             
			validarCamposObrigatorioSalvar(computador);                                                                         
			retorno = this.geradorRepository.save(computador);                                                                  
		} catch (Exception e) {                                                                                           
			super.LOGGER.error(                                                                                           
					"Ocorreu um erro na classe: ComputadorService >> salvar(ObjetoDominio objetoDominio): " + e.getMessage());  
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
		return retorno;                                                                                                   
	}                                                                                                                     
                                                                                                                       
	/**                                                                                                                   
	 * @param computador                                                                                                        
	 *                                                                                                                    
	 *             Método responsável por validar se todos os campos obrigatórios                                         
	 *             foram informados antes da operação de salva                                                            
	 * @throws Exception                                                                                                  
	 */                                                                                                                   
	private void validarCamposObrigatorioSalvar(Computador computador) throws Exception {                                             
		StringBuilder camposNaoInformados = new StringBuilder();                                                          
                                                                                                                       
		camposNaoInformados.append("[ ");                                                                                 
		if (computador.getPreco() <= 0) {                                                                         
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
	public Computador editar(Computador computador) throws Exception {                                                                      
		Computador retorno = new Computador();                                                                                        
		try {                                                                                                             
			retorno = this.geradorRepository.save(computador);                                                                  
		} catch (Exception e) {                                                                                           
			super.LOGGER.error(                                                                                           
					"Ocorreu um erro na classe: ComputadorService >> editar(ObjetoDominio objetoDominio): " + e.getMessage());  
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
			super.LOGGER.error("Ocorreu um erro na classe: ComputadorService >> (Long id): " + e.getMessage());               
			throw new Exception(gson.toJson(retornoException));                                                           
		}                                                                                                                 
	}                                                                                                                     
                                                                                                                       
}                                                                                                                      
