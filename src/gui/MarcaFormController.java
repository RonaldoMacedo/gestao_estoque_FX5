package gui;

import java.net.URL;
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
import model.entities.Marca;
import model.services.MarcaService;

public class MarcaFormController implements Initializable {
	
	private Marca entity;
	
	public void setMarca(Marca entity) {
		this.entity = entity;
	}
	
	//*************************************************************************************************************************************************************
	
	private MarcaService service;
	
	public void setMarcaService(MarcaService service) {
		this.service = service;
	}
	
	//*************************************************************************************************************************************************************

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
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
	public void onBtSalvarMarcaAction(ActionEvent event) {
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
		}catch(DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
		
	}

	private Marca getFormData() {
		Marca obj = new Marca();
		obj.setIdMarca(Utils.tryParseToInt(txtIdMarca.getText()));
		obj.setMarca(txtMarca.getText());
		return obj;
	}

	//*************************************************************************************************************************************************************
	
	@FXML
	private Button btCancelar;
	
	@FXML
	public void onBtCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
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
