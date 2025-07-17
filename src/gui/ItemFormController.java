package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ItemFormController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldDouble(txtCodigoDeBarras);
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private TextField txtIdItem;
	
	@FXML
	private TextField txtDescricao;
	
	@FXML
	private TextField txtDataCadastro;
	
	@FXML
	private TextField txtCodigoRef;
	
	@FXML
	private TextField txtCodigoDeBarras;
	
	@FXML
	private TextField txtSituacao;
	
	@FXML
	private TextField txtMarca;
	
	@FXML
	private TextField txtProduto;
	
	@FXML
	private TextField txtSaldo;
	
	//*************************************************************************************************************************************************************

	@FXML
	private Button btSalvar;
	
	@FXML
	public void onBtSalvarItemAction() {
		System.out.println("Novo item");
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
	private Label lblErroDescricao;
	
	@FXML
	private Label lblErroCodigoRef;
	
	@FXML
	private Label lblErroCodigoDeBarras;
	
	//*************************************************************************************************************************************************************

}
