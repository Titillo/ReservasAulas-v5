package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladores;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorInsertarAula {
	
	private static final String ER_OBLIGATORIO = ".+";
	private static final String ER_PUESTOS = "0?\\d{1,2}|50";
	
	private IControlador controladorMVC;
	
	@FXML
	private TextField tfNombre;
	@FXML
	private TextField tfPuestos;
	@FXML
	private Button bntAceptar;
	@FXML
	private Button bntCerrar;
	
	
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	public void inicializa() {
		tfNombre.setText("");
		tfPuestos.setText("");

	}
	@FXML
	private void initialize() {
		tfNombre.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfNombre));
		tfPuestos.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_PUESTOS, tfPuestos));
	}
	
	@FXML
	private void aceptar() {
		Aula aula = null;
		try {
			aula = getAula();
			controladorMVC.insertarAula(aula);
			Stage propietario = ((Stage) bntAceptar.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Aula", "Aula añadida satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Aula", e.getMessage());
		}
	}
	
	@FXML
	private void cerrar() {
		((Stage) bntCerrar.getScene().getWindow()).close();
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
	
	private void compruebaCampoTexto(String er, TextField campoTexto) {
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		} else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}
}
