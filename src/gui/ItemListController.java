package gui;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Item;
import model.entities.Product;
import model.enums.Situacao;

public class ItemListController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	private void initializeNodes() {
		tableColumnIdItem.setCellValueFactory(new PropertyValueFactory<>("idItem"));
		tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tableColumnDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
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
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Button btNovoItem;
	
	@FXML
	public void onBtNovoItemAction() {
		System.out.println("Novo item");
	}
	
	//*************************************************************************************************************************************************************
	
	

}
