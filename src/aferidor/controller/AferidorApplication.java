package aferidor.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AferidorApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("Aferidor.fxml"));
		
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Aferidor de valores do Data Warehouse");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
