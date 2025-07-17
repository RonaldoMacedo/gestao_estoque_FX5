package gui;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Product;
import model.enums.Grupo;
import model.enums.Situacao;
import model.exceptions.ValidationException;
import model.services.ProductService;

public class ProductFormController implements Initializable {
	
	private Product entity;
	private ProductService service;
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	public void setProduct(Product entity) {
		this.entity = entity;
	}
	
	public void setProductService(ProductService service) {
		this.service = service;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
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
	private Label lblErro;
	
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		if(fields.contains("descricaoInterna")) {
			lblErro.setText(errors.get("descricaoInterna"));
		}
	}
	
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
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		}
		catch(DbException e) {
			Alerts.showAlert("Error saving product", null, e.getMessage(), AlertType.ERROR);
		}
		catch(ValidationException e) {
			setErrorMessages(e.getErrors());
		}
	}
	
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
		
	}

	private Product getFormData() {
		Product obj = new Product();
		ValidationException exception = new ValidationException("Validation error");
		
		obj.setIdProduto(Utils.tryParseToInt(txtCodigo.getText()));
		
		if(txtDescricaoInterna.getText() == null || txtDescricaoInterna.getText().trim().equals("")) {
			exception.addError("descricaoInterna", "O nome do produto é obrigatório");
		}
		obj.setDescricaoInterna(txtDescricaoInterna.getText());
		if(exception.getErrors().size() > 0) {
			throw exception;
		}
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
		if(entity.getDataCadastro() != null) {
			txtDataDoCadastro.setText(String.valueOf(LocalDateTime.ofInstant(entity.getDataCadastro().toInstant(), ZoneId.systemDefault())));
		}
		txtGrupo.setText(String.valueOf(entity.getGrupo()));
		txtSituacao.setText(String.valueOf(entity.getSituacao()));
	}
	
}
