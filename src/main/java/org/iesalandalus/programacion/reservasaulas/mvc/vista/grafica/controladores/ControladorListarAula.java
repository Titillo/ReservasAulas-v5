package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladores;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorListarAula {
	private IControlador controladorMVC;
	
	@FXML
	private TextField tfNombre;
	@FXML
	private TextField tfPuestos;
	@FXML
	private Button bntInsertar;
	@FXML
	private Button bntCancelar;
	
	
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	@FXML
	private void anadirAula() {
		Aula aula = null;
		try {
			aula = getAula();
			controladorMVC.insertarAula(aula);
			Stage propietario = ((Stage) bntInsertar.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Aula", "Aula añadida satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Aula", e.getMessage());
		}
	}
	
	@FXML
	private void cancelar() {
		((Stage) bntCancelar.getScene().getWindow()).close();
	}
	
	private Aula getAula() {
		Aula aula = null;
		try {
			String nombre = tfNombre.getText();
			if (!nombre.equals("")) {
				int puestos = Integer.parseInt(tfPuestos.getText());
				aula = new Aula(nombre, puestos);
			} else {
				Dialogos.mostrarDialogoError("Añadir Aula", "Debe introducir el nombre del aula");
			}
		} catch (NumberFormatException e) {
			Dialogos.mostrarDialogoError("Añadir Aula", e.getMessage());
		}
		return aula;
	}
	
	
}
