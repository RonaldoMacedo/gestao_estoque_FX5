package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Product;
import model.enums.Grupo;
import model.enums.Situacao;
import model.services.ProductService;

public class ListaProdutoController implements Initializable, DataChangeListener {
	
	private ProductService service;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private ObservableList<Product> obsList;
	
	private void initializeNodes() {
		tableColumnIdProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto"));	
		tableColumnDescricaoInterna.setCellValueFactory(new PropertyValueFactory<>("descricaoInterna"));
		tableColumnDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
		tableColumnGrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
		tableColumnSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
		tableColumnSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewProduct.prefHeightProperty().bind(stage.heightProperty());
		
		
	}

	//*************************************************************************************************************************************************************
	
	@FXML
	private TableView<Product> tableViewProduct;
	
	@FXML
	private TableColumn<Product, Integer> tableColumnIdProduto;
	
	@FXML
	private TableColumn<Product, String> tableColumnDescricaoInterna;
	
	@FXML
	private TableColumn<Product, Date> tableColumnDataCadastro;
	
	@FXML
	private TableColumn<Product, Grupo> tableColumnGrupo;
	
	@FXML
	private TableColumn<Product, Situacao> tableColumnSituacao;
	
	@FXML
	private TableColumn<Product, Integer> tableColumnSaldo;
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Button btNovo;
	
	@FXML
	public void onBtNovoAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Product obj = new Product();
		createDialogForm(obj, "/gui/ProductForm.fxml", parentStage);
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Button btAtualizar;
	
	@FXML
	public void onBtAtualizarAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Product obj = new Product();
		createDialogForm2(obj, "/gui/UpdateProductForm.fxml", parentStage);
	}
	
	public void setProductService(ProductService service) {
		this.service = service;
	}
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Product> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewProduct.setItems(obsList);
	}
	
	private void createDialogForm(Product obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			ProductFormController controller = loader.getController();
			controller.setProduct(obj);
			controller.setProductService(new ProductService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Digite os dados do produto");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void createDialogForm2(Product obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			ProductFormController controller = loader.getController();
			controller.setProduct(obj);
			controller.setProductService(new ProductService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Digite os dados do produto");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
		
	}

}
