package com.br.gerador.prjservicosgerados.controller;                                                                    
                                                                                                                         
import org.springframework.beans.factory.annotation.Autowired;                                                           
import org.springframework.web.bind.annotation.PathVariable;                                                             
import org.springframework.web.bind.annotation.RequestBody;                                                              
import org.springframework.web.bind.annotation.RequestMapping;                                                           
import org.springframework.web.bind.annotation.RequestMethod;                                                            
import org.springframework.web.bind.annotation.RestController;                                                           
                                                                                                                         
import com.br.gerador.prjservicosgerados.model.domain.Carro;                                                              
import com.br.gerador.prjservicosgerados.model.domain.RetornoException;                                                  
import com.br.gerador.prjservicosgerados.model.services.fachada.CarroServiceFachada;                                      
import com.google.gson.Gson;                                                                                             
                                                                                                                         
import io.swagger.annotations.ApiOperation;                                                                              
                                                                                                                         
/**                                                                                                                      
 * @author alan.franco                                                                                                   
 *                                                                                                                       
 */                                                                                                                      
@RestController                                                                                                          
@RequestMapping("/carro")                                                                                                 
public class CarroController extends BaseController {                                                                     
                                                                                                                         
	@Autowired                                                                                                              
	private CarroServiceFachada carroServiceFachada;                                                                          
                                                                                                                         
	private Gson gson;                                                                                                      
                                                                                                                         
	public CarroController() {                                                                                               
		super();                                                                                                            
		this.gson = new Gson();                                                                                             
	}                                                                                                                       
                                                                                                                         
	@ApiOperation(value = "Recuperar e listar todos os registos.")                                                          
	@RequestMapping(value = "/listar-todos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")      
	public String listarTodos() {                                                                                           
		try {                                                                                                               
			return this.gson.toJson(carroServiceFachada.listarTodos());                                                      
		} catch (Exception e) {                                                                                             
			return gson.toJson(e.getMessage());                                                                             
		}                                                                                                                   
	}                                                                                                                       
                                                                                                                         
	@ApiOperation(value = "Recuperar um registro de acordo com id passado como parâmetro.")                                 
	@RequestMapping(value = "/buscar-por-id", method = RequestMethod.GET, produces = "application/json; charset=utf-8")     
	public String buscarPorId(Long id) {                                                                                    
		try {                                                                                                               
			return this.gson.toJson(carroServiceFachada.buscarPorId(id));                                                    
		} catch (Exception e) {                                                                                             
			return gson.toJson(e.getMessage());                                                                             
		}                                                                                                                   
	}                                                                                                                       
                                                                                                                         
	@ApiOperation(value = "Salvar um novo registro no banco.")                                                              
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json; charset=utf-8")           
	public String salvar(Carro carro) {                                                                                       
		try {                                                                                                               
			return gson.toJson(carroServiceFachada.salvar(carro));                                                            
		} catch (Exception e) {                                                                                             
			return gson.toJson(e.getMessage());                                                                             
		}                                                                                                                   
	}                                                                                                                       
                                                                                                                         
	@ApiOperation(value = "Editar um registro de acordo com o id informado na requisição.")                                 
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")       
	public String editar(@PathVariable("id") Long id, @RequestBody Carro carro) {                                             
		try {                                                                                                               
			carro.setId(id);                                                                                                 
			return gson.toJson(carroServiceFachada.editar(carro));                                                            
		} catch (Exception e) {                                                                                             
			return gson.toJson(e.getMessage());                                                                             
		}                                                                                                                   
	}                                                                                                                       
                                                                                                                         
	@ApiOperation(value = "Deletar um registro de acordo com o id informado na requisição.")                                
	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")   
	public String deletar(@PathVariable("id") Long id) {                                                                    
		try {                                                                                                               
			carroServiceFachada.deletar(id);                                                                                 
			return gson.toJson(new RetornoException(200, "200", "Deleção ocorreu com sucesso!"));                           
		} catch (Exception e) {                                                                                             
			return gson.toJson(e.getMessage());                                                                             
		}                                                                                                                   
	}                                                                                                                       
                                                                                                                         
}                                                                                                                        
