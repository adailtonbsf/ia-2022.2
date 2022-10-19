package buscaAEstrela;

import java.util.HashMap;

import mapa.Estado;

public class No implements Comparable<No>{
	
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
	
	public int compareTo(No no) {
        HashMap<String, Integer> valoresHeuristicos = Busca.valoresHeuristicos;
        int no1 = valoresHeuristicos.get(this.estado.getNome().toLowerCase()) + this.custo;
        int no2 = valoresHeuristicos.get(no.getEstado().getNome().toLowerCase()) + no.getCusto();
        if(no1 < no2)
            return -1;
        if(no1 > no2)
            return 1;
        return 0;
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