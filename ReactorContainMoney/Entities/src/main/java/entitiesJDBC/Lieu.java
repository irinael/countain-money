package entitiesJDBC;
public class Lieu {
	
	
	private int id;
	
	
	private String nom;

	public Lieu() {
	}

	public Lieu(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
}
