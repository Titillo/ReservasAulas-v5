package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.utilidades.Dialogos;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class VistaGrafica extends Application implements IVista {
	
	private static IControlador controladorMVC = null;

	@Override
	public void setControlador(IControlador controlador) {
		controladorMVC = controlador;
	}

	@Override
	public void comenzar() {
		launch(this.getClass());
	}

	@Override
	public void salir() {
		controladorMVC.terminar();
	}
	
	@Override
	public void start(Stage Principal) {
		try {
			Parent root = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/PaginaPrincipal.fxml"));
			
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(LocalizadorRecursos.class.getResource("estilos/estilos.css").toExternalForm());
			Principal.setScene(scene);
			Principal.setTitle("Gesti�n IES AL-ANDALUS");
			Principal.setOnCloseRequest(e -> confirmarSalida(Principal, e));
			Principal.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void confirmarSalida(Stage Principal, WindowEvent e) {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?", Principal)) {
			controladorMVC.terminar();
			Principal.close();
		}
		else {
			e.consume();	
		}
	}

}
