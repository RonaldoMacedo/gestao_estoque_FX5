package gui;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;
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
import model.entities.Fornecedor;
import model.enums.Situacao;
import model.exceptions.ValidationException;
import model.services.FornecedorService;

public class FornecedorFormController implements Initializable {
	
	private Fornecedor entity;
	
	public void setFornecedor(Fornecedor entity) {
		this.entity = entity;
	}
	
	//************************************************************************************************************************************************************
	
	private FornecedorService service;
	
	public void setFornecedorService(FornecedorService service) {
		this.service = service;
	}
	
	//************************************************************************************************************************************************************
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtIdFornecedor.setText(String.valueOf(entity.getIdFornecedor()));
		txtRazaoSocial.setText(entity.getRazaoSocial());
		txtApelido.setText(entity.getApelido());
		txtCNPJ.setText(entity.getCnpj());
		txtDataCadastro.setText(String.valueOf(entity.getDataCadastro(LocalDateTime.now())));
		txtSituacao.setText(String.valueOf(entity.getSituacao(Situacao.valueOf("Ativo"))));
		
	}
	
	//************************************************************************************************************************************************************
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	//************************************************************************************************************************************************************

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
	
	//************************************************************************************************************************************************************
	
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
	
	//************************************************************************************************************************************************************

	@FXML
	private Label lblErroRazaoSocial;
	
	@FXML
	private Label lblErroApelido;
	
	@FXML
	private Label lblErroCNPJ;
	
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		if(fields.contains("razaoSocial")) {
			lblErroRazaoSocial.setText(errors.get("razaoSocial"));
		}
		if(fields.contains("apelido")) {
			lblErroApelido.setText(errors.get("apelido"));
		}
		if(fields.contains("CNPJ")) {
			lblErroCNPJ.setText(errors.get("CNPJ"));
		}
	}
	
	//************************************************************************************************************************************************************
	
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
			Alerts.showAlert("Erro ao salvar fornecedor", null, e.getMessage(), AlertType.ERROR);
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

	private Fornecedor getFormData() {
		Fornecedor obj = new Fornecedor();
		
		ValidationException exception = new ValidationException("Validation exception");
		
		
		if(txtRazaoSocial.getText() == null || txtRazaoSocial.getText().trim().equals("")) {
			exception.addError("razaoSocial", "Campo obrigatório");
		}
		obj.setRazaoSocial(txtRazaoSocial.getText());
		
		if(txtApelido.getText() == null || txtApelido.getText().trim().equals("")) {
			exception.addError("apelido", "Campo obrigatório");
		}
		obj.setApelido(txtApelido.getText());
		
		if(txtCNPJ.getText() == null || txtCNPJ.getText().trim().equals("")) {
			exception.addError("CNPJ", "Campo obrigatório");
		}
		obj.setCnpj(txtCNPJ.getText());

		obj.setSituacao(Situacao.valueOf(txtSituacao.getText()));
		
		if(exception.getErrors().size() > 0) {
			throw exception;
		}
		return obj;
	}
	
	//************************************************************************************************************************************************************

	@FXML
	private Button btCancelar;
	
	@FXML
	public void onBtCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	//************************************************************************************************************************************************************
	
}

	
