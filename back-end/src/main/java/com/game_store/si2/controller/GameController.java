package com.game_store.si2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game_store.si2.model.Game;
import com.game_store.si2.model.Response;
import com.game_store.si2.service.GameService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(path = "/v1/games", produces = "application/json")
public class GameController {
	
	@Autowired
	private GameService service;
	
	//GET todos
	@ApiOperation(value = "Retorna todos os games cadastrados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acesssar este recurso"),
			@ApiResponse(code = 404, message = "Lista não encontrada"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@GetMapping()
	public Response<List<Game>> listGames(){
		Response<List<Game>> response =  new Response<List<Game>>();
		
		try {
			response.setDados(service.getAllGames());
			response.setStatus(HttpStatus.OK.value());
			
			return response;
		} catch (Exception e){
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getErros().put("1", "Falha ao obter games");
			
			return response;
		}
		
	}
	
	//GET por ID
	@ApiOperation(value = "Retorna um game cadastrado específico")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acesssar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@GetMapping("/{id}")
	public Response<Game> getGame(@PathVariable int id) {
		
		Response<Game> response = new Response<Game>();
		
		try {
			response.setDados(service.getGameById(id));
			response.setStatus(HttpStatus.OK.value());
			
			return response;
			
		} catch (Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getErros().put("1", "Falha ao obter game " + id);
			
			return response;
		}
		
	}
	
	//Vender jogo
	@ApiOperation(value = "Realiza a venda de uma unidade de um jogo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acesssar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@GetMapping("/{id}/buy")
	public Response<Game> sellGame(@PathVariable int id) {
		
		Response<Game> response = new Response<Game>();
		
		try {
			response.setDados(service.venderUnidade(id));
			response.setStatus(HttpStatus.OK.value());
			
			return response;
			
		} catch (Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getErros().put("1", "Falha ao vender game " + id);
			
			return response;
		}
		
	}

	//Vender múltiplas cópias do mesmo jogo
	@ApiOperation(value = "Realiza a venda de múltiplas unidade de um game")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acesssar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@GetMapping("/{id}/buy/{quant}")
	public Response<Game> sellMultipleGames(@PathVariable int id, @PathVariable int quant) {
		
		Response<Game> response = new Response<Game>();
		
		try {
			response.setDados(service.venderMultiplos(id, quant));
			response.setStatus(HttpStatus.OK.value());
			
			return response;
			
		} catch (Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getErros().put("1", "Falha ao vender game " + id);
			
			return response;
		}
		
	}
	
	@ApiOperation(value = "Cadastra um novo game no sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acesssar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@PostMapping()
	public ResponseEntity<Response<Game>> addGame (@Valid @RequestBody Game game, BindingResult result) {
		Response<Game> response = new Response<Game>();
		
		try {
			if(result.hasErrors()) {
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				for(ObjectError error : result.getAllErrors()) {
					String key = String.valueOf(response.getErros().size() + 1);
					
					response.getErros().put(key, error.getDefaultMessage());
				}
				return ResponseEntity.ok(response);
			}
			
			response.setStatus(HttpStatus.OK.value());
			service.addNewGame(game);
			response.setDados(game);
			
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getErros().put("1", "Falha ao cadastrar novo jogo");
			
			return ResponseEntity.ok(response);
		}
	}
	
	@ApiOperation(value = "Deleta um game do sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acesssar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@DeleteMapping("/{id}")
	public Response<String> deletarGame(@PathVariable int id) {
		
		Response<String> response = new Response<String>();
		
		try {
			service.deleteGameById(id);
			response.setDados("Game removido com sucesso");
			response.setStatus(HttpStatus.OK.value());
			return response;
		}
		catch(Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return response;
		}
	}
	
	@ApiOperation(value = "Atualiza os dados de um game cadastrado específico")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acesssar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Response<Game>> updateGame(@PathVariable int id, @Valid @RequestBody Game game, BindingResult result) {
		Response<Game> response = new Response<Game>();
		
		try {
			if(result.hasErrors()) {
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				for(ObjectError error : result.getAllErrors()) {
					String key = String.valueOf(response.getErros().size() + 1);
					
					response.getErros().put(key, error.getDefaultMessage());
				}
				return ResponseEntity.ok(response);
			}
			
			response.setStatus(HttpStatus.OK.value());
			service.updateGame(id, game);
			response.setDados(game);
			
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getErros().put("1", "Falha ao cadastrar novo jogo");
			
			return ResponseEntity.ok(response);
		}
	}
	
}
