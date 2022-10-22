package buscaEmLargura;

import java.util.ArrayList;
import java.util.LinkedList;
import buscaAEstrela.No;
import mapa.Estado;
import mapa.Mapa;
import mapa.Transicao;

public class Busca {
	
    public static boolean jaFoiVisitado(LinkedList<No> borda, ArrayList<No> explorados, No filho) {
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
	
	
	public static String BUSCA_EM_LARGURA(Mapa mapa, String origem, String destino) {
		LinkedList<No> borda = new LinkedList<>();
		Estado estado_inicial = mapa.getEstado(origem);
		borda.add(new No(estado_inicial));
		ArrayList<No> explorados = new ArrayList<>();
		
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
