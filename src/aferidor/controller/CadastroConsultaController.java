package aferidor.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class CadastroConsultaController extends Application implements Initializable {
	
	private String fxml;
	
	public  CadastroConsultaController(String fxml) {
		this.fxml = fxml;
	}

	@Override
	@FXML
	public void initialize(URL url, ResourceBundle bundle) {		
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource(fxml));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Cadastro de consulta do aferidor");
		stage.show();
	}

}
