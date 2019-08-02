package com.br.gerador.prjservicosgerados.model.services.fachada;                 
                                                                                  
import java.util.List;                                                            
                                                                                  
import com.br.gerador.prjservicosgerados.model.domain.Sebastiao;                       
                                                                                  
/**                                                                               
 * @author alan.franco                                                            
 *                                                                                
 */                                                                               
public interface SebastiaoServiceFachada {                                             
                                                                                  
	/**                                                                              
	 * @return                                                                       
	 */                                                                              
	List<Sebastiao> listarTodos() throws Exception;                                       
                                                                                  
	/**                                                                              
	 * @param id                                                                     
	 * @return                                                                       
	 */                                                                              
	Sebastiao buscarPorId(Long id) throws Exception;                                      
                                                                                  
	/**                                                                              
	 * @param gerador                                                                
	 * @return                                                                       
	 */                                                                              
	Sebastiao salvar(Sebastiao sebastiao) throws Exception;                                         
                                                                                  
	/**                                                                              
	 * @param id                                                                     
	 * @return                                                                       
	 */                                                                              
	Sebastiao editar(Sebastiao sebastiao) throws Exception;                                         
                                                                                  
	/**                                                                              
	 * @param id                                                                     
	 * @return                                                                       
	 */                                                                              
	void deletar(Long id) throws Exception;                                          
                                                                                  
}                                                                                 
