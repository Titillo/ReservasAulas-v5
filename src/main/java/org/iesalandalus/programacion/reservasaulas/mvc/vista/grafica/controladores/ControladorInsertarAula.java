package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladores;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorInsertarAula {
	
	private static final String ER_NOMBRE = "[A-Za-z-0-9].+";
	private static final String ER_PUESTOS = "[0-99]{1,2}"; 

	private IControlador controladorMVC;
	
	@FXML private TextField tfNombre;
	@FXML private TextField tfPuestos;
	
	@FXML private Button bntAceptar;
	@FXML private Button bntCerrar;
	
	@FXML
	private void initialize() {
		tfNombre.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_NOMBRE, tfNombre));
		tfPuestos.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_PUESTOS, tfPuestos));
	}
	
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	private Aula getAulas() {
		String nombre = tfNombre.getText();
		int puestos = Integer.parseInt(tfPuestos.getText());
		
		return new Aula(nombre, puestos);
	}
	
	public void inicializa() {
    	tfNombre.setText("");
    	compruebaCampoTexto(ER_NOMBRE, tfNombre);
    	tfPuestos.setText("");
    	compruebaCampoTexto(ER_PUESTOS, tfPuestos);
    }

	@FXML
	private void cerrar() {
		((Stage) bntCerrar.getScene().getWindow()).close();
	}
	
	@FXML
	private void aceptar() {
		Aula aula = null;
		try {
			aula = getAulas();
			controladorMVC.insertarAula(aula);
			Stage aceptar = ((Stage) bntAceptar.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Insertar Aula", "Aula insertada correctamente", aceptar);
		}catch(Exception e) {
			Dialogos.mostrarDialogoError("Insertar Aula", e.getMessage());
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
