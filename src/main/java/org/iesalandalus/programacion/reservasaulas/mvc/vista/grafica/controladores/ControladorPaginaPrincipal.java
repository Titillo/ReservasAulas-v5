package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladores;


import java.io.IOException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class ControladorPaginaPrincipal {
	
	private IControlador controladorMVC;
	
	private ObservableList<Aula> ListaAula = FXCollections.observableArrayList();
	private ObservableList<Profesor> ListaProfesor = FXCollections.observableArrayList();
	private ObservableList<Reserva> ListaReserva = FXCollections.observableArrayList();
	
	
	@FXML private TableView<Aula> tvAula;
	@FXML private TableColumn<Aula, String> colNombreAula;
	@FXML private TableColumn<Aula, String> colPuestosAula;
	
	
	
	@FXML private TableView<Profesor> tvProfesor;
	@FXML private TableColumn<Profesor, String> colNombreProf;
	@FXML private TableColumn<Profesor, String> colCorreoProf;
	@FXML private TableColumn<Profesor, String> colTelefonoProf;
	
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
	private void initialize() {
		//Aula
		tvAula.setItems(ListaAula);
		colNombreAula.setCellValueFactory(aula -> new SimpleStringProperty(aula.getValue().getNombre()));
		colPuestosAula.setCellValueFactory(aula -> new SimpleStringProperty(String.valueOf(aula.getValue().getPuestos())));
		
		//Profesor
		tvProfesor.setItems(ListaProfesor);
		colNombreProf.setCellValueFactory(aula -> new SimpleStringProperty(aula.getValue().getNombre()));
		colCorreoProf.setCellValueFactory(aula -> new SimpleStringProperty(aula.getValue().getCorreo()));
		colTelefonoProf.setCellValueFactory(aula -> new SimpleStringProperty(aula.getValue().getTelefono()));
		
	}
	
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
		insertaNuevaReserva();
		insertaReserva.showAndWait();
	}
	
	//Borrar
	
	@FXML
	void borrarAula (ActionEvent event) throws IOException {
		Aula aula=null;
		try {
			aula=tvAula.getSelectionModel().getSelectedItem();
			if(aula != null && Dialogos.mostrarDialogoConfirmacion("Borrar Aula", "¿Deseas borrar esta Aula?", null)) {
				controladorMVC.borrarAula(aula);
				ListaAula.remove(aula);
				Dialogos.mostrarDialogoInformacion("Borrar Aula", "Aula borrada correctamente");
				
			}
		}catch (Exception e) {
			Dialogos.mostrarDialogoError("Borrar Aula", e.getMessage());
		}
	}
	
	@FXML
	void borrarProfesor (ActionEvent event) throws IOException {
		Profesor profesor=null;
		try {
			profesor=tvProfesor.getSelectionModel().getSelectedItem();
			if(profesor != null && Dialogos.mostrarDialogoConfirmacion("Borrar Profesor", "¿Deseas borrar este Profesor?", null)) {
				controladorMVC.borrarProfesor(profesor);
				ListaProfesor.remove(profesor);
				Dialogos.mostrarDialogoInformacion("Borrar Profesor", "Profesor borrado correctamente");
				
			}
		}catch (Exception e) {
			Dialogos.mostrarDialogoError("Borrar Profesor", e.getMessage());
		}
		
		
	}
	@FXML
	void borrarReserva (ActionEvent event) throws IOException {
		
		
	}
	
	//Listar
	
	
	@FXML
	void listarAula (ActionEvent event) throws IOException {
		ListaAula.setAll(controladorMVC.getAulas());
	}
	
	@FXML
	void listarProfesor (ActionEvent event) throws IOException {
		ListaProfesor.setAll(controladorMVC.getProfesores());
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
	
	private void insertaNuevaReserva() throws IOException {
		if(insertaReserva ==null) {
			insertaReserva = new Stage();
			
			FXMLLoader abreInsertaReserva = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/InsertarReserva.fxml"));
			VBox panelInsertaReserva = abreInsertaReserva.load();
			
			controladorInserReserva=abreInsertaReserva.getController();
			controladorInserReserva.setControladorMVC(controladorMVC);
			controladorInserReserva.inicializa();
			
			Scene escenaInsertaReserva = new Scene(panelInsertaReserva);
			insertaReserva.setTitle("Inserta Reserva");
			insertaReserva.setScene(escenaInsertaReserva);
			insertaReserva.initModality(Modality.APPLICATION_MODAL);
			insertaReserva.setResizable(false);
			
		}else {
			controladorInserProfesor.inicializa();
		}
	}

		
	

}
