package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Product;

public class UpdateProductFormController implements Initializable {
	
	private Product entity;
	
	public void setProduct(Product entnty) {
		this.entity = entity;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtDescricaoInterna;
	
	@FXML
	private TextField txtDataDoCadastro;
	
	@FXML
	private TextField txtGrupo;
	
	@FXML
	private TextField txtSituacao;
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Label lblErroCodigo;
	
	@FXML
	private Label lblErroDescricaoInterna;
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Button btSalvar;
	
	@FXML
	public void onBtSalvarAction() {
		System.out.println("Salvar atualização");
	}
	
	//*************************************************************************************************************************************************************

	@FXML
	private Button btCancelar;
	
	@FXML
	public void onBtCancelarAction() {
		System.out.println("Cancelar atualização");
	}
	
	//*************************************************************************************************************************************************************
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtCodigo);
		Constraints.setTextFieldMaxLength(txtDescricaoInterna, 200);
	}

}
