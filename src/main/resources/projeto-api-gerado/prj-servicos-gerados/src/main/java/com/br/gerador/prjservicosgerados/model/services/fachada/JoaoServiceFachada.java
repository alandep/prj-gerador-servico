package com.br.gerador.prjservicosgerados.model.services.fachada;                 
                                                                                  
import java.util.List;                                                            
                                                                                  
import com.br.gerador.prjservicosgerados.model.domain.Joao;                       
                                                                                  
/**                                                                               
 * @author alan.franco                                                            
 *                                                                                
 */                                                                               
public interface JoaoServiceFachada {                                             
                                                                                  
	/**                                                                              
	 * @return                                                                       
	 */                                                                              
	List<Joao> listarTodos() throws Exception;                                       
                                                                                  
	/**                                                                              
	 * @param id                                                                     
	 * @return                                                                       
	 */                                                                              
	Joao buscarPorId(Long id) throws Exception;                                      
                                                                                  
	/**                                                                              
	 * @param gerador                                                                
	 * @return                                                                       
	 */                                                                              
	Joao salvar(Joao joao) throws Exception;                                         
                                                                                  
	/**                                                                              
	 * @param id                                                                     
	 * @return                                                                       
	 */                                                                              
	Joao editar(Joao joao) throws Exception;                                         
                                                                                  
	/**                                                                              
	 * @param id                                                                     
	 * @return                                                                       
	 */                                                                              
	void deletar(Long id) throws Exception;                                          
                                                                                  
}                                                                                 
