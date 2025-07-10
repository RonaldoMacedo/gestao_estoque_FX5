package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Marca;

public class MarcaFormController implements Initializable {
	
	private Marca entity;
	
	public void setMarca(Marca entity) {
		this.entity = entity;
	}
	
	//*************************************************************************************************************************************************************

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
	
	//*************************************************************************************************************************************************************
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtIdMarca.setText(String.valueOf(entity.getIdMarca()));
		txtMarca.setText(String.valueOf(entity.getMarca()));
	}

}
