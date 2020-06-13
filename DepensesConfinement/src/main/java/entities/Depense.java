package entities;



public class Depense {
	
	
	private int id;
	
	
	private double montant;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}


	public Depense(int id, double montant, int idLieu) {
		super();
		this.id = id;
		this.montant = montant;
		//this.idLieu = idLieu;
	}

	public Depense() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	

}
