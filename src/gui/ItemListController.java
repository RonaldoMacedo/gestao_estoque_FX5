package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Item;
import model.entities.Marca;
import model.entities.Product;
import model.enums.Situacao;
import model.services.ItemService;

public class ItemListController implements Initializable {
	
	//*************************************************************************************************************************************************************
	
	private ItemService service;
	
	public void setItemService(ItemService service) {
		this.service = service;
	}
	
	//*************************************************************************************************************************************************************

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	private void initializeNodes() {
		tableColumnIdItem.setCellValueFactory(new PropertyValueFactory<>("idItem"));
		tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tableColumnDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
		Utils.formatTableColumnDate(tableColumnDataCadastro, "dd/MM/yyyy");
		tableColumnCodigoRef.setCellValueFactory(new PropertyValueFactory<>("codigoRef"));
		tableColumnCodigoDeBarras.setCellValueFactory(new PropertyValueFactory<>("codigoDeBarras"));
		tableColumnSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
		tableColumnProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewItems.prefHeightProperty().bind(stage.heightProperty());
	}

	//*************************************************************************************************************************************************************
	@FXML
	private TableView<Item> tableViewItems;
	
	@FXML 
	private TableColumn<Item, Integer> tableColumnIdItem;
	
	@FXML
	private TableColumn<Item, String> tableColumnDescricao;
	
	@FXML
	private TableColumn<Item, Date> tableColumnDataCadastro;
	
	@FXML
	private TableColumn<Item, String> tableColumnCodigoRef;
	
	@FXML
	private TableColumn<Item, String> tableColumnCodigoDeBarras;
	
	@FXML
	private TableColumn<Item, Situacao> tableColumnSituacao;
	
	@FXML
	private TableColumn<Item, Product> tableColumnProduto;
	
	@FXML
	private TableColumn<Item, Marca> tableColumnMarca;
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Button btNovoItem;
	
	@FXML
	public void onBtNovoItemAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Item obj = new Item();
		createDialogform(obj, "/gui/ItemForm.fxml", parentStage);
	}
	
	private void createDialogform(Item obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			ItemFormController controller = loader.getController();
			controller.setItem(obj);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Digite os dados do item");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}catch(IOException e) {
			Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
		
	}

	//*************************************************************************************************************************************************************
	
	private ObservableList<Item> obsList;
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Item> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewItems.setItems(obsList);
	}

}
