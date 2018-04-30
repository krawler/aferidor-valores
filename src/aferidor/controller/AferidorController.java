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

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		lblMsgMaisUmaData.setVisible(false);
		cmbMaisUmaData.setVisible(false);
		dtpDataInicialConsulta.requestFocus();
		
		initListeners();
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
		
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:h2:~/aferidor", "sa", "onsoft");
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from CONSULTA_TRANSACIONAL");
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		
	}

}
