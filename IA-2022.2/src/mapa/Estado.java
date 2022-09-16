package mapa;
import java.util.List;

public class Estado {
	private String nome;
	private List<Transicao> adjacencias;
	
	public Estado(String nome, List<Transicao> adjacencias) {
		this.nome = nome;
		this.adjacencias = adjacencias;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Transicao> getAdjacentes() {
		return adjacencias;
	}

	public void setAdjacencias(List<Transicao> adjacencias) {
		this.adjacencias = adjacencias;
	}
}
