package com.br.gerador.prjservicosgerados.model.repository;                             
                                                                                        
import java.io.Serializable;                                                            
                                                                                        
import org.springframework.data.jpa.repository.JpaRepository;                           
import org.springframework.stereotype.Repository;                                       
                                                                                        
import com.br.gerador.prjservicosgerados.model.domain.Caneta;                             
                                                                                        
/**                                                                                     
 * @author alan.franco                                                                  
 *                                                                                      
 *         Classe de acesso aos dados de faixas de registro.                            
 *                                                                                      
 */                                                                                     
@Repository                                                                             
public interface CanetaRepository extends JpaRepository<Caneta, Long>, Serializable {       
                                                                                        
}                                                                                       
