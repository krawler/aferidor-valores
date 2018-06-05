package aferidor.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import aferidor.dao.ConsultaDao;
import aferidor.dao.Dao;
import aferidor.dao.DimensaoDao;
import aferidor.model.Consulta;
import aferidor.model.Dimensao;
import factory.ConexaoPostgre;
import factory.IConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	@FXML
	private Button btnLimpar;
	private Dao consultaDao;
	private Dao dimensaoDao;
	

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		
		lblMsgMaisUmaData.setVisible(false);
		cmbMaisUmaData.setVisible(false);
		dtpDataInicialConsulta.requestFocus();
		consultaDao = new ConsultaDao();
		dimensaoDao = new DimensaoDao();
		
		initListeners();		
		initcombos();				
	}
	
	private void initcombos() {		
		
		ObservableList<String> items = FXCollections.observableArrayList(consultaDao.listarNomesCombo());
		cmbConsultaTransacional.setItems(items);		
		items = FXCollections.observableArrayList(dimensaoDao.listarNomesCombo());
		cmbCuboDataWareHouse.setItems(items);
		
		
	}
	
	private void initListeners() {
		btnConsultaValores.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent arg0) {
				consultaDao.obterTodos();			
			}
			
		});
		
		btnLimpar.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent arg0) {
				limpaTela();	
			}
			
		});
		
		cmbDimensaoFiltro.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {				 
				System.out.println(cmbDimensaoFiltro.getValue());
			}
		});
	}
	
	private void limpaTela() {
		cmbConsultaTransacional.setPromptText("");
		cmbCuboDataWareHouse.setPromptText("");
		cmbDimensaoFiltro.setPromptText("");
		dtpDataInicialConsulta.setPromptText("");
		dtpDataFinalConsulta.setPromptText("");
		cmbCampoConsulta.setPromptText("");
	}
	

}
