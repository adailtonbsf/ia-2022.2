package aspiradorDePo;

public class Agent {
	
	public Perception perceives(Environment e) {		
		Perception p = new Perception(e);
		return p;
	}
	
	public String act(Perception p) {
		if(p.isDirty()) {
			p.setDirty(false);
			return "Aspirar";
		}
		else if(!p.getLocation()) {
			p.setLocation(true);
			return "Esquerda";
		}
		else {
			p.setLocation(false);
			return "Direita";
		}
	}

}
