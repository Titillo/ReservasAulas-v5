package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
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
	
	//Leer Aula
	
	public static Aula leerAula() {
		return new Aula(leerAulaFicticia());
	}
	
	public static int leerNumeroPuestos() {
		int puestos;
		do {
		System.out.println("Numero de puestos del aula: ");
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
		return new Aula(leerNombreAula(),leerNumeroPuestos());
	}
	
	
	//Leer profesor
	
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
	
	public static Tramo leerTramo() {
		Tramo tramo;
		int cadenaTramo;
		do {
			System.out.println("Tramo horario => 0 para Mañana | 1 para Tarde ");
			cadenaTramo=Entrada.entero();
		}while(cadenaTramo < 0 || cadenaTramo > 1);
		
		if(cadenaTramo==0) {
			tramo=Tramo.MANANA;
		}else {
			tramo=Tramo.TARDE;
		}
		
		return tramo;
	}
	
	public static LocalDate leeDia() {
		String cadenaDia;
		System.out.println("Dime el dia de la semana: ");
		cadenaDia=Entrada.cadena();
		return LocalDate.parse(cadenaDia,FORMATO_DIA);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
