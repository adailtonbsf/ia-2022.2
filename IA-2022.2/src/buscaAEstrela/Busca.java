package buscaAEstrela;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import mapa.Estado;
import mapa.Mapa;
import mapa.Transicao;

public class Busca {
	
	public static boolean jaFoiVisitado(PriorityQueue<No> borda, ArrayList<No> explorados, No filho) {
	    for(No no: borda)                                                                            //Se filho est� na borda
            if(no.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome()))
                return true;

            for(No no: explorados)                                                                   //Se filho est� em explorados
                if(no.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome()))
                    return true;
            
        return false;
	}
	
	public static String solucao(No no) {
		if(no.getPai() != null)
			return solucao(no.getPai())  + " -> " + no.getEstado().getNome();
		
		return no.getEstado().getNome();
	}
	
	public static String BUSCA_A_ESTRELA(Mapa mapa, String origem, String destino, HashMap<String, Integer> valoresHeuristicos) {
	    class NoComparator implements Comparator<No> {
	        HashMap<String, Integer> valoresHeuristicos;
	        public NoComparator(HashMap<String, Integer> valoresHeuristicos) {
	            this.valoresHeuristicos = valoresHeuristicos;
	        }
	        
	        @Override
	        public int compare(No no1, No no2) {
	            if(valoresHeuristicos.get(no1.getEstado().getNome().toLowerCase()) + no1.getCusto() 
	                < valoresHeuristicos.get(no2.getEstado().getNome().toLowerCase()) + no2.getCusto())
	                    return -1;
	            if(valoresHeuristicos.get(no1.getEstado().getNome().toLowerCase()) + no1.getCusto() 
                    > valoresHeuristicos.get(no2.getEstado().getNome().toLowerCase()) + no2.getCusto())
	                    return 1;
	            return 0;
	        }
	    }

		
		PriorityQueue<No> borda = new PriorityQueue<>(new NoComparator(valoresHeuristicos));
		Estado estado_inicial = mapa.getEstado(origem);
		borda.add(new No(estado_inicial));
		ArrayList<No> explorados = new ArrayList<>();
		
		while(true) {
			if(borda.size() == 0 || estado_inicial == null)											//Se a borda est� vazia	
				return "Percurso n�o encontrado!";													//Falha
			
			No no = borda.poll(); 																	//Remover elemento da borda
			
			if(no.getEstado().getNome().equalsIgnoreCase(destino))									//Se � o n� destino
				return String.format("%s (Custo Total: %dkm)", solucao(no), no.getCusto());			//Solu��o
			
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
							break;
						}	
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("BUSCA A*");
		
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
		
		System.out.println(BUSCA_A_ESTRELA(new Mapa(), args[0], args[1], valoresHeuristicos));
	}

}
