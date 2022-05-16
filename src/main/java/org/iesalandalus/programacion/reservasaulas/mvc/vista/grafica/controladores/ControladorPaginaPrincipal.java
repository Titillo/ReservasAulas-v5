package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladores;


import java.awt.Panel;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;

import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ControladorPaginaPrincipal {
	
	private IControlador controladorMVC;
	private ControladorInsertarAula controladorInsertarAulas;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	@FXML
	private MenuItem miSalir;
	
	//Aulas
	@FXML
	private Button btnInsertarAula;
	@FXML
	private Button bntBorrarAula;
	@FXML
	private Button btnListarAula;
	@FXML
	private Button bntReservasAula;
	@FXML
	private Button bntDisponibilidad;
	
	//Profesores
	@FXML
	private Button bntInsertarProfesor;
	@FXML
	private Button bntBorrarProfesor;
	@FXML
	private Button bntListarProfesor;
	@FXML
	private Button bntReservasProfesor;
	
	//Reservas
	@FXML
	private Button bntInsertarReserva;
	@FXML
	private Button bntBorrarReserva;
	@FXML
	private Button bntListarReserva;
	
	
	private Stage listarAulas;
	private ControladorListarAula cListarAulas;

	private Stage anadirAula;
	private ControladorInsertarAula cAnadirAula;

	private Stage borrarAula;
	private ControladorBorrarAula cBorrarAula;

	private Stage listarReservasAula;
	private ControladorListarAula cListarReservasAula;

	private Stage comprobarDisponibilidad;
	private ControladorDisponibilidad cComprobarDisponibilidad;

	private Stage listarProfesores;
	private ControladorListarProfesor cListarProfesores;

	private Stage anadirProfesor;
	private ControladorInsertarProfesor cAnadirProfesor;

	private Stage borrarProfesor;
	private ControladorBorrarProfesor cBorrarProfesor;

	private Stage listarReservasProfesor;
	private ControladorListarProfesor cListarReservasProfesor;

	private Stage listarReservas;
	private ControladorListarReserva cListarReservas;

	private Stage anadirReserva;
	private ControladorInsertarReserva cAnadirReserva;
	
	private Stage borrarReserva;
	private ControladorBorrarReserva cBorrarReserva;
	
	@FXML
	private void salirAplicacion(ActionEvent event) {	
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro que desea salir de la aplicación?",null)) {
			controladorMVC.terminar();
			System.exit(0);
		}
	}
	

	
	
	@FXML
	private void insertarAula(ActionEvent event) {
		if (anadirAula == null) {
			anadirAula = new Stage();
			FXMLLoader cargadorAnadirAula = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/AnadirAula.fxml"));
			Parent panel = cargadorAnadirAula.load();
			cAnadirAula = cargadorAnadirAula.getController();
			cAnadirAula.setControladorMVC(controladorMVC);
			cAnadirAula.inicializa();
			Scene escenaAnadirAula = new Scene(panel);
			anadirAula.setTitle("Añadir Aula");
			anadirAula.initModality(Modality.APPLICATION_MODAL);
			anadirAula.setScene(escenaAnadirAula);
			anadirAula.setResizable(false);
		} else {
			cAnadirAula.inicializa();
		}
	}
	
	@FXML
	private void borrarAula(ActionEvent event) {
		
	}
	@FXML
	private void listarAula(ActionEvent event) {
		
	}
	@FXML
	private void reservasAula(ActionEvent event) {
		
	}
	@FXML
	private void disponibilidad(ActionEvent event) {
		
	}
	
	@FXML
	private void borrarProfesor(ActionEvent event) {
		
	}
	@FXML
	private void insertarProfesor(ActionEvent event) {
		
	}
	@FXML
	private void listarProfesor(ActionEvent event) {
		
	}
	
	@FXML
	private void insertarReserva(ActionEvent event) {
		
	}
	
	@FXML
	private void borrarReserva(ActionEvent event) {
		
	}
	@FXML
	private void listarReserva(ActionEvent event) {
		
	}
	@FXML
	private void reservasProfesor(ActionEvent event) {
		
	}
	
	

	
}
