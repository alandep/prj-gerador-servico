package com.br.gerador.prjservicosgerados.controller;                                                                    
                                                                                                                         
import org.springframework.beans.factory.annotation.Autowired;                                                           
import org.springframework.web.bind.annotation.PathVariable;                                                             
import org.springframework.web.bind.annotation.RequestBody;                                                              
import org.springframework.web.bind.annotation.RequestMapping;                                                           
import org.springframework.web.bind.annotation.RequestMethod;                                                            
import org.springframework.web.bind.annotation.RestController;                                                           
                                                                                                                         
import com.br.gerador.prjservicosgerados.model.domain.Marcos;                                                              
import com.br.gerador.prjservicosgerados.model.domain.RetornoException;                                                  
import com.br.gerador.prjservicosgerados.model.services.fachada.MarcosServiceFachada;                                      
import com.google.gson.Gson;                                                                                             
                                                                                                                         
import io.swagger.annotations.ApiOperation;                                                                              
                                                                                                                         
/**                                                                                                                      
 * @author alan.franco                                                                                                   
 *                                                                                                                       
 */                                                                                                                      
@RestController                                                                                                          
@RequestMapping("/marcos")                                                                                                 
public class MarcosController extends BaseController {                                                                     
                                                                                                                         
	@Autowired                                                                                                              
	private MarcosServiceFachada marcosServiceFachada;                                                                          
                                                                                                                         
	private Gson gson;                                                                                                      
                                                                                                                         
	public MarcosController() {                                                                                               
		super();                                                                                                            
		this.gson = new Gson();                                                                                             
	}                                                                                                                       
                                                                                                                         
	@ApiOperation(value = "Recuperar e listar todos os registos.")                                                          
	@RequestMapping(value = "/listar-todos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")      
	public String listarTodos() {                                                                                           
		try {                                                                                                               
			return this.gson.toJson(marcosServiceFachada.listarTodos());                                                      
		} catch (Exception e) {                                                                                             
			return gson.toJson(e.getMessage());                                                                             
		}                                                                                                                   
	}                                                                                                                       
                                                                                                                         
	@ApiOperation(value = "Recuperar um registro de acordo com id passado como parâmetro.")                                 
	@RequestMapping(value = "/buscar-por-id", method = RequestMethod.GET, produces = "application/json; charset=utf-8")     
	public String buscarPorId(Long id) {                                                                                    
		try {                                                                                                               
			return this.gson.toJson(marcosServiceFachada.buscarPorId(id));                                                    
		} catch (Exception e) {                                                                                             
			return gson.toJson(e.getMessage());                                                                             
		}                                                                                                                   
	}                                                                                                                       
                                                                                                                         
	@ApiOperation(value = "Salvar um novo registro no banco.")                                                              
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json; charset=utf-8")           
	public String salvar(Marcos marcos) {                                                                                       
		try {                                                                                                               
			return gson.toJson(marcosServiceFachada.salvar(marcos));                                                            
		} catch (Exception e) {                                                                                             
			return gson.toJson(e.getMessage());                                                                             
		}                                                                                                                   
	}                                                                                                                       
                                                                                                                         
	@ApiOperation(value = "Editar um registro de acordo com o id informado na requisição.")                                 
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")       
	public String editar(@PathVariable("id") Long id, @RequestBody Marcos marcos) {                                             
		try {                                                                                                               
			marcos.setId(id);                                                                                                 
			return gson.toJson(marcosServiceFachada.editar(marcos));                                                            
		} catch (Exception e) {                                                                                             
			return gson.toJson(e.getMessage());                                                                             
		}                                                                                                                   
	}                                                                                                                       
                                                                                                                         
	@ApiOperation(value = "Deletar um registro de acordo com o id informado na requisição.")                                
	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")   
	public String deletar(@PathVariable("id") Long id) {                                                                    
		try {                                                                                                               
			marcosServiceFachada.deletar(id);                                                                                 
			return gson.toJson(new RetornoException(200, "200", "Deleção ocorreu com sucesso!"));                           
		} catch (Exception e) {                                                                                             
			return gson.toJson(e.getMessage());                                                                             
		}                                                                                                                   
	}                                                                                                                       
                                                                                                                         
}                                                                                                                        
