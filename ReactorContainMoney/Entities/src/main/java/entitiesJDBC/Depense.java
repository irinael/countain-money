package entitiesJDBC;

import java.time.LocalDate;

public class Depense {
	
	private int id;
	private double montant;
	private LocalDate date;
	private int idLieu;
	
	
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


	public int getIdLieu() {
		return idLieu;
	}

	public void setIdLieu(int idLieu) {
		this.idLieu = idLieu;
	}
	
	

	

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Depense(int id, double montant, LocalDate date, int idLieu) {
		super();
		this.id = id;
		this.montant = montant;
		this.date = date;
		this.idLieu = idLieu;
		
	}

	public Depense() {
		
	}


	
	

}
