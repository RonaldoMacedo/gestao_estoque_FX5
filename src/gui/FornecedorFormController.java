package gui;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import model.services.FornecedorService;

public class FornecedorFormController implements Initializable {
	
	private Fornecedor entity;
	
	public void setFornecedor(Fornecedor entity) {
		this.entity = entity;
	}
	
	//*************************************************************************************************************************************************************
	
	private FornecedorService service;
	
	public void setFornecedorService(FornecedorService service) {
		this.service = service;
	}
	
	//*************************************************************************************************************************************************************
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
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
	}
	
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
		
	}

	private Fornecedor getFormData() {
		Fornecedor obj = new Fornecedor();
		obj.setIdFornecedor(Utils.tryParseToInt(txtIdFornecedor.getText()));
		obj.setRazaoSocial(txtRazaoSocial.getText());
		obj.setApelido(txtApelido.getText());
		obj.setCnpj(txtCNPJ.getText());
		obj.setDataCadastro(new Date(0));
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

	
