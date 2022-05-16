package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

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
		}catch(OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertarProfesor()  {
		Consola.mostrarCabecera("Insetar Profesor");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente");
		}catch(OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
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
		}catch(OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		try {
			controlador.borrarProfesor(Consola.leerProfesor());
		}catch(OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
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
		}catch(IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar Profesor");
		try {
			Profesor profesor = controlador.buscarProfesor(Consola.leerProfesor());
			String mensaje = (profesor!=null) ? profesor.toString():"Profesor no econtrado";
			System.out.println(mensaje);
		}catch(IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * MÃ©todos listar
	 */
	
	public void listarAulas() {
		Consola.mostrarCabecera("Listar Aulas");
		List<String> aulas = controlador.representarAulas();
		if (aulas.size() > 0) {
			for (String aula : aulas) {
				System.out.println(aula);
			}

		} else {
			System.out.println("ERROR: No hay aulas que listar. Debe insertar primero un aula en el sistema.");
		}
	}
	
	public void listarProfesores() {
		List<String> profesores = controlador.representarProfesores();
		if (profesores.size() > 0) {
			for (String profesor:profesores) {
				System.out.println(profesor);
			}
		} else {
			System.out.println("ERROR: No hay profesores que listar. Debe insertar primero un profesor en el sistema.");
		}
	}
	
	public void listarReservas() {
		List<String> reservas = controlador.representarReservas();
		if (reservas.size() > 0) {
			for (String reserva: reservas) {
				System.out.println(reserva);
			}
		} else {
			System.out.println("ERROR: No hay reservas que listar. Debe insertar primero una reserva.");
		}
	}
	
	/*
	 * Realizar reservas
	 */
	
	public void realizarReserva()  {
		Consola.mostrarCabecera("Realizar Reserva");
		
		try {
			Profesor profesor = Consola.leerProfesorFicticio();
			Profesor profesorEncontrado = controlador.buscarProfesor(profesor);

			if (profesorEncontrado != null) {	
				Aula aula = Consola.leerAulaFicticia();
				Aula aulaEncontrada = controlador.buscarAula(aula);
					if (aulaEncontrada != null) {
						Permanencia permanencia = Consola.leerPermanencia();
						Reserva reserva = new Reserva(profesorEncontrado, aulaEncontrada, permanencia);
						controlador.realizarReserva(reserva);
						System.out.println("Reserva realizada correctamente");
					}else {
						System.out.println("ERROR: El aula " + aula.getNombre() + ", no está registrada.");
					}
			} else {
				System.out.println("ERROR: El correo " + profesor.getCorreo() + ", no está registrado.");
			}
		}catch(OperationNotSupportedException | IllegalArgumentException | NullPointerException  e) {
			System.out.println(e.getMessage());
		}
	}
		
	/*
	 * Anular reservas
	 */
	
	public void anularReserva() {
		Consola.mostrarCabecera("Borrar Reserva");
		try {
			controlador.anularReserva(Consola.leerReservaFicticia());
			System.out.println("Reserva anulada correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		
	}

	
	
	public void listaReservasAula() {
		Consola.mostrarCabecera("Listar Reservas de Aulas");
		List<Reserva> reservas = controlador.getReservasAula(Consola.leerAulaFicticia());
		Iterator<Reserva> it = reservas.iterator();
		if(reservas.size() > 0) {
			while(it.hasNext())
				System.out.println(it);
		}else {
			System.out.println("No hay reservas de aulas que mostrar");
		}
	}
	
	public void listaReservasProfesor() {
		Consola.mostrarCabecera("Listar Reservas de Aulas");
		List<Reserva> reservas = controlador.getReservasProfesor(Consola.leerProfesorFicticio());
		Iterator<Reserva> it = reservas.iterator();
		if(reservas.size()>=0) {
			while(it.hasNext()) 
				System.out.println(it);
		}else {
			System.out.println("No hay reservas de aulas que mostrar");
		}
	}

	
	/*
	 * Consultar disponibilidad
	 */
	
	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("Consultar Disponibilidad");
		try {
			Aula aula = Consola.leerAulaFicticia();
			Aula aulaRegistrada = controlador.buscarAula(aula);

			if (aulaRegistrada != null) {
				Permanencia permanencia = Consola.leerPermanencia();
				if (controlador.consultarDisponibilidad(aula, permanencia)) {
					System.out.println("Aula: " + aula.getNombre() + "disponible para la permanencia: " + permanencia);
				} else {
					System.out.println("Aula: " + aula.getNombre() + "no disponible para la permanencia: " + permanencia);
				}
			} else {
				System.out.println("ERROR: El aula " + aula.getNombre() + " no está registrada.");
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}

		
	}
	

	
	
}
