package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Vista implements IVista{
	/*
	private final static String ERROR ="";
	private final static String NOMBRE_VALIDO ="";
	private final static String CORREO_VALIDO ="";
	*/
	
	private IControlador controlador;
	
	
	public Vista() {
		Opcion.setVista(this);
	}
	
	@Override
	public void setControlador(IControlador controlador) {
		if(controlador == null ) {
			throw new NullPointerException("ERROR: El controtador no pude ser nulo");
		}
		this.controlador=controlador;
	}
	
	/*
	 * Comenzar
	 */
	@Override
	public void comenzar() {
		Consola.mostrarCabecera("Gestión de reservas de aulas del IES Al-Ã�ndalus");
		int elegirOpcion;
		do {
			Consola.mostrarMenu();
			elegirOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(elegirOpcion);
			opcion.ejecutar();
			
		}while(elegirOpcion != Opcion.SALIR.ordinal());
		
	}
	
	/*
	 * Salir
	 */
	@Override
	public void salir() {
		controlador.terminar();
	}
	
	
	/*
	 * MÃ©todos insertar
	 */
	
	public void insertarAula()  {
		Consola.mostrarCabecera("Insetar Alumno");
		try {
			controlador.insertarAula(Consola.leerAula());
			System.out.println("Aula insertada correctamente");
		}catch(OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertarProfesor()  {
		Consola.mostrarCabecera("Insetar Profesor");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente");
		}catch(OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/*
	 * MÃ©todos borrar
	 */
	
	public void borrarAula()  {
		Consola.mostrarCabecera("Borrar Aula");
		try {
			controlador.borrarAula(Consola.leerAula());
		}catch(OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		try {
			controlador.borrarProfesor(Consola.leerProfesor());
		}catch(OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * MÃ©todos buscar
	 */
	
	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
		try {
			Aula aula = controlador.buscarAula(Consola.leerAula());
			String mensaje = (aula!=null) ? aula.toString():"Aula no econtrada";
			System.out.println(mensaje);
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar Profesor");
		try {
			Profesor profesor = controlador.buscarProfesor(Consola.leerProfesor());
			String mensaje = (profesor!=null) ? profesor.toString():"Profesor no econtrado";
			System.out.println(mensaje);
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * MÃ©todos listar
	 */
	
	public void listarAulas() {
		Consola.mostrarCabecera("Listar Aulas");
		try {
			List<String> aulas = controlador.representarAulas();
			//(int i=0;i<aulas.leng-1;i++) {
				System.out.println(aulas.toString());
			//}
		}catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarProfesores() {
		try {
			Consola.mostrarCabecera("Listar Profesores");
			List<String> profesores = controlador.representarProfesores();
			System.out.println(profesores);
		}catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarReservas() {
		try {
			Consola.mostrarCabecera("Listar Reservas");
			List<String> reservas = controlador.representarReservas();
			System.out.println(reservas);
		}catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Realizar reservas
	 */
	
	public void realizarReserva()  {
		Consola.mostrarCabecera("Realizar Reserva");
		
		try {
			Permanencia permanencia=new Permanencia(Consola.leeDia(),Consola.leerTramo());
			Profesor profesor = new Profesor(Consola.leerProfesor());
			Aula aula = new Aula(Consola.leerAula());
			Reserva reserva = new Reserva(profesor,aula,permanencia);
			controlador.realizarReserva(reserva);
			System.out.println("Aula insertada correctamente");
		}catch(OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
		
	/*
	 * Anular reservas
	 */
	
	public void anularReserva() {
		Consola.mostrarCabecera("Borrar Reserva");
		try {
			Permanencia permanencia=new Permanencia(Consola.leeDia(),Consola.leerTramo());
			Profesor profesor = new Profesor(Consola.leerProfesor());
			Aula aula = new Aula(Consola.leerAula());
			Reserva reserva = new Reserva(profesor,aula,permanencia);
			controlador.anularReserva(reserva);
		}catch(OperationNotSupportedException | IllegalArgumentException e) {
				System.out.println(e.getMessage());
		}
		
	}

	
	
	public void listaReservasAula() {
		Consola.mostrarCabecera("Listar Reservas de Aulas");
		List<Reserva> reservas = new ArrayList<>(controlador.getReservasAula(Consola.leerAula()));
		Iterator<Reserva> it = reservas.iterator();
		if(reservas.size()==0) {
			System.out.println("No hay reservas de aulas que mostrar");
		}
		while(it.hasNext()) {
			System.out.println(it);
		}
	}
	
	public void listaReservasProfesor() {
		Consola.mostrarCabecera("Listar Reservas de Aulas");
		List<Reserva> reservas = new ArrayList<>(controlador.getReservasProfesor(Consola.leerProfesor()));
		Iterator<Reserva> it = reservas.iterator();
		if(reservas.size()==0) {
			System.out.println("No hay reservas de aulas que mostrar");
		}
		while(it.hasNext()) {
			System.out.println(it);
		}
	}

	
	/*
	 * Consultar disponibilidad
	 */
	
	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("Consultar Disponibilidad");
		Permanencia permanencia = new Permanencia(Consola.leeDia(),Consola.leerTramo());
		Aula aula = new Aula(Consola.leerAula());
		
		if(controlador.consultarDisponibilidad(aula, permanencia)) {
			System.out.println("Si hay disponibilidad");
		}else {
			System.out.println("No hay disponibilidad");
		}
		
	}
	

	
	
}
