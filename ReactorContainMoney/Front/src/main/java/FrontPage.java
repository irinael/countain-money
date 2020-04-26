import javafx.scene.layout.BorderPane;

@SuppressWarnings("restriction")
public class FrontPage extends BorderPane {
	
	private DepenseForm form = new DepenseForm();
	private DepenseListe list = new DepenseListe();

	public FrontPage() {
		this.setLeft(form);
		this.setRight(list);
	}

	
}
