package gui;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

import gui.util.Constraints;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Item;

public class ItemFormController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldDouble(txtCodigoDeBarras);
	}
	
	//*************************************************************************************************************************************************************
	
	
	private Item entity;
	
	public void setItem(Item entity) {
		this.entity = entity;
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

	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtIdItem.setText(String.valueOf(entity.getIdItem()));
		txtDescricao.setText(String.valueOf(entity.getDescricao()));
		
		if(entity.getDataCadastro() != null) {
			txtDataCadastro.setText(String.valueOf(LocalDateTime.ofInstant(entity.getDataCadastro().toInstant(), ZoneId.systemDefault())));
		}
		
		txtCodigoRef.setText(String.valueOf(entity.getCodigoRef()));
		txtCodigoDeBarras.setText(String.valueOf(entity.getCodigoDeBarras()));
		txtSituacao.setText(String.valueOf(entity.getSituacao()));
		txtMarca.setText(String.valueOf(entity.getMarca()));
		txtProduto.setText(String.valueOf(entity.getProduct()));
		txtSaldo.setText(String.valueOf(entity.getSaldo()));
	}
}
