package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Consola(){
		//Evito que se genere el constructor pÃºblico por defecto
	}
	
	public static void mostrarMenu() {
		mostrarCabecera("Gestión reserva aulas IES AL-ANDALUS");
		for(Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}
	
	
	public static void mostrarCabecera(String cabecera) {
		System.out.println("**************************************************");
		System.out.println(cabecera);
		System.out.println("**************************************************");
	}
	
	public static int elegirOpcion() {
		int opcion;
		do {
			System.out.println("Elige una opción (0-14): ");
			opcion=Entrada.entero();
		}while((opcion < 0) || (opcion >14));
		
		return opcion;
	}
	
	/*
	 * Leer Aula
	 */
	
	public static Aula leerAula() {
		System.out.print("Nombre del aula: ");
		String nombre = Entrada.cadena();
		System.out.print("Número de puestos del aula: ");
		int puestos = Entrada.entero();
		return new Aula(nombre, puestos);
	}
	
	public static int leerNumeroPuestos() {
		int puestos;
		do {
		System.out.println("Número de puestos del aula: ");
		puestos=Entrada.entero();
		}while(puestos<10 && puestos>50);
		
		return puestos;
	}
	
	public static String leerNombreAula() {
		String nombreAula;
		System.out.println("Nombre del aula: ");
		nombreAula=Entrada.cadena();
		return nombreAula;
	}
	
	public static Aula leerAulaFicticia() {
		String aula = leerNombreAula();
		return Aula.getAulaFicticia(aula);
	}
	
	
	/*
	 * Leer profesor
	 */
	
	public static Profesor leerProfesor() {
		String nombreProfesor=Consola.leerNombreProfesor();
		System.out.println("Correo del Profesor: ");
		String correoProfesor=Entrada.cadena();
		System.out.println("Teléfono del Profesor: ");
		String telefonoProfesor=Entrada.cadena();
		return new Profesor(nombreProfesor,correoProfesor,telefonoProfesor);
	}
	
	public static String leerNombreProfesor() {
		String nombreProfesor;
		System.out.println("Nombre del Profesor: ");
		nombreProfesor=Entrada.cadena();
		return nombreProfesor;
	}
	
	public static Profesor leerProfesorFicticio() {
		String correo;
		System.out.print("Introduce el correo del profesor: ");
		correo=Entrada.cadena();
		return Profesor.getProfesorFicticio(correo);

	}
	
	/*
	 * LeeTramo
	 */
	public static Tramo leerTramo() {
		System.out.print("Tramo de la reserva. Mañana --> 0, Tarde --> 1 : ");
		int tramoReserva = Entrada.entero();
		Tramo tramo = null;
		if (tramoReserva < 0 || tramoReserva >= Tramo.values().length) {
			System.out.println("ERROR: La opción elegida no corresponde con ningún tramo.");
		} else {
			tramo = Tramo.values()[tramoReserva];
		}
		return tramo;
	}
	
	/*
	 * LeerDia
	 */
	public static LocalDate leerDia() {

		LocalDate dia = null;
		do {
			System.out.printf("Introduza una fecha(dd/MM/yyyy):");
			String fechaStr = Entrada.cadena();

			try {
				dia = LocalDate.parse(fechaStr, FORMATO_DIA);
			} catch (DateTimeParseException e) {
				System.out.println("ERROR: El formato de la fecha no es correcto. Formato correcto (dd/MM/yyyy)");
				dia = null;
			}

		} while (dia == null);

		return dia;

	}

	/*
	 * LeePermanecia
	 */
	public static Permanencia leerPermanencia() {
		int ordinalPermanencia = Consola.elegirPermanencia();
		LocalDate dia = leerDia();
		Permanencia permanencia = null;
		if (ordinalPermanencia == 0) {
			Tramo tramo = leerTramo();
			permanencia = new PermanenciaPorTramo(dia, tramo);
		} else if (ordinalPermanencia == 1) {
			LocalTime hora = leerHora();
			permanencia = new PermanenciaPorHora(dia, hora);
		}
		return permanencia;
	}
	
	/*
	 * ElegirPermanencia
	 */

	public static int elegirPermanencia() {
		int ordinalPermanencia;
		do {
			System.out.print("\nTipo de permanencia. Por Tramo --> 0, Por Hora --> 1 : ");
			ordinalPermanencia = Entrada.entero();
		} while (ordinalPermanencia < 0 || ordinalPermanencia > 1);
		return ordinalPermanencia;
	}

	private static LocalTime leerHora() {
		LocalTime hora = null;
		String formato = "HH:mm";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formato);
		do {
			System.out.printf("Introduce la hora (%s): ", formato);
			String horaStr = Entrada.cadena();
			try {
				hora = LocalTime.parse(horaStr, dtf);
			} catch (DateTimeParseException e) {
				System.out.println("ERROR: El formato de la hora no es correcto.");
				hora = null;
			}

		} while (hora == null);

		return hora;
	}

	/*
	 * LeerReserva
	 */
	public static Reserva leerReserva() {
		Profesor profesor = leerProfesorFicticio();
		Aula aula = leerAulaFicticia();
		Permanencia permanencia = leerPermanencia();
		return new Reserva(profesor, aula, permanencia);
	}

	/*
	 * LeerReservaFicticia
	 */
	public static Reserva leerReservaFicticia() {
		return Reserva.getReservaFicticia(leerAulaFicticia(), leerPermanencia());
	}

	
}
