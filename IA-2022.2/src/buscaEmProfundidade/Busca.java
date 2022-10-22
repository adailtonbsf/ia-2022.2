package buscaEmProfundidade;

import java.util.ArrayList;
import java.util.Stack;

import buscaAEstrela.No;
import mapa.Estado;
import mapa.Mapa;
import mapa.Transicao;

public class Busca {
	
	public static boolean jaFoiVisitado(Stack<No> borda, ArrayList<No> explorados, No filho) {
	    for(No no: borda)                                                                            //Se filho está na borda
            if(no.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome()))
                return true;

            for(No no: explorados)                                                                   //Se filho está em explorados
                if(no.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome()))
                    return true;
            
        return false;
	}
	
	public static String solucao(No no) {
		if(no.getPai() != null)
			return solucao(no.getPai())  + " -> " + no.getEstado().getNome();
		
		return no.getEstado().getNome();
	}
	
	public static String BUSCA_EM_PROFUNDIDADE(Mapa mapa, String origem, String destino) {
		Stack<No> borda = new Stack<>();
		Estado estado_inicial = mapa.getEstado(origem);
		borda.add(new No(estado_inicial));
		ArrayList<No> explorados = new ArrayList<>();
		
		while(true) {
			if(borda.size() == 0 || estado_inicial == null)                                                 //Se a borda está vazia	
				return "Percurso não encontrado!";                                                          //Falha
			
			No no = borda.pop();                                                                            //Remover elemento da borda
			explorados.add(no);                                                                             //Adicionar ao explorados
			
			for(Transicao adj: no.getEstado().getAdjacentes()) {                                            //Para cada ação aplicável
			    No filho = new No(adj.getEstadoDestino(), no, adj.getCusto() + no.getCusto());              //Criar filho
				if(!jaFoiVisitado(borda, explorados, filho))                                                //Se o filho não está em explorados ou borda
				    if(filho.getEstado().getNome().equalsIgnoreCase(destino))                               //Se o filho é o destino
				        return String.format("%s (Custo Total: %dkm)", solucao(filho), filho.getCusto());   //Solução
				    else borda.add(filho);                                                                  //Adicionar filho na borda	
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("BUSCA EM PROFUNDIDADE");
		System.out.println(BUSCA_EM_PROFUNDIDADE(new Mapa(), args[0], args[1]));
	}

}
