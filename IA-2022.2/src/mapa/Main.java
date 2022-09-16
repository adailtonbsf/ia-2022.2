package mapa;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Mapa m = new Mapa();
		List<Estado> estados = m.getEstados();
		for(int i = 0; i < estados.size(); i++) {
			System.out.println("Estado: " + estados.get(i).getNome());
			List<Transicao> adjacencias = estados.get(i).getAdjacentes();
			for(int j = 0; j < adjacencias.size(); j++) {
				System.out.println("    Estado adjacente: " + adjacencias.get(j).getEstadoDestino().getNome() + " | Custo: " + adjacencias.get(j).getCusto());
			}
		}
	}

}
