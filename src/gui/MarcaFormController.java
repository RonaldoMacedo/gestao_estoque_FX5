package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MarcaFormController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtIdMarca);
		Constraints.setTextFieldMaxLength(txtMarca, 50);
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private TextField txtIdMarca;
	
	@FXML
	private TextField txtMarca;
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Button btSalvar;
	
	@FXML
	public void onBtSalvarMarcaAction() {
		System.out.println("Salvar marca");
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Button btCancelar;
	
	@FXML
	public void onBtCancelarAction() {
		System.out.println("Cancelar");
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Label lblErroMarca;

}
