package buscaGulosa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import buscaAEstrela.No;
import mapa.Estado;
import mapa.Mapa;
import mapa.Transicao;

public class Busca {
	
	public static boolean jaFoiVisitado(PriorityQueue<No> borda, ArrayList<No> explorados, No filho) {
	    boolean visitado = false;
        for(No n: borda)                                                                            //Se filho está na borda
            if(n.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome())) {
                visitado = true;
                break;
            }
        if(!visitado)
            for(No n: explorados)                                                                   //Se filho está em explorados
                if(n.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome())) {
                    visitado = true;
                    break;
                }
        return visitado;
	}
	
	public static String solucao(No no) {
		if(no.getPai() != null)
			return solucao(no.getPai())  + " -> " + no.getEstado().getNome();
		
		return no.getEstado().getNome();
	}
	
	public static String BUSCA_GULOSA(Mapa mapa, String origem, String destino, HashMap<String, Integer> valoresHeuristicos) {
	    class NoComparator implements Comparator<No> {
            HashMap<String, Integer> valoresHeuristicos;
            public NoComparator(HashMap<String, Integer> valoresHeuristicos) {
                this.valoresHeuristicos = valoresHeuristicos;
            }
            
            @Override
            public int compare(No no1, No no2) {
                if(valoresHeuristicos.get(no1.getEstado().getNome().toLowerCase()) 
                        < valoresHeuristicos.get(no2.getEstado().getNome().toLowerCase()))
                    return -1;
                if(valoresHeuristicos.get(no1.getEstado().getNome().toLowerCase()) 
                        > valoresHeuristicos.get(no2.getEstado().getNome().toLowerCase()))
                    return 1;
                return 0;
            }
        }
	    
		Estado estado_inicial = null;
		for(Estado estado: mapa.getEstados())
			if(estado.getNome().equalsIgnoreCase(origem)) {
				estado_inicial = estado;
				break;
			}
		
		PriorityQueue<No> borda = new PriorityQueue<>(new NoComparator(valoresHeuristicos));
		borda.add(new No(estado_inicial));
		ArrayList<No> explorados = new ArrayList<>();
		
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
		System.out.println("BUSCA GULOSA DE MELHOR ESCOLHA");
		
		HashMap<String, Integer> valoresHeuristicos = new HashMap<>();
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
		
		System.out.println(BUSCA_GULOSA(new Mapa(), args[0], args[1], valoresHeuristicos));
	}

}
