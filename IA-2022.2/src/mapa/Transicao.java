package mapa;

public class Transicao {
	private Estado estadoDestino;
	private int custo;
	
	public Transicao(Estado estadoDestino, int custo) {
		this.estadoDestino = estadoDestino;
		this.custo = custo;
	}

	public Estado getEstadoDestino() {
		return estadoDestino;
	}

	public void setEstadoDestino(Estado estadoDestino) {
		this.estadoDestino = estadoDestino;
	}

	public int getCusto() {
		return custo;
	}

	public void setCusto(int custo) {
		this.custo = custo;
	}
}
