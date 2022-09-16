package aspiradorDePo;

public class Perception {
	
	private boolean location;
	private boolean isDirty;
	private Environment env;
	
	public Perception(Environment e) {
		this.location = e.getAgentLocation();
		if(location) isDirty = e.isDirtyA();
		else isDirty = e.isDirtyB();
		this.env = e;
	}
	
	public boolean getLocation() {
		return location;
	}
	
	public void setLocation(boolean location) {
		this.location = location;
		env.setAgentLocation(location);
	}
	
	public boolean isDirty() {
		return isDirty;
	}
	
	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
		if(location) env.setDirtyA(isDirty);
		else env.setDirtyB(isDirty);
	}

}
