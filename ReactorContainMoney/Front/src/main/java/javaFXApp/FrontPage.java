package javaFXApp;
import javafx.scene.layout.BorderPane;

public class FrontPage extends BorderPane {
	private DepenseListe list = new DepenseListe();
	private DepenseForm form = new DepenseForm();


	public FrontPage() {
		this.setRight(list);
		this.setCenter(form);	
	}

	public DepenseForm getDepenseForm() {
		return form;
	}

	public void setDepenseForm(DepenseForm form) {
		this.form = form;
	}

	public DepenseListe getDepenseList() {
		return list;
	}

	public void setDepenseList(DepenseListe list) {
		this.list = list;
	}

	
	
}
