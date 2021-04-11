package com.game_store.si2.model;

import javax.persistence.*;

import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Game {
	
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private String titulo;
	private String imgURL;
	private double preco;
	private int numVendas;
	
	public int getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getNumVendas() {
		return numVendas;
	}
	public void setNumVendas(int numVendas) {
		this.numVendas = numVendas;
	}

	
	
}
