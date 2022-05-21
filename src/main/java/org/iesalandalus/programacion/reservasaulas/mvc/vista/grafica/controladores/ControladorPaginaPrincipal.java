package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladores;


import java.io.IOException;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.ficheros.Aulas;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class ControladorPaginaPrincipal {
	
	private IControlador controladorMVC;
	    
	
	private ObservableList<String> ListAula = FXCollections.observableArrayList();
	@FXML private TableView<String> tvAula;
	@FXML private TableColumn<String, String> colNombreAula;
	@FXML private TableColumn<String, String> colPuestosAula;
	
	
	
	@FXML private TableView<String> tvProfesor;
	@FXML private TableColumn<String, String> colNombreProf;
	@FXML private TableColumn<String, String> colCorreoProf;
	@FXML private TableColumn<String, String> colTelefonoProf;
	
	@FXML private TableView<String> tvReserva;
	@FXML private TableColumn<String, String> colNombreReserva;
	@FXML private TableColumn<String, String> colCorreoReserva;
	@FXML private TableColumn<String, String> colTelefonoReserva;
	@FXML private TableColumn<String, String> colAulaReserva;
	@FXML private TableColumn<String, String> colPuestosReserva;
	@FXML private TableColumn<String, String> colTramoReserva;
	@FXML private TableColumn<String, String> colPuntosReserva;
	@FXML private TableColumn<String, String> colFechaReserva;
	
	
	private Stage insertaAula;
	private ControladorInsertarAula controladorInserAula;
	private Stage insertaProfesor;
 	private ControladorInsertarProfesor controladorInserProfesor;
 	private Stage insertaReserva;
	private ControladorInsertarReserva controladorInserReserva;
	
	private Aulas representaAulas;
	
	
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	
	@FXML
	private void accionSalir() {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?", null)) {
			controladorMVC.terminar();
			System.exit(0);
		}
	}
	
	//Insertar 
	
	@FXML
	void insertarAula (ActionEvent event) throws IOException {
		insertaNuevaAula();
		insertaAula.showAndWait();
	}
	
	@FXML
	void insertarProfesor (ActionEvent event) throws IOException {
		insertarNuevoProfesor();
		insertaProfesor.showAndWait();
		
	}
	@FXML
	void insertarReserva (ActionEvent event) throws IOException {
		
		
	}
	
	//Borrar
	
	@FXML
	void borrarAula (ActionEvent event) throws IOException {
		
		
	}
	
	@FXML
	void borrarProfesor (ActionEvent event) throws IOException {
		
		
	}
	@FXML
	void borrarReserva (ActionEvent event) throws IOException {
		
		
	}
	
	//Listar
	
	
	@FXML
	void listarAula (ActionEvent event) throws IOException {
		
		
		tvAula.setItems(FXCollections.observableArrayList(controladorMVC.representarAulas()));
		
		colNombreAula.setCellValueFactory(nombre -> new SimpleStringProperty(nombre.getValue().substring(nombre.getValue().indexOf("nombre=") + 7, nombre.getValue().indexOf(", puestos="))));
		colPuestosAula.setCellValueFactory(nombre -> new SimpleStringProperty(nombre.getValue().substring(nombre.getValue().lastIndexOf(", puestos=") + 10)));
		
	
	
	}
	
	@FXML
	void listarProfesor (ActionEvent event) throws IOException {
		
	}
	@FXML
	void listarReserva (ActionEvent event) throws IOException {
		
		
	}
	
	
	//Reservas
	
	
	@FXML
	void reservasAula (ActionEvent event) throws IOException {
		
		
	}
	
	@FXML
	void reservasProfesor (ActionEvent event) throws IOException {
		
		
	}
	
	//Disponibilidad
	
	@FXML
	void disponibleAula (ActionEvent event) throws IOException {
		
		
	}
	
	
	
	
	
	
	
	
	
	private void insertaNuevaAula() throws IOException {
		if(insertaAula==null) {
			insertaAula = new Stage();
			
			FXMLLoader abreInsertaAula = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/InsertarAula.fxml"));
			VBox panelInsertaAula = abreInsertaAula.load();
			
			controladorInserAula = abreInsertaAula.getController();
			controladorInserAula.setControladorMVC(controladorMVC);
			controladorInserAula.inicializa();
			
			Scene escenaInsertaAula = new Scene(panelInsertaAula);
			insertaAula.setTitle("Inserta Aula");
			insertaAula.setScene(escenaInsertaAula);
			insertaAula.initModality(Modality.APPLICATION_MODAL);
			insertaAula.setResizable(false);
		}else {
			controladorInserAula.inicializa();
		}
		
	}
	
	private void insertarNuevoProfesor() throws IOException{
		if(insertaProfesor ==null) {
			insertaProfesor = new Stage();
			
			FXMLLoader abreInsertaProfesor = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/InsertarProfesor.fxml"));
			VBox panelInsertaProfesor = abreInsertaProfesor.load();
			
			controladorInserProfesor=abreInsertaProfesor.getController();
			controladorInserProfesor.setControladorMVC(controladorMVC);
			controladorInserProfesor.inicializa();
			
			Scene escenaInsertaProfesor = new Scene(panelInsertaProfesor);
			insertaProfesor.setTitle("Inserta Profesor");
			insertaProfesor.setScene(escenaInsertaProfesor);
			insertaProfesor.initModality(Modality.APPLICATION_MODAL);
			insertaProfesor.setResizable(false);
			
		}else {
			controladorInserProfesor.inicializa();
		}
		
	}
	
	

		
	

}
