package com.game_store.si2.model;

import java.util.HashMap;
import java.util.Map;

public class Response<T> {
	private T dados;
	private int status;
	private Map<String, String> erros;
	
	public T getDados() {
		return dados;
	}
	public void setDados(T dados) {
		this.dados = dados;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Map<String, String> getErros() {
		if(this.erros == null) {
			this.erros = new HashMap<String, String>();
		}
		return erros;
	}
	
}
