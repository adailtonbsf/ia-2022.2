package buscaGulosa;

import mapa.Estado;

public class No {
	
	private Estado estado;
	private No pai = null;
	private int custo = 0;
	
	public No(Estado estado, No pai, int custo) {
		this.estado = estado;
		this.pai = pai;
		this.custo = custo;
	}
	
	public No(Estado estado) {
		this.estado = estado;
	}

	public Estado getEstado() {
		return estado;
	}

	public No getPai() {
		return pai;
	}

	public void setPai(No pai) {
		this.pai = pai;
	}

	public int getCusto() {
		return custo;
	}
	
	public void setCusto(int custo) {
		this.custo = custo;
	}

	public void addCusto(int custo) {
		this.custo += custo;
	}
}