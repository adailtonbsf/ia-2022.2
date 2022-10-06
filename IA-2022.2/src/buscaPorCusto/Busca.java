package buscaPorCusto;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import mapa.Estado;
import mapa.Mapa;
import mapa.Transicao;

public class Busca {
	
	public static boolean jaFoiVisitado(Stack<No> borda, ArrayList<No> explorados, No filho) {
		boolean visitado = false;
		for(No n: borda) 														//Se filho está na borda
			if(n.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome()))
				visitado = true;
		if(!visitado)
			for(No n: explorados) 												//Se filho está em explorados
				if(n.getEstado().getNome().equalsIgnoreCase(filho.getEstado().getNome()))
					visitado = true;
		return visitado;
	}
	
	public static String BUSCA_EM_LARGURA(Mapa m, String origem, String destino) {
		PriorityQueue<No> borda = new PriorityQueue<>();
		borda.
		ArrayList<No> explorados = new ArrayList<>();
		
		Estado est_inicial = null;
		String percurso = "";
		for(Estado estado: m.getEstados())
			if(estado.getNome().equalsIgnoreCase(destino)) {
				est_inicial = estado;
				break;
			}
	
		borda.push(new No(est_inicial, null, 0));
		while(true) {
			if(borda.size() == 0)																		//Se a borda está vazia	
				return "Percurso não encontrado!";														//Falha
			
			No no = borda.pop(); 																		//Remover elemento da borda
			explorados.add(no); 																		//Adicionar ao explorados
			for(Transicao adj: no.getEstado().getAdjacentes()) { 										//Para cada ação aplicável
				No filho = new No(adj.getEstadoDestino(), no, adj.getCusto() + no.getCusto()); 			//Criar filho
				if(!jaFoiVisitado(borda, explorados, filho)) {											//Se o filho não está em explorados ou borda
					if(filho.getEstado().getNome().equalsIgnoreCase(origem)) {							//Se o filho é o destino
						percurso += filho.getEstado().getNome();
						for(No n = filho.getPai(); n != null; n = n.getPai())
							percurso += " -> " + n.getEstado().getNome();
						return percurso + " (Custo Total: " + filho.getCusto() + "km)";					//Solução
					}
					else borda.push(filho);																//Adicionar filho na borda
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
