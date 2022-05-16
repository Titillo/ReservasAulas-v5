package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladores;


import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class ControladorPaginaPrincipal {
	
	private IControlador controladorMVC;

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
	
	@FXML
	private void salirAplicacion(ActionEvent event) {
		
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro que desea salir de la aplicación?", null)) {
			controladorMVC.terminar();
			System.exit(0);
		}
	}
	

	
	
	@FXML
	private void insertarAula(ActionEvent event) {
		
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
