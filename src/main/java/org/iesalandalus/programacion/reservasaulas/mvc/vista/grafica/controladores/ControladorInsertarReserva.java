package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorInsertarReserva {
	
	private static final String ER_HORA = "^([01]?[0-9]|2[0-3]):[0-5][0]$";
	
	private IControlador controladorMVC;
	
	private ObservableList<Aula> aulas = FXCollections.observableArrayList();
	private ObservableList<Profesor> profesores = FXCollections.observableArrayList();
		
	@FXML private ListView<Aula> listaAula;
	@FXML private ListView<Profesor> listaProfesor;
	
	
	@FXML private DatePicker dpFecha;
	@FXML private ComboBox<Tramo> cbTramoDia;
	@FXML private ComboBox<String> cbElegirTramo =  new ComboBox<>();
	@FXML private TextField tfTramoHora;
	
	@FXML private Button bntAceptar;
	@FXML private Button bntCerrar;
	
	
		
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	private class columnaAula extends ListCell<Aula> {
        @Override
        public void updateItem(Aula aula, boolean vacio) {
            super.updateItem(aula, vacio);
            if (vacio) {
            	setText("");
            } else {
            	setText(aula.getNombre());
            }
        }
    }
    
    private class columnaProfesor extends ListCell<Profesor> {
        @Override
        public void updateItem(Profesor profesor, boolean vacio) {
            super.updateItem(profesor, vacio);
            if (vacio) {
            	setText("");
            } else {
            	setText(profesor.getCorreo());
            }
        }
    }
    
    @FXML
	private void initialize() {
		cbTramoDia.setItems(FXCollections.observableArrayList(Tramo.values()));
		cbElegirTramo.setItems(FXCollections.observableArrayList("Tramo dia", "Tramo hora"));
		
		
    	listaAula.setItems(aulas);
    	listaAula.setCellFactory(l -> new columnaAula());
    	
    	listaProfesor.setItems(profesores);
    	listaProfesor.setCellFactory(l -> new columnaProfesor());
    	
    	
    	tfTramoHora.setDisable(true);
    	tfTramoHora.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_HORA, tfTramoHora));
    	
    	cbTramoDia.setDisable(true);
	}
    
        
    
    public void inicializa() {
    	aulas.setAll(FXCollections.observableArrayList(controladorMVC.getAulas()));
		profesores.setAll(FXCollections.observableArrayList(controladorMVC.getProfesores()));
		
    	listaAula.getSelectionModel().clearSelection();
    	listaProfesor.getSelectionModel().clearSelection();
        listaAula.setItems(aulas);
        listaProfesor.setItems(profesores);
    	dpFecha.setValue(LocalDate.now());
    	cbTramoDia.getSelectionModel().clearSelection();
    	cbElegirTramo.getSelectionModel().clearSelection();
    	tfTramoHora.setDisable(true);
    	cbTramoDia.setDisable(true);
    }

	
	@FXML
	private void cerrar() {
		((Stage) bntCerrar.getScene().getWindow()).close();
	}
	@FXML
	private void accionTramo() {
		if (cbElegirTramo.getValue().equals("Tramo dia")) {
			cbTramoDia.setDisable(false);
		}else if(cbElegirTramo.getValue().equals("Tramo hora")){
			tfTramoHora.setDisable(false);
		}
	}
	
	@FXML
	private void aceptar() {
		Aula aula = listaAula.getSelectionModel().getSelectedItem();
		Profesor profesor = listaProfesor.getSelectionModel().getSelectedItem();
		try {
			if (profesor != null) {
				Permanencia permanencia;
				if (tfTramoHora.isDisable()) {
					permanencia = new PermanenciaPorTramo(dpFecha.getValue(), cbTramoDia.getValue());
				} else {
					permanencia = new PermanenciaPorHora(dpFecha.getValue(),LocalTime.parse(tfTramoHora.getText(), DateTimeFormatter.ofPattern("HH:mm")));
				}
				Reserva reserva = new Reserva(profesor, aula,permanencia);
				controladorMVC.realizarReserva(reserva);
				Stage propietario = ((Stage) bntAceptar.getScene().getWindow());
				Dialogos.mostrarDialogoInformacion("Realizar Reserva", "Reserva realizada correctamente",
						propietario);
			} else {
				Dialogos.mostrarDialogoError("Profesor no encontrado", "No se ha encontrado a ningún profesor");
			}

		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Realizar Reserva", e.getMessage());
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
