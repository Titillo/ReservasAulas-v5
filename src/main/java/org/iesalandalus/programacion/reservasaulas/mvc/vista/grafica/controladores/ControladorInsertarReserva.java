package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladores;

import java.time.LocalDate;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.ficheros.Aulas;
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
	
	
	@FXML private TextField tfCorreoReserva;
	@FXML private DatePicker dpFecha;
	@FXML private ComboBox<Tramo> cbTramoDia;
	@FXML private TextField tfTramoHora;
	
	@FXML private Button bntAceptar;
	@FXML private Button bntCerrar;
	
	@FXML
	private void initialize() {
    	listaAula.setItems(aulas);
    	listaAula.setCellFactory(l -> new columnaAula());
    	listaProfesor.setItems(profesores);
    	listaProfesor.setCellFactory(l -> new columnaProfesor());
    	tfTramoHora.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_HORA, tfTramoHora));

	}
		
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	public void setAlumnos(ObservableList<Aula> aulas) {
	    this.aulas.setAll(aulas);
	}
	    
	public void setLibros(ObservableList<Profesor> profesores) {
	    this.profesores.setAll(profesores);
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
    /*
    private Reserva getReserva() {
		Aula alumno = listaAula.getSelectionModel().getSelectedItem();
		Profesor libro = listaProfesor.getSelectionModel().getSelectedItem();
		
		
		Permanencia permanecia;
				
		LocalDate fechaPrestamo = dpFecha.getValue();
		return new Prestamo(alumno, libro, fechaPrestamo);
	}
    
    
    */
    
    public void inicializa() {
    	listaAula.getSelectionModel().clearSelection();
    	listaProfesor.getSelectionModel().clearSelection();
        listaAula.setItems(aulas);
        listaProfesor.setItems(profesores);
    	dpFecha.setValue(LocalDate.now());
    }
	
    
    
    
    
    
    
    
    
    
    
    
    /*
    
    public void mostrarPrestamosAlumno(Alumno alumno) {
    	try {
    		if (alumno != null) {
    			prestamosAlumno.setAll(controladorMVC.getPrestamos(alumno));
    		}
		} catch (IllegalArgumentException e) {
			prestamosAlumno.setAll(FXCollections.observableArrayList());
		}
    	actualizaPrestamos();
    }
    
    public void mostrarPrestamosLibro(Libro libro) {
    	try {
    		if (libro != null) {
    			prestamosLibro.setAll(controladorMVC.getPrestamos(libro));
    		}
		} catch (IllegalArgumentException e) {
			prestamosLibro.setAll(FXCollections.observableArrayList());
		}
    	actualizaPrestamos();
    }
    
	
	*/
	
	@FXML
	private void cerrar() {
		((Stage) bntCerrar.getScene().getWindow()).close();
	}
	
	@FXML
	private void aceptar() {
		/*
		Profesor profesor = null;
		try {
			profesor = getProfesor();
			controladorMVC.insertarProfesor(profesor);
			Stage aceptar = ((Stage) bntAceptar.getScene().getWindow());
			
			Dialogos.mostrarDialogoInformacion("Insertar Profesor", "Profesor insertado correctamente", aceptar);
		}catch(Exception e) {
			Dialogos.mostrarDialogoError("Insertar Profesor", e.getMessage());
		}
		*/
		
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
