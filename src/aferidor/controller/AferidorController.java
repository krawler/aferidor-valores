package aferidor.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class AferidorController implements Initializable {
	
	@FXML
	private ComboBox<String> cmbCuboDataWareHouse;
	@FXML
	private ComboBox<String> cmbConsultaTransacional;
	@FXML
	private ComboBox<String> cmbMaisUmaData;
	@FXML
	private DatePicker dtpDataInicialConsulta;
	@FXML
	private DatePicker dtpDataFinalConsulta;
	@FXML
	private ComboBox<String> cmbDimensaoFiltro;
	@FXML
	private ComboBox<String> cmbCampoConsulta;
	@FXML
	private TableView<String> tbvFiltros;
	@FXML
	private TableColumn<String, String> tbcCampoDimensao;
	@FXML
	private TableColumn<String, String> tbcValorFiltro;
	@FXML
	private Label lblMsgMaisUmaData;
	@FXML
	private Button btnConsultaValores;
	
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:C:\\Users\\Rafael.Ramos\\Documents\\workspace-sts-3.9.1.RELEASE\\aferidor-valores\\db\\consultas;create=true";
	Connection conn = null;

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		
		lblMsgMaisUmaData.setVisible(false);
		cmbMaisUmaData.setVisible(false);
		dtpDataInicialConsulta.requestFocus();
		
		initListeners();		
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL);
			
		} catch (SQLException e) {			 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		}
		
	}
	
	private void initListeners() {
		btnConsultaValores.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent arg0) {
				testaConexaoBanco();			
			}
			
		});
	}
	
	private void testaConexaoBanco() {		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from AFERIDOR.CONSULTAS");
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		
	}

}
