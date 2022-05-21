package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladores.ControladorPaginaPrincipal;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.utilidades.Dialogos;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
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
			
			FXMLLoader abrePaginaPrincipal = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/PaginaPrincipal.fxml"));
			VBox panelPaginaPrincipal = abrePaginaPrincipal.load();
			
			ControladorPaginaPrincipal controladorPaginaPrincipal = abrePaginaPrincipal.getController();
			controladorPaginaPrincipal.setControladorMVC(controladorMVC);
		
			Scene scene = new Scene(panelPaginaPrincipal);
			Principal.setScene(scene);
			Principal.setTitle("Gestión IES AL-ANDALUS");
			Principal.setOnCloseRequest(e -> confirmarSalida(Principal, e));
			Principal.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void confirmarSalida(Stage Principal, WindowEvent e) {
		if (Dialogos.mostrarDialogoConfirmacion("Salir de la Aplicación", "¿Realmente quieres salir?", Principal)) {
			controladorMVC.terminar();
			Principal.close();
		}
		else {
			e.consume();	
		}
	}

}
