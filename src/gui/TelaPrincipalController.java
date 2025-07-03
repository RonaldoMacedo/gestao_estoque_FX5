package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.services.FornecedorService;
import model.services.ProductService;

public class TelaPrincipalController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}

	//*************************************************************************************************************************************************************

	@FXML
	private MenuItem menuItemListarProdutos;

	@FXML
	public void onMenuItemListarProdutosAction() {
		loadView("/gui/ListaProduto.fxml", (ListaProdutoController controller) -> {
			controller.setProductService(new ProductService());
			controller.updateTableView();
		});
	}

	//*************************************************************************************************************************************************************

	@FXML
	private MenuItem menuItemListarItens;
	
	@FXML
	public void onMenuItemListarItensAction() {
		loadView("/gui/ItemList.fxml", null);
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemListarFornecedores;

	@FXML
	public void onMenuItemListarFornecedoresAction() {
		loadView("/gui/ListaFornecedor.fxml", (ListaFornecedorController controller) -> {
			controller.setFornecedorService(new FornecedorService());
			controller.updateTableView();
		});
	}

	//*************************************************************************************************************************************************************

	@FXML
	private MenuItem menuItemListarUsuarios;
	
	@FXML
	public void onMenuItemListarUsuarios() {
		System.out.println("Lista de usuários");
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemNovoPedidoDeCompra;
	
	@FXML
	public void onMenuItemNovoPedidoDeCompraAction() {
		System.out.println("Novo pedido de compra");
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemNovoPedidoInterno;
	
	@FXML
	public void onMenuItemNovoPedidoInternoAction() {
		System.out.println("Novo pedido interno");
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemSobre;

	@FXML
	public void onMenuItemSobreAction() {
		loadView("/gui/Sobre.fxml", x -> {
		});
	}

	//*************************************************************************************************************************************************************

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox telaPrincipalVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = telaPrincipalVBox.getChildren().get(0);
			telaPrincipalVBox.getChildren().clear();
			telaPrincipalVBox.getChildren().add(mainMenu);
			telaPrincipalVBox.getChildren().addAll(newVBox.getChildren());

			T controller = loader.getController();
			initializingAction.accept(controller);

		} catch (IOException e) {
			Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}
