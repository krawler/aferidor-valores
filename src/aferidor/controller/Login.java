package aferidor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login extends Application {
	
	private GridPane pane;
	private TextField txLogin;
	private PasswordField txSenha;
	private Button btEntrar;
	private Button btSair;
	private static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {		
		initComponents();
		initListeners();
		stage.setTitle("Login - Aferidor");		
		//pane.setStyle("-fx-background-color: linear-gradiente(from 0% 0% to 100% 100%, blue 0%, silver 100%);");		
		//pane.getChildren().addAll(txLogin, txSenha, btEntrar, btSair);		
		initGridPane();
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();
		Login.stage = stage;	
	}
	
	private void initComponents() {		
		pane =  new GridPane();
		pane.setPrefSize(260, 180);
		txLogin = new TextField();
		txLogin.setPromptText("Digite aqui seu login");		
		txSenha = new PasswordField();
		txSenha.setPromptText("Digite aqui sua senha");		
		btEntrar = new Button("Entrar");		
		btSair = new Button("Sair");		
	}
	
	private void initGridPane() {		
		pane.setPadding(new Insets(20,30,10,30));
		pane.setHgap(10);
		pane.setVgap(10);		
		pane.addRow(0, txLogin);
		pane.addRow(1, txSenha);
		pane.addRow(2, btEntrar, btSair);
		
	}	
	
	private void initListeners() {
		btSair.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent arg0) {
				System.exit(0);				
			}
		});
		
		btEntrar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				logar();				
			}
			
		});
		
		txLogin.setOnKeyPressed(e -> handle(e));		
	}
	
	public void handle(KeyEvent e) {
		String type = e.getEventType().getName();
		KeyCode keycode = e.getCode();
		System.out.println(type + " key code: " + keycode.getName());
		
		if(e.getEventType() == KeyEvent.KEY_PRESSED && e.getCode() == KeyCode.ENTER) {					 
		    e.consume();
		    selectNextNode(!e.isShiftDown(), (TextField) e.getSource());     
		}
	}
	
	public void selectNextNode(boolean forward, TextField txField){
        List<Node> nodes = getAllNodes(txField.getScene().getRoot());
        int index = nodes.indexOf(txField);
        if(forward){
            if(index < nodes.size() - 1) {
                nodes.get(index + 1).requestFocus();
            }else {
                nodes.get(0).requestFocus();
            }
        }else {
            if(index == 0) {
                nodes.get(nodes.size() - 1).requestFocus();
            }else {
                nodes.get(index - 1).requestFocus();
            }
        }
    }
	
	private ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        addAllDescendents(root, nodes);
        return nodes;
    }
	
	private void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            if(node.isFocusTraversable()){
                nodes.add(node);
            }
            if (node instanceof Parent)
                addAllDescendents((Parent)node, nodes);
        }
    }
	
	private void logar() {
		if(txLogin.getText().equals("admin") && txSenha.getText().equals("onsoft")) {
		   try {
			   new AferidorApplication().start(new Stage());
			   Login.getStage().close();
		   }catch (Exception e) {			
			   e.printStackTrace();
		   }	
		}else {
			JOptionPane.showMessageDialog(null, "Login ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
