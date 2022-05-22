package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.mongodb.utilidades;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDB {
	
	public static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	
	private static final String SERVIDOR = "localhost";	
	private static final int PUERTO = 27017;
	private static final String BD = "reservasaulas";
	private static final String USER = "reservasaulas";
	private static final String PASSWORD = "reservasaulas-2022";
	private static final String URI = String.format("mongodb://%s:%s@%s:%d/?authSource=%s&authMechanism=SCRAM-SHA-1", USER, PASSWORD, SERVIDOR, PUERTO, BD);
	//Aula
	public static final String AULA = "aula";
	public static final String PUESTOS = "puestos";
	
	//Profesor
	public static final String PROFESOR = "profesor";
	public static final String NOMBRE = "nombre";
	public static final String CORREO = "correo";
	public static final String TELEFONO = "telefono";
	
	//Reserva
	public static final String PERMANENCIA = "permanencia";
	public static final String TIPO = "tipo";
	public static final String DIA = "dia";
	public static final String HORA = "hora";
	public static final String TRAMO = "tramo";
	
	public static final String PROFESOR_CORREO = PROFESOR + "." + CORREO;
	public static final String AULA_NOMBRE = AULA + "." + NOMBRE;
	public static final String TIPO_HORA = "POR_HORA";
	public static final String TIPO_TRAMO = "POR_TRAMO";
	public static final String PERMANENCIA_DIA = PERMANENCIA + "." + DIA;
	public static final String PERMANENCIA_TRAMO = PERMANENCIA + "." + TRAMO;
	public static final String PERMANENCIA_HORA = PERMANENCIA + "." + HORA;
	
	
	private static MongoClient conexion = null;
	
	private MongoDB() {
		//No se cree el constructor por defecto
	}
	
	public static MongoDatabase getBD() {
		if (conexion == null) {
			establecerConexion();
		}
		return conexion.getDatabase(BD);
	}
	
	private static MongoClient establecerConexion() {
		 Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		    mongoLogger.setLevel(Level.SEVERE);
			if (conexion == null) {
				conexion = MongoClients.create(URI);
				System.out.println("Conexi�n a MongoDB realizada correctamente.");	
			}
			return conexion;
	}
	
	public static void cerrarConexion() {
		if (conexion != null) {
			conexion.close();
			conexion = null;
			System.out.println("Conexi�n a MongoDB cerrada.");
		}
	}
	
	
	//Aula

	public static Document getDocumento(Aula aula) {
		if (aula == null) {
			return null;
		}
		String nombre = aula.getNombre();
		int puestos = aula.getPuestos();
		return new Document().append(NOMBRE, nombre).append(PUESTOS, puestos);
	}

	public static Document getCriterioOrdenacionAula() {
		return new Document().append(NOMBRE, 1);
	}

	public static Aula getAula(Document documentoAula) {
		if (documentoAula == null) {
			return null;
		}
		return new Aula(documentoAula.getString(NOMBRE), documentoAula.getInteger(PUESTOS));
	}

	//Profesor
	
	public static Document getDocumento(Profesor profesor) {
		if (profesor == null) {
			return null;
		}
		String nombre = profesor.getNombre();
		String correo = profesor.getCorreo();
		String telefono = profesor.getTelefono();
		return new Document().append(NOMBRE, nombre).append(CORREO, correo).append(TELEFONO, telefono);
	}

	
	public static Document getCriterioOrdenacionProfesor() {
		return new Document().append(CORREO, 1);
	}

	
	public static Profesor getProfesor(Document documentoProfesor) {
		if (documentoProfesor == null) {
			return null;
		}
		return new Profesor(documentoProfesor.getString(NOMBRE), documentoProfesor.getString(CORREO),documentoProfesor.getString(TELEFONO));
	}

	//Reservas

	public static Document getDocumento(Reserva reserva) {
		if (reserva == null) {
			return null;
		}
		Profesor profesor = reserva.getProfesor();
		Aula aula = reserva.getAula();
		Permanencia permanencia = reserva.getPermanencia();
		String dia = permanencia.getDia().format(FORMATO_DIA);
		Document dProfesor = getDocumento(profesor);
		Document dAula = getDocumento(aula);
		Document dPermanencia = new Document().append(DIA, dia);
		if (permanencia instanceof PermanenciaPorTramo) {
			String tramo = ((PermanenciaPorTramo) permanencia).getTramo().name();
			dPermanencia.append(TIPO, TIPO_TRAMO).append(TRAMO, tramo);
		} else {
			String hora = ((PermanenciaPorHora) permanencia).getHora().format(FORMATO_HORA);
			dPermanencia.append(TIPO, TIPO_HORA).append(HORA, hora);
		}
		return new Document().append(PROFESOR, dProfesor).append(AULA, dAula).append(PERMANENCIA, dPermanencia);
	}

	public static Reserva getReserva(Document documentoReserva) {
		if (documentoReserva == null) {
			return null;
		}
		Aula aula = new Aula(getAula((Document) documentoReserva.get(AULA)));
		Profesor profesor = new Profesor(getProfesor((Document) documentoReserva.get(PROFESOR)));
		Document dPermanencia = (Document) documentoReserva.get(PERMANENCIA);
		LocalDate dia = LocalDate.parse(dPermanencia.getString(DIA), FORMATO_DIA);
		String tipoPermanencia = dPermanencia.getString(TIPO);
		Permanencia permanencia = null;
		if (tipoPermanencia.equals(TIPO_TRAMO)) {
			Tramo tramo = Tramo.valueOf(dPermanencia.getString(TRAMO));
			permanencia = new PermanenciaPorTramo(dia, tramo);
		} else {
			LocalTime hora = LocalTime.parse(dPermanencia.getString(HORA), FORMATO_HORA);
			permanencia = new PermanenciaPorHora(dia, hora);
		}
		return new Reserva(profesor, aula, permanencia);
	}
	

}
