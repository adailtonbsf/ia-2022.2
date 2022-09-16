package aspiradorDePo;
import java.util.Scanner;

public class AspiradorDePo {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira a quantidade de passos que o agente deve executar:");
		int n = scan.nextInt();
		Environment e = new Environment(true, true, false);
		Agent a = new Agent();
		
		for (int i = 0; i < n; i++) {
			Perception p = a.perceives(e);
			System.out.println("Ambiente: Sala A = " + (e.isDirtyA() ? "Suja":"Limpa") + " | Sala B = " 
					+ (e.isDirtyB() ? "Suja":"Limpa") + " | Local do Agente = " + (e.getAgentLocation() ? "Sala A":"Sala B") 
					+ " | Ação = " + a.act(p));
		}
		
		scan.close();
	}

}
