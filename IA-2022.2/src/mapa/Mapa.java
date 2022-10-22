package mapa;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mapa {
	private List<Estado> estados = new ArrayList<>();
	
	public Mapa() {
		Estado oradea = new Estado("Oradea", new ArrayList<Transicao>());
		Estado zerind = new Estado("Zerind", new ArrayList<Transicao>());
		Estado arad = new Estado("Arad", new ArrayList<Transicao>());
		Estado timisoara = new Estado("Timisoara", new ArrayList<Transicao>());
		Estado lugoj = new Estado("Lugoj", new ArrayList<Transicao>());
		Estado mehadia = new Estado("Mehadia", new ArrayList<Transicao>());
		Estado dobreta = new Estado("Dobreta", new ArrayList<Transicao>());
		Estado sibiu = new Estado("Sibiu", new ArrayList<Transicao>());
		Estado rimnicu = new Estado("Rimnicu Vilcea", new ArrayList<Transicao>());
		Estado craiova = new Estado("Craiova", new ArrayList<Transicao>());
		Estado fagaras = new Estado("Fagaras", new ArrayList<Transicao>());
		Estado pitesti = new Estado("Pitesti", new ArrayList<Transicao>());
		Estado bucharest = new Estado("Bucharest", new ArrayList<Transicao>());
		Estado giurgiu = new Estado("Giurgiu", new ArrayList<Transicao>());
		Estado neamt = new Estado("Neamt", new ArrayList<Transicao>());
		Estado iasi = new Estado("Iasi", new ArrayList<Transicao>());
		Estado vaslui = new Estado("Vaslui", new ArrayList<Transicao>());
		Estado urziceni = new Estado("Urziceni", new ArrayList<Transicao>());
		Estado hirsova = new Estado("Hirsova", new ArrayList<Transicao>());
		Estado eforie = new Estado("Eforie", new ArrayList<Transicao>());
		oradea.setAdjacencias(Arrays.asList(
			new Transicao(zerind, 71), 
			new Transicao(sibiu, 151)));
		zerind.setAdjacencias(Arrays.asList(
			new Transicao(oradea, 71), 
			new Transicao(arad, 75)));
		arad.setAdjacencias(Arrays.asList(
			new Transicao(zerind, 75), 
			new Transicao(timisoara, 118), 
			new Transicao(sibiu, 140)));
		timisoara.setAdjacencias(Arrays.asList(
			new Transicao(arad, 118), 
			new Transicao(lugoj, 111)));
		lugoj.setAdjacencias(Arrays.asList(
			new Transicao(timisoara, 111), 
			new Transicao(mehadia, 70)));
		mehadia.setAdjacencias(Arrays.asList(
			new Transicao(lugoj, 70), 
			new Transicao(dobreta, 75)));
		dobreta.setAdjacencias(Arrays.asList(
			new Transicao(mehadia, 75), 
			new Transicao(craiova, 120)));
		craiova.setAdjacencias(Arrays.asList(
			new Transicao(dobreta, 120), 
			new Transicao(pitesti, 138), 
			new Transicao(rimnicu, 146)));
		sibiu.setAdjacencias(Arrays.asList(
			new Transicao(arad, 140), 
			new Transicao(oradea, 151), 
			new Transicao(fagaras, 99), 
			new Transicao(rimnicu, 80)));
		rimnicu.setAdjacencias(Arrays.asList(
			new Transicao(sibiu, 80), 
			new Transicao(pitesti, 97), 
			new Transicao(craiova, 146)));
		fagaras.setAdjacencias(Arrays.asList(
			new Transicao(sibiu, 99), 
			new Transicao(bucharest, 211)));
		pitesti.setAdjacencias(Arrays.asList(
			new Transicao(rimnicu, 97), 
			new Transicao(craiova, 138), 
			new Transicao(bucharest, 101)));
		bucharest.setAdjacencias(Arrays.asList(
			new Transicao(fagaras, 211), 
			new Transicao(pitesti, 101), 
			new Transicao(giurgiu, 90), 
			new Transicao(urziceni, 85)));
		giurgiu.setAdjacencias(Arrays.asList(
			new Transicao(bucharest, 90)));
		urziceni.setAdjacencias(Arrays.asList(
			new Transicao(bucharest, 85), 
			new Transicao(vaslui, 142), 
			new Transicao(hirsova, 98)));
		hirsova.setAdjacencias(Arrays.asList(
			new Transicao(urziceni, 98), 
			new Transicao(eforie, 86)));
		eforie.setAdjacencias(Arrays.asList(
			new Transicao(hirsova, 86)));
		vaslui.setAdjacencias(Arrays.asList(
			new Transicao(urziceni, 142), 
			new Transicao(iasi, 92)));
		iasi.setAdjacencias(Arrays.asList(
			new Transicao(vaslui, 92), 
			new Transicao(neamt, 87)));
		neamt.setAdjacencias(Arrays.asList(
			new Transicao(iasi, 87)));
		estados.add(oradea);
		estados.add(zerind);
		estados.add(arad);
		estados.add(timisoara);
		estados.add(lugoj);
		estados.add(mehadia);
		estados.add(dobreta);
		estados.add(sibiu);
		estados.add(rimnicu);
		estados.add(craiova);
		estados.add(fagaras);
		estados.add(pitesti);
		estados.add(bucharest);
		estados.add(giurgiu);
		estados.add(neamt);
		estados.add(iasi);
		estados.add(vaslui);
		estados.add(urziceni);
		estados.add(hirsova);
		estados.add(eforie);
	}
	

	public List<Estado> getEstados() {
		return estados;
	}
	
	public Estado getEstado(String nome) {
	    for(Estado estado: estados)
            if(estado.getNome().equalsIgnoreCase(nome))
                return estado;
	    return null;
	}
	
	@Override
	public String toString() {
		String mapa = "[";
		for(Estado estado : this.estados) {
			mapa += ("{nome : " + estado.getNome()) + ", transicoes : ["; 
			for(Transicao transicao : estado.getAdjacentes()) {
				mapa += "{nome : " + transicao.getEstadoDestino().getNome() +
						", custo : " + transicao.getCusto() + "},";
			}
			mapa = mapa.substring(0, mapa.length() - 1);
			mapa += "]},\n";
		}
		mapa = mapa.substring(0, mapa.length() - 2);
		mapa += ']';
		return mapa;
	}
}
