package app;
import javafx.scene.layout.BorderPane;

@SuppressWarnings("restriction")
public class FrontPage extends BorderPane {
	
	private DepenseForm form = new DepenseForm();
	private DepenseListe list = new DepenseListe();

	public FrontPage() {
		this.setCenter(form);
		this.setRight(list);
		//list.setPrefWidth(400);
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
