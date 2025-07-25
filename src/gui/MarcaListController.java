package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Marca;
import model.services.MarcaService;

public class MarcaListController implements Initializable, DataChangeListener {
	
	//*************************************************************************************************************************************************************
	
	private MarcaService service;
	
	public void setMarcaService(MarcaService service) {
		this.service = service;
	}
	
	//*************************************************************************************************************************************************************

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	private void initializeNodes() {
		tableColumnIdMarca.setCellValueFactory(new PropertyValueFactory<>("idMarca"));
		tableColumnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewMarca.prefHeightProperty().bind(stage.heightProperty());
		
	}

	//*************************************************************************************************************************************************************
	
	@FXML
	private TableView<Marca> tableViewMarca;
	
	private ObservableList<Marca> obsList;
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Marca> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewMarca.setItems(obsList);
		initEditButtons();
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private TableColumn<Marca, Integer> tableColumnIdMarca;
	
	@FXML
	private TableColumn<Marca, String> tableColumnMarca;
	
	@FXML
	private TableColumn<Marca, Marca> tableColumnEditar;
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Button btNovaMarca;
	
	@FXML
	public void onBtNovaMarcaAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Marca obj = new Marca();
		createDialogForm(obj, "/gui/MarcaForm.fxml", parentStage);
	}
	
	//*************************************************************************************************************************************************************

	private void createDialogForm(Marca obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			MarcaFormController controller = loader.getController();
			controller.setMarca(obj);
			controller.setMarcaService(new MarcaService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Digite os dados da marca");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
		}catch(IOException e) {
			Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
		
	}
	
	private void initEditButtons() {
		tableColumnEditar.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEditar.setCellFactory(param -> new TableCell<Marca, Marca>() {
			private final Button button = new Button("Editar");
			
			@Override
			protected void updateItem(Marca obj, boolean empty) {
				super.updateItem(obj, empty);
				
				if(obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
				event -> createDialogForm(obj, "/gui/MarcaForm.fxml", Utils.currentStage(event)));
			}
		});
	}
	
}
