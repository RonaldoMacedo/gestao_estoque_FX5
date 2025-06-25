package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.ProductService;

public class TelaPrincipalController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemNovoProduto;
	
	@FXML
	public void onMenuItemNovoProdutoAction() {
		System.out.println("Novo produto");
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemNovoItem;
	
	@FXML
	public void onMenuItemNovoItemAction() {
		System.out.println("Novo item");
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemNovoFornecedor;
	
	@FXML
	public void onMenuItemNovoFornecedorAction() {
		System.out.println("Novo fornecedor");
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemSobre;
	
	@FXML
	public void onMenuItemSobreAction() {
		loadView("/gui/Sobre.fxml");
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemListarProdutos;
	
	@FXML
	public void onMenuItemListarProdutosAction() {
		loadView2("/gui/ListaProduto.fxml");
	}
	
	//*************************************************************************************************************************************************************
	
	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox telaPrincipalVBox = (VBox) ((ScrollPane)mainScene.getRoot()).getContent();
			
			Node mainMenu = telaPrincipalVBox.getChildren().get(0);
			telaPrincipalVBox.getChildren().clear();
			telaPrincipalVBox.getChildren().add(mainMenu);
			telaPrincipalVBox.getChildren().addAll(newVBox.getChildren());
			
		}catch(IOException e) {
			Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private synchronized void loadView2(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox telaPrincipalVBox = (VBox) ((ScrollPane)mainScene.getRoot()).getContent();
			
			Node mainMenu = telaPrincipalVBox.getChildren().get(0);
			telaPrincipalVBox.getChildren().clear();
			telaPrincipalVBox.getChildren().add(mainMenu);
			telaPrincipalVBox.getChildren().addAll(newVBox.getChildren());
			
			ListaProdutoController controller = loader.getController();
			controller.setProductService(new ProductService());
			controller.updateTableView();
			
		}catch(IOException e) {
			Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}
