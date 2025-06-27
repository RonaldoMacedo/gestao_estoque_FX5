package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Fornecedor;

public class FornecedorFormController implements Initializable {
	
	private Fornecedor entity;
	
	public void setFornecedor(Fornecedor entity) {
		this.entity = entity;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtIdFornecedor);
		Constraints.setTextFieldMaxLength(txtRazaoSocial, 150);
		Constraints.setTextFieldMaxLength(txtApelido, 60);
		Constraints.setTextFieldMaxLength(txtCNPJ, 18);
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private TextField txtIdFornecedor;
	
	@FXML
	private TextField txtRazaoSocial;
	
	@FXML
	private TextField txtApelido;
	
	@FXML
	private TextField txtCNPJ;
	
	@FXML
	private TextField txtDataCadastro;
	
	@FXML
	private TextField txtSituacao;
	
	//*************************************************************************************************************************************************************

	@FXML
	private Label lblErroRazaoSocial;
	
	@FXML
	private Label lblErroApelido;
	
	@FXML
	private Label lblErroCNPJ;
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Button btSalvar;
	
	@FXML
	public void onBtSalvarAction() {
		System.out.println("Salvar");
	}
	
	@FXML
	private Button btCancelar;
	
	@FXML
	public void onBtCancelarAction() {
		System.out.println("Cancelar");
	}
	
	//*************************************************************************************************************************************************************
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtIdFornecedor.setText(String.valueOf(entity.getIdFornecedor()));
		txtRazaoSocial.setText(entity.getRazaoSocial());
		txtApelido.setText(entity.getApelido());
		txtCNPJ.setText(entity.getCnpj());
		txtDataCadastro.setText(String.valueOf(entity.getDataCadastro()));
		txtSituacao.setText(String.valueOf(entity.getSituacao()));
	}
}

	
