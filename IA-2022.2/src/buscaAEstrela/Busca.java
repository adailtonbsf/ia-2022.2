package buscaAEstrela;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

import mapa.Estado;
import mapa.Mapa;
import mapa.Transicao;

public class Busca {
    
    public static HashMap<String, Integer> valoresHeuristicos = new HashMap<>();
	
	public static boolean jaFoiVisitado(PriorityQueue<No> borda, ArrayList<No> explorados, No filho) {
		boolean visitado = false;
		for(No n: borda) 																			//Se filho está na borda
			if(n.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome()))
				visitado = true;
		if(!visitado)
			for(No n: explorados) 																	//Se filho está em explorados
				if(n.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome()))
					visitado = true;
		return visitado;
	}
	
	public static String solucao(No no) {
		if(no.getPai() != null)
			return solucao(no.getPai())  + " -> " + no.getEstado().getNome();
		
		return no.getEstado().getNome();
	}
	
	public static String BUSCA_A_ESTRELA(Mapa mapa, String origem, String destino) {
		PriorityQueue<No> borda = new PriorityQueue<>();
		ArrayList<No> explorados = new ArrayList<>();
		Estado estado_inicial = null;
		for(Estado estado: mapa.getEstados())
			if(estado.getNome().equalsIgnoreCase(origem)) {
				estado_inicial = estado;
				break;
			}
		borda.add(new No(estado_inicial));
		
		while(true) {
			if(borda.size() == 0 || estado_inicial == null)											//Se a borda está vazia	
				return "Percurso não encontrado!";													//Falha
			
			No no = borda.poll(); 																	//Remover elemento da borda
			
			if(no.getEstado().getNome().equalsIgnoreCase(destino))									//Se é o nó destino
				return String.format("%s (Custo Total: %dkm)", solucao(no), no.getCusto());			//Solução
			
			explorados.add(no); 																	//Adicionar ao explorados
			
			for(Transicao adj: no.getEstado().getAdjacentes()) { 									//Para cada ação aplicável
				No filho = new No(adj.getEstadoDestino(), no, adj.getCusto() + no.getCusto()); 		//Criar filho
				if(!jaFoiVisitado(borda, explorados, filho)) 										//Se o filho não está em explorados ou borda			
					borda.add(filho);																//Adicionar filho na borda
				else 
					for(No n: borda) 
						if(n.getEstado().getNome().equals(filho.getEstado().getNome()) 
								&& n.getCusto() > filho.getCusto()) {								//Se o filho está na borda com maior custo
							n.setPai(filho.getPai());
							n.setCusto(filho.getCusto());
							break;
						}	
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("BUSCA A*");
		System.out.println("Digite o nome do estado de origem:");
		String origem = scanner.nextLine();
		System.out.println("Digite o nome do estado de destino");
		String destino = scanner.nextLine();
		valoresHeuristicos.put("arad", 366);
		valoresHeuristicos.put("bucharest", 0);
		valoresHeuristicos.put("craiova", 160);
		valoresHeuristicos.put("dobreta", 242);
		valoresHeuristicos.put("eforie", 161);
		valoresHeuristicos.put("fagaras", 176);
		valoresHeuristicos.put("giurgiu", 77);
		valoresHeuristicos.put("hirsova", 151);
		valoresHeuristicos.put("iasi", 226);
		valoresHeuristicos.put("lugoj", 244);
		valoresHeuristicos.put("mehadia", 241);
		valoresHeuristicos.put("neamt", 234);
		valoresHeuristicos.put("oradea", 380);
		valoresHeuristicos.put("pitesti", 100);
		valoresHeuristicos.put("rimnicu vilcea", 193);
		valoresHeuristicos.put("sibiu", 253);
		valoresHeuristicos.put("timisoara", 329);
		valoresHeuristicos.put("urziceni", 80);
		valoresHeuristicos.put("vaslui", 199);
		valoresHeuristicos.put("zerind", 374);
		System.out.println(BUSCA_A_ESTRELA(new Mapa(), origem, destino));
		scanner.close();
	}

}
