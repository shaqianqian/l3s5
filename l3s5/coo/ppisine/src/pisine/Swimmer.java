package pisine;

public class Swimmer  {
	
	
	String nom;
	long t_dehabille;
	long t_baigner;
	long t_habiller;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public long getT_dehabille() {
		return t_dehabille;
	}
	public void setT_dehabille(long t_dehabille) {
		this.t_dehabille = t_dehabille;
	}
	public long getT_baigner() {
		return t_baigner;
	}
	public void setT_baigner(long t_baigner) {
		this.t_baigner = t_baigner;
	}
	public long getT_habiller() {
		return t_habiller;
	}
	public void setT_habiller(long t_habiller) {
		this.t_habiller = t_habiller;
	}
	public Swimmer(String nom, long t_dehabille, long t_baigner, long t_habiller) {
		super();
		this.nom = nom;
		this.t_dehabille = t_dehabille;
		this.t_baigner = t_baigner;
		this.t_habiller = t_habiller;
	}
	
	boolean a=false;
	boolean b=false;
	public boolean isA() {
		return a;
	}
	public void setA(boolean a) {
		this.a = a;
	}
	public boolean isB() {
		return b;
	}
	public void setB(boolean b) {
		this.b = b;
	}


	
}
