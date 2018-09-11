package com.br.gerador.prjservicosgerados.model.services.fachada;                 
                                                                                  
import java.util.List;                                                            
                                                                                  
import com.br.gerador.prjservicosgerados.model.domain.Carro;                       
                                                                                  
/**                                                                               
 * @author alan.franco                                                            
 *                                                                                
 */                                                                               
public interface CarroServiceFachada {                                             
                                                                                  
	/**                                                                              
	 * @return                                                                       
	 */                                                                              
	List<Carro> listarTodos() throws Exception;                                       
                                                                                  
	/**                                                                              
	 * @param id                                                                     
	 * @return                                                                       
	 */                                                                              
	Carro buscarPorId(Long id) throws Exception;                                      
                                                                                  
	/**                                                                              
	 * @param gerador                                                                
	 * @return                                                                       
	 */                                                                              
	Carro salvar(Carro carro) throws Exception;                                         
                                                                                  
	/**                                                                              
	 * @param id                                                                     
	 * @return                                                                       
	 */                                                                              
	Carro editar(Carro carro) throws Exception;                                         
                                                                                  
	/**                                                                              
	 * @param id                                                                     
	 * @return                                                                       
	 */                                                                              
	void deletar(Long id) throws Exception;                                          
                                                                                  
}                                                                                 
