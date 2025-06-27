package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDateTime;
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
import model.entities.Fornecedor;
import model.enums.Situacao;
import model.services.FornecedorService;

public class ListaFornecedorController implements Initializable, DataChangeListener {
	
	private FornecedorService service;
	
	public void setFornecedorService(FornecedorService service) {
		this.service = service;
	}
	
	private ObservableList<Fornecedor> obsList;
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Fornecedor> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewFornecedor.setItems(obsList);
		initEditButtons();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	private void initializeNodes() {
		tableColumnIdFornecedor.setCellValueFactory(new PropertyValueFactory<>("idFornecedor"));
		tableColumnRazaoSocial.setCellValueFactory(new PropertyValueFactory<>("razaoSocial"));
		tableColumnApelido.setCellValueFactory(new PropertyValueFactory<>("apelido"));
		tableColumnCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		tableColumnDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
		tableColumnSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewFornecedor.prefHeightProperty().bind(stage.heightProperty());
	}

	//*************************************************************************************************************************************************************
	
	@FXML
	private TableView<Fornecedor> tableViewFornecedor;
	
	@FXML
	private TableColumn<Fornecedor, Integer> tableColumnIdFornecedor;
	
	@FXML
	private TableColumn<Fornecedor, String> tableColumnRazaoSocial;
	
	@FXML
	private TableColumn<Fornecedor, String> tableColumnApelido;
	
	@FXML
	private TableColumn<Fornecedor, String> tableColumnCNPJ;
	
	@FXML
	private TableColumn<Fornecedor, LocalDateTime> tableColumnDataCadastro;
	
	@FXML
	private TableColumn<Fornecedor, Situacao> tableColumnSituacao;
	
	@FXML
	private TableColumn<Fornecedor, Fornecedor> tableColumnEditar;
	
	private void initEditButtons() {
		tableColumnEditar.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEditar.setCellFactory(param -> new TableCell<Fornecedor, Fornecedor>(){
			private final Button button = new Button("Editar");
			
			@Override
			protected void updateItem(Fornecedor obj, boolean empty) {
				super.updateItem(obj, empty);
				
				if(obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogForm(obj, "/gui/FornecedorForm.fxml", Utils.currentStage(event)));
			}
		});
	}
	
	//************************************************************************************************************************************************************
	
	@FXML
	private Button btNovo;
	
	@FXML
	public void onBtNovoAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Fornecedor obj = new Fornecedor();
		createDialogForm(obj, "/gui/FornecedorForm.fxml", parentStage);
	}
	
	//************************************************************************************************************************************************************
	
	@FXML
	private Button btAtualizar;
	
	@FXML
	public void onBtAtualizarAction() {
		System.out.println("Atualizar fornecedor por código");
	}
	
	//************************************************************************************************************************************************************

	private void createDialogForm(Fornecedor obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			FornecedorFormController controller = loader.getController();
			controller.setFornecedor(obj);
			controller.setFornecedorService(new FornecedorService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Digite os dados do fornecedor");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch(IOException e) {
			Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
		
	}
	
}
