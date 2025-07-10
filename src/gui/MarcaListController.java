package gui;

import java.net.URL;
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
import model.entities.Marca;
import model.services.MarcaService;

public class MarcaListController implements Initializable {
	
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
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private TableColumn<Marca, Integer> tableColumnIdMarca;
	
	@FXML
	private TableColumn<Marca, String> tableColumnMarca;
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Button btNovaMarca;
	
	@FXML
	public void onBtNovaMarcaAction() {
		System.out.println("Nova marca");
	}
	
	//*************************************************************************************************************************************************************

}
