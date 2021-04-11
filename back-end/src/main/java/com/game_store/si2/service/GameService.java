package com.game_store.si2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game_store.si2.model.Game;
import com.game_store.si2.repository.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository repository;

	public List<Game> getAllGames(){
		return repository.findAll(); 
	}

	public Game getGameById(int id) {
		return repository.findById(id).orElse(null);
	}

	public Game addNewGame(Game game) {
		return repository.save(game);
	}

	public String deleteGameById(int id) {
		repository.deleteById(id);
		return id + " excluído com sucesso!";
	}

	public Game updateGame(int id, Game game) {
		Game oldGame = repository.findById(id).orElse(null);
		oldGame.setTitulo(game.getTitulo());
		oldGame.setPreco(game.getPreco());
		oldGame.setImgURL(game.getImgURL());
		return repository.save(oldGame);
	}

	public Game venderUnidade(int id) {
		return this.venderMultiplos(id, 1);
	}
	
	public Game venderMultiplos(int id, int num) {
		Game game = repository.findById(id).orElse(null);
		game.setNumVendas(game.getNumVendas() + num);
		return repository.save(game);
	}
	
}
