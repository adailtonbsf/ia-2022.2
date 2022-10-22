package buscaEmLargura;

import java.util.ArrayList;
import java.util.LinkedList;
import mapa.Estado;
import mapa.Mapa;
import mapa.Transicao;

public class Busca {
	
	public static boolean jaFoiVisitado(LinkedList<No> borda, ArrayList<No> explorados, No filho) {
		boolean visitado = false;
		for(No n: borda) 														//Se filho est� na borda
			if(n.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome()))
				visitado = true;
		if(!visitado)
			for(No n: explorados) 												//Se filho est� em explorados
				if(n.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome()))
					visitado = true;
		return visitado;
	}
	
	public static String solucao(No no) {
		if(no.getPai() != null)
			return solucao(no.getPai())  + " -> " + no.getEstado().getNome();

		return no.getEstado().getNome();
	}
	
	public static String BUSCA_EM_LARGURA(Mapa mapa, String origem, String destino) {
		LinkedList<No> borda = new LinkedList<>();
		ArrayList<No> explorados = new ArrayList<>();
		Estado estado_inicial = null;
		for(Estado estado: mapa.getEstados())
			if(estado.getNome().equalsIgnoreCase(origem)) {
				estado_inicial = estado;
				break;
			}
		borda.push(new No(estado_inicial));
		
		while(true) {
			if(borda.size() == 0 || estado_inicial == null)													//Se a borda est� vazia	
				return "Percurso n�o encontrado!";															//Falha
			
			No no = borda.removeFirst(); 																	//Remover elemento da borda
			explorados.add(no); 																			//Adicionar ao explorados
			for(Transicao adj: no.getEstado().getAdjacentes()) { 											//Para cada a��o aplic�vel
				No filho = new No(adj.getEstadoDestino(), no, adj.getCusto() + no.getCusto()); 				//Criar filho
				if(!jaFoiVisitado(borda, explorados, filho)) 												//Se o filho n�o est� em explorados ou borda
					if(filho.getEstado().getNome().equalsIgnoreCase(destino)) 								//Se o filho � o destino
						return String.format("%s (Custo Total: %dkm)", solucao(filho), filho.getCusto());	//Solu��o
					else borda.push(filho);																	//Adicionar filho na borda
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("BUSCA EM LARGURA");
		System.out.println(BUSCA_EM_LARGURA(new Mapa(), args[0], args[1]));
	}

}
