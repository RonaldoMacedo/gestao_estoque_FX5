package gui;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Fornecedor;
import model.enums.Situacao;
import model.services.FornecedorService;

public class ListaFornecedorController implements Initializable {
	
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
	private TableColumn<Fornecedor, Date> tableColumnDataCadastro;
	
	@FXML
	private TableColumn<Fornecedor, Situacao> tableColumnSituacao;
	
	//************************************************************************************************************************************************************
	
	@FXML
	private Button btNovo;
	
	@FXML
	public void onBtNovoAction() {
		System.out.println("Novo fornecedor");
	}
	
	//************************************************************************************************************************************************************
	
	@FXML
	private Button btAtualizar;
	
	@FXML
	public void onBtAtualizarAction() {
		System.out.println("Atualizar fornecedor");
	}
	
	//************************************************************************************************************************************************************

}
