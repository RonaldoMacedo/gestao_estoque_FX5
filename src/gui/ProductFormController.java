package gui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Product;
import model.enums.Grupo;
import model.enums.Situacao;
import model.services.ProductService;

public class ProductFormController implements Initializable {
	
	private Product entity;
	private ProductService service;
	
	public void setProduct(Product entity) {
		this.entity = entity;
	}
	
	public void setProductService(ProductService service) {
		this.service = service;
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
	private Button btSalvar;
	
	@FXML
	public void onBtSalvarAction(ActionEvent event) {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			Utils.currentStage(event).close();
		}
		catch(DbException e) {
			Alerts.showAlert("Error saving product", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private Product getFormData() {
		Product obj = new Product();
		obj.setIdProduto(Utils.tryParseToInt(txtCodigo.getText()));
		obj.setDescricaoInterna(txtDescricaoInterna.getText());
		obj.setDataCadastro(new Date());
		obj.setGrupo(Grupo.valueOf(txtGrupo.getText()));
		obj.setSituacao(Situacao.valueOf(txtSituacao.getText()));
		return obj;
	}

	@FXML
	private Button btCancelar;
	
	@FXML
	public void onBtCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	//*************************************************************************************************************************************************************

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtCodigo);
		Constraints.setTextFieldMaxLength(txtDescricaoInterna, 200);
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtCodigo.setText(String.valueOf(entity.getIdProduto()));
		txtDescricaoInterna.setText(entity.getDescricaoInterna());
		txtDataDoCadastro.setUserData(entity.getDataCadastro());
		txtGrupo.setText(String.valueOf(entity.getGrupo()));
		txtSituacao.setText(String.valueOf(entity.getSituacao()));
	}
	
}
