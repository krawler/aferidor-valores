package aferidor.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import aferidor.dao.CampoFiltroDao;
import aferidor.dao.ConsultaDao;
import aferidor.dao.CuboDao;
import aferidor.dao.DWDao;
import aferidor.dao.Dao;
import aferidor.dao.DimensaoDao;
import aferidor.dao.TransacionalDao;
import aferidor.model.CampoFiltro;
import aferidor.model.Consulta;
import aferidor.model.Cubo;
import aferidor.model.Dimensao;
import factory.ConexaoPostgre;
import factory.IConnectionFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AferidorController implements Initializable {
	
	@FXML
	private ComboBox<String> cmbCuboDataWareHouse;
	@FXML
	private ComboBox<String> cmbConsultaTransacional;	
	@FXML
	private DatePicker dtpDataInicialConsulta;
	@FXML
	private DatePicker dtpDataFinalConsulta;
	@FXML
	private ComboBox<String> cmbDimensao;
	@FXML
	private ComboBox<String> cmbCampoConsulta;
	@FXML
	private TableView<CampoFiltro> tbvFiltros;
	@FXML
	private TableColumn<CampoFiltro, String> tbcCampoDimensao;
	@FXML
	private TableColumn<CampoFiltro, Double> tbcValorFiltro;	
	@FXML
	private Button btnConsultaValores;
	@FXML
	private Button btnLimpar;
	@FXML
	private Button btnCadastroConsulta;
	@FXML
	private CheckBox chkIsData;
	@FXML
	private Label lblTotalValorTrans;
	@FXML
	private Label lblTotalRegistrosTrans;
	@FXML
	private Label lblTotalValorDW;
	@FXML
	private Label lblTotalRegistrosDW;
	@FXML
	private Label lblDiferenca;
	private Dao consultaDao;
	private Dao dimensaoDao;
	private Dao cuboDao;
	private DWDao dWDao;
	private TransacionalDao transacionalDao;
	private Dao campoFiltroDao;
	private URL url;
	private ResourceBundle bundle;
	private Map<String, Consulta> mapConsultas = new HashMap<String, Consulta>();
	private Double valorTotalDW;
	private Integer totalRegistrosDW;
	private Double valorTotalTrans;
	private Integer totalRegistrosTrans;
	private List<CampoFiltro> itemsToBeAddToTableView;
	
	@Override
	@FXML
	public void initialize(URL url, ResourceBundle bundle) {
				
		dtpDataInicialConsulta.requestFocus();
		consultaDao = new ConsultaDao();
		dimensaoDao = new DimensaoDao();
		cuboDao = new CuboDao();
		dWDao = new DWDao();
		transacionalDao = new TransacionalDao();
		campoFiltroDao = new CampoFiltroDao();
		tbvFiltros = new TableView<CampoFiltro>();
		
		initListeners();		
		initcombos();
		initMaps();
		
		this.url = url;
		this.bundle = bundle;
		
		tbcCampoDimensao.setCellValueFactory(new PropertyValueFactory<CampoFiltro, String>("nome"));		
		tbvFiltros.getItems().setAll(getItemsToBeAddToTableView());
	}
	
	private void initMaps() {		
		List<Consulta> consultas = consultaDao.obterTodos();
		for (Consulta consulta : consultas) {
			mapConsultas.put(consulta.getNome(), consulta);
		}
	}

	private void initcombos() {
		ObservableList<String> items = FXCollections.observableArrayList(consultaDao.listarNomesCombo());
		cmbConsultaTransacional.setItems(items);		
		items = FXCollections.observableArrayList(dimensaoDao.listarNomesCombo());
		cmbCuboDataWareHouse.setItems(items);		
		items = FXCollections.observableArrayList(cuboDao.listarNomesCombo());
		cmbCuboDataWareHouse.setItems(items);
	}
	
	@SuppressWarnings("unchecked")
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
		
		btnCadastroConsulta.setOnAction(new EventHandler() {
			@Override
			public void handle(Event arg0) {
				try {
					new CadastroConsultaController("CadastroConsulta.fxml").start(new Stage());
				} catch (Exception e) {				
					e.printStackTrace();
				}
			}
			
		});
		
		cmbCuboDataWareHouse.setOnAction(new EventHandler() {
			@Override
			public void handle(Event arg0) {				
				carregaDimensoesCubo();
			}
		});
		
		cmbConsultaTransacional.setOnAction(new EventHandler() {
			@Override
			public void handle(Event arg0) {
				
			}
		});
		
		btnConsultaValores.setOnAction(new EventHandler() {
			@Override
			public void handle(Event arg0) {				
				try {
					consultaRetornaValores();
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}			
		});
		
		cmbDimensao.setOnAction(new EventHandler() {
			@Override
			public void handle(Event arg0) {
				carregaCamposDimensao();
			}
		});
					
	}
	
	private void consultaRetornaValores() {
		String consultaDW = mapConsultas.get(cmbConsultaTransacional.getValue()).getSqlDW();
		String[] resultsConsultaDW = null;
		String consultaTrans = mapConsultas.get(cmbConsultaTransacional.getValue()).getSqlConsulta();
		String[] resultsConsultaTrans = null;
		try {
			resultsConsultaDW = dWDao.resultConsultaDW(consultaDW, null);
			resultsConsultaTrans = transacionalDao.resultConsultaTransacional(consultaTrans, null);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		String strValorTotalDW = resultsConsultaDW[0];
		String strTotalRegistrosDW = resultsConsultaDW[1];
		totalRegistrosDW.parseInt(strTotalRegistrosDW);
		valorTotalDW = Double.valueOf(strValorTotalDW);
		lblTotalValorDW.setText("Total R$:" + strValorTotalDW);
		lblTotalRegistrosDW.setText("Total registros: " + strTotalRegistrosDW);
		
		String strValorTotalTrans = resultsConsultaTrans[0];
		String strTotalRegistrosTrans = resultsConsultaTrans[1];
		totalRegistrosTrans.parseInt(strTotalRegistrosTrans);
		valorTotalTrans = Double.valueOf(strValorTotalTrans);		
		lblTotalValorTrans.setText("Total R$: " + strValorTotalTrans);
		lblTotalRegistrosTrans.setText("Total registros: " + strTotalRegistrosTrans);
		
		Double diferencaValor = 0.0;
		diferencaValor = valorTotalTrans - valorTotalDW;
		DecimalFormat df = new DecimalFormat("0.00");		
		lblDiferenca.setText("R$" + df.format(diferencaValor));		
	}
	
	@FXML
	public void carregaDimensoesCubo() {
		Map<String, Integer> mapRowCubo = new HashMap<String,Integer>();
		List<Cubo> cubos = cuboDao.obterTodos();
		for (Cubo cubo : cubos) {
			mapRowCubo.put(cubo.getNome(), cubo.getId());
		}		
		ObservableList<String> items = FXCollections
									   .observableArrayList(dimensaoDao.
											   				listarNomesComboByFilterField("ID_CUBO",mapRowCubo.get(cmbCuboDataWareHouse.getValue())));
		cmbDimensao.setItems(items);
	}
	
	@FXML
	private void carregaCamposDimensao() {
		Map<String, Integer> mapRowDimensao = new HashMap<String,Integer>();
		List<Dimensao> dimensoes = dimensaoDao.obterTodos();
		for (Dimensao dimensao : dimensoes) {
			mapRowDimensao.put(dimensao.getNome(), dimensao.getId());
		}
		ObservableList<String> items = FXCollections
										.observableArrayList(campoFiltroDao
												.listarNomesComboByFilterField("ID_DIMENSAO", mapRowDimensao.get(cmbDimensao.getValue())));
		cmbCampoConsulta.setItems(items);
	}
	
	private void limpaTela() {
		cmbConsultaTransacional.setPromptText("");
		cmbCuboDataWareHouse.setPromptText("");
		cmbDimensao.setPromptText("");
		dtpDataInicialConsulta.setPromptText("");
		dtpDataFinalConsulta.setPromptText("");
		cmbCampoConsulta.setPromptText("");
	}

	public List<CampoFiltro> getItemsToBeAddToTableView() {
		itemsToBeAddToTableView = campoFiltroDao.obterTodos();
		return itemsToBeAddToTableView;
	}	

}
