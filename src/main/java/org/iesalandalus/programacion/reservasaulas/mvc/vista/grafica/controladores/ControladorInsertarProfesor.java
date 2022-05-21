package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladores;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorInsertarProfesor {
	
	private static final String ER_NOMBRE = "[A-Za-z]+";
	private static final String ER_CORREO = "[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)+";
	private static final String ER_TELEFONO = "[6]{1}[0-9]{8}";
	
	private IControlador controladorMVC;
	
	@FXML private TextField tfNombre;
	@FXML private TextField tfCorreo;
	@FXML private TextField tfTelefono;
	
	@FXML private Button bntAceptar;
	@FXML private Button bntCerrar;
	
	@FXML
	private void initialize() {
		tfNombre.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_NOMBRE, tfNombre));
		tfCorreo.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_CORREO, tfCorreo));
		tfTelefono.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_TELEFONO, tfTelefono));
	}
	
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	
	private Profesor getProfesor() {
		String nombre = tfNombre.getText();
		String correo = tfCorreo.getText();
		String telefono = tfTelefono.getText();
		
		return new Profesor(nombre,correo, telefono);
	}
	
	
	public void inicializa() {
    	tfNombre.setText("");
    	compruebaCampoTexto(ER_NOMBRE, tfNombre);
    	tfCorreo.setText("");
    	compruebaCampoTexto(ER_CORREO, tfCorreo);
    	tfTelefono.setText("");
    	compruebaCampoTexto(ER_CORREO, tfTelefono);
    }
	
	
	@FXML
	private void cerrar() {
		((Stage) bntCerrar.getScene().getWindow()).close();
	}
	
	@FXML
	private void aceptar() {
		Profesor profesor = null;
		try {
			profesor = getProfesor();
			controladorMVC.insertarProfesor(profesor);
			Stage aceptar = ((Stage) bntAceptar.getScene().getWindow());
			
			Dialogos.mostrarDialogoInformacion("Insertar Profesor", "Profesor insertado correctamente", aceptar);
		}catch(Exception e) {
			Dialogos.mostrarDialogoError("Insertar Profesor", e.getMessage());
		}
		
		
	}
	
	private void compruebaCampoTexto(String er, TextField campoTexto) {	
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		}
		else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}
	
}
