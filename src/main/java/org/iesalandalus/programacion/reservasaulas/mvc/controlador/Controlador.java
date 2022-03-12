package org.iesalandalus.programacion.reservasaulas.mvc.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IModelo;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;

public class Controlador implements IControlador {
	/*
	 * Atributos
	 */
	
	private IModelo modelo;
	private IVista vista;
	
	/*
	 * Constructores
	 */
	public Controlador(IModelo modelo, IVista vista) {
		if(modelo == null) 
			throw new IllegalArgumentException("ERROR: El modelo no puede ser nulo");
		if(vista == null) 
			throw new IllegalArgumentException("ERROR: La vista no puede ser nula");
		
		this.modelo=modelo;
		this.vista=vista;
		this.vista.setControlador(this);
		
	}
	
	/*
	 * Comenzar
	 */
	
	@Override
	public void comenzar() {
		vista.comenzar();
	}
	
	/*
	 * Terminar
	 */
	
	@Override
	public void terminar() {
		System.out.println("Hasta prontoooo!!!");
	}
	
	/*
	 * Métodos insertar
	 */
	
	@Override
	public void insertarAula(Aula aula) throws OperationNotSupportedException {
		modelo.insertarAula(aula);
	}
	
	@Override
	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.insertarProfesor(profesor);
	}
	
	/*
	 * Métodos borrar
	 */
	
	@Override
	public void borrarAula(Aula aula) throws OperationNotSupportedException {
		modelo.borrarAula(aula);
	}
	
	@Override
	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.borrarProfesor(profesor);
	}
	
	/*
	 * Métodos buscar
	 */
	
	@Override
	public Aula buscarAula(Aula aula) {
		return modelo.buscarAula(aula);
	}
	
	@Override
	public Profesor buscarProfesor(Profesor profesor) {
		return modelo.buscarProfesor(profesor);
	}
	
	/*
	 * Métodos representar
	 */
	
	@Override
	public List<String> representarAulas() {
		return modelo.representarAulas();
	}
	
	@Override
	public List<String> representarProfesores() {
		return modelo.representarProfesores();
	}
	
	@Override
	public List<String> representarReservas() {
		return modelo.representarReservas();
	}
	
	/*
	 * Realizar reservas
	 */
	
	@Override
	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		modelo.realizarReserva(reserva);
	}
	
	/*
	 * Anular reservas
	 */
	
	@Override
	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		modelo.anularReserva(reserva);
	}
	
	/*
	 * getReservas
	 */
	
	@Override
	public List<Reserva> getReservasAula(Aula aula) {
		return modelo.getReservasAula(aula);
	}
	
	@Override
	public List<Reserva> getReservasProfesor(Profesor profesor) {
		return modelo.getReservasProfesor(profesor);
	}
	
	@Override
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		return modelo.getReservasPermanencia(permanencia);
	}
	
	/*
	 * Consultar disponibilidad
	 */
	
	@Override
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return modelo.consultarDisponibilidad(aula, permanencia);
	}

}
