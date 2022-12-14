package buscaDeCustoUniforme;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import buscaAEstrela.No;
import mapa.Estado;
import mapa.Mapa;
import mapa.Transicao;

public class Busca {
	
    public static boolean jaFoiVisitado(PriorityQueue<No> borda, ArrayList<No> explorados, No filho) {
        for(No no: borda)                                                                            //Se filho est? na borda
            if(no.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome()))
                return true;

            for(No no: explorados)                                                                   //Se filho est? em explorados
                if(no.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome()))
                    return true;
            
        return false;
    }
	
	public static String solucao(No no) {
		if(no.getPai() != null)
			return solucao(no.getPai())  + " -> " + no.getEstado().getNome();
		
		return no.getEstado().getNome();
	}
	
	public static String BUSCA_DE_CUSTO_UNIFORME(Mapa mapa, String origem, String destino) {
	    class NoComparator implements Comparator<No> {            
            @Override
            public int compare(No no1, No no2) {
                if(no1.getCusto() < no2.getCusto())
                        return -1;
                if(no1.getCusto() > no2.getCusto())
                        return 1;
                return 0;
            }
        }
	    
		PriorityQueue<No> borda = new PriorityQueue<>(new NoComparator());
		Estado estado_inicial = mapa.getEstado(origem);
		borda.add(new No(estado_inicial));
		ArrayList<No> explorados = new ArrayList<>();
		
		while(true) {
			if(borda.size() == 0 || estado_inicial == null)											//Se a borda est? vazia
				return "Percurso n?o encontrado!";													//Falha
			
			No no = borda.poll(); 																	//Remover elemento da borda
			
			if(no.getEstado().getNome().equalsIgnoreCase(destino))									//Se ? o n? destino
				return String.format("%s (Custo Total: %dkm)", solucao(no), no.getCusto());			//Solu??o
			
			explorados.add(no); 																	//Adicionar ao explorados
			
			for(Transicao adj: no.getEstado().getAdjacentes()) { 									//Para cada a??o aplic?vel
				No filho = new No(adj.getEstadoDestino(), no, adj.getCusto() + no.getCusto()); 		//Criar filho
				if(!jaFoiVisitado(borda, explorados, filho)) 										//Se o filho n?o est? em explorados ou borda			
					borda.add(filho);																//Adicionar filho na borda
				else
					for(No n: borda) 
						if(n.getEstado().getNome().equals(filho.getEstado().getNome()) 
								&& n.getCusto() > filho.getCusto()) {								//Se o filho est? na borda com maior custo
							n.setPai(filho.getPai());
							n.setCusto(filho.getCusto());
							break;
						}	
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("BUSCA DE CUSTO UNIFORME");
		System.out.println(BUSCA_DE_CUSTO_UNIFORME(new Mapa(), args[0], args[1]));
	}

}
