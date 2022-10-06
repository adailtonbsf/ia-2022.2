package buscaDeCustoUniforme;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import mapa.Estado;
import mapa.Mapa;
import mapa.Transicao;

public class Busca {
	
	public static boolean jaFoiVisitado(PriorityQueue<No> borda, ArrayList<No> explorados, No filho) {
		boolean visitado = false;
		for(No n: borda) 																			//Se filho est� na borda
			if(n.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome()))
				visitado = true;
		if(!visitado)
			for(No n: explorados) 																	//Se filho est� em explorados
				if(n.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome()))
					visitado = true;
		return visitado;
	}
	
	public static String solucao(No no) {
		if(no.getPai() != null)
			return solucao(no.getPai())  + " -> " + no.getEstado().getNome();
		
		return no.getEstado().getNome();
	}
	
	public static String BUSCA_EM_LARGURA(Mapa m, String origem, String destino) {
		PriorityQueue<No> borda = new PriorityQueue<>();
		ArrayList<No> explorados = new ArrayList<>();
		Estado est_inicial = null;
		for(Estado estado: m.getEstados())
			if(estado.getNome().equalsIgnoreCase(origem)) {
				est_inicial = estado;
				break;
			}
		borda.add(new No(est_inicial, null, 0));
		
		while(true) {
			if(borda.size() == 0)																	//Se a borda est� vazia	
				return "Percurso n�o encontrado!";													//Falha
			
			No no = borda.poll(); 																	//Remover elemento da borda
			
			if(no.getEstado().getNome().equalsIgnoreCase(destino))									//Se � o n� destino
				return solucao(no) + " (Custo Total: " + no.getCusto() + "km)";						//Solu��o
			
			explorados.add(no); 																	//Adicionar ao explorados
			
			for(Transicao adj: no.getEstado().getAdjacentes()) { 									//Para cada a��o aplic�vel
				No filho = new No(adj.getEstadoDestino(), no, adj.getCusto() + no.getCusto()); 		//Criar filho
				if(!jaFoiVisitado(borda, explorados, filho)) 										//Se o filho n�o est� em explorados ou borda			
					borda.add(filho);																//Adicionar filho na borda
				else 
					for(No n: borda) 
						if(n.getEstado().getNome().equals(filho.getEstado().getNome()) 
								&& n.getCusto() > filho.getCusto()) {								//Se o filho est� na borda com maior custo
							n.setPai(filho.getPai());
							n.setCusto(filho.getCusto());
						}	
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome do estado de origem:");
		String origem = scanner.nextLine();
		System.out.println("Digite o nome do estado de destino");
		String destino = scanner.nextLine();
		System.out.println(BUSCA_EM_LARGURA(new Mapa(), origem, destino));
		scanner.close();
	}

}
