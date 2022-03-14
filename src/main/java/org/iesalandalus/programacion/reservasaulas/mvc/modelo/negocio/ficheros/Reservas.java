package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.ficheros;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IAulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IProfesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IReservas;

public class Reservas implements IReservas{
	/*
	 * Atributos
	 */ 
	private static final String NOMBRE_FICHERO_RESERVAS="datos/reservas.dat";
	private List<Reserva> coleccionReservas;
	
	/*
	 * Constructores
	 */ 
	
	public Reservas() {
		coleccionReservas = new ArrayList<>();
	}
	
	public Reservas(Reservas reservas) {
		setReservas(reservas);
	}
	
	@Override
	public void comenzar() {
		leer();
		
	}
	private void leer() {
		File ficheroReserva= new File(NOMBRE_FICHERO_RESERVAS);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroReserva))){
			Reserva reserva = null;
			do {
				reserva= (Reserva) entrada.readObject();
				insertar(reserva);
			}while(reserva!= null);
			
		}catch (ClassNotFoundException e) {
			System.out.println("ERROR: No puedo encontrar la clase que tengo que leer");
		}catch (FileNotFoundException e) {
			System.out.println("ERROR: No se puede abir el fichero de aulas");
		}catch (EOFException e) {
			System.out.println("Fichero leido correctamente");
		}catch (IOException e) {
			System.out.println("ERROR en la Entrada/Salida");
		}catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void terminar() {
		
		escribir();
	}
	
	private void escribir() {
		File ficheroReserva = new File(NOMBRE_FICHERO_RESERVAS);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroReserva))){
			for(Reserva reserva : coleccionReservas) {
				salida.writeObject(reserva);
			}
		}catch (FileNotFoundException e) {
			System.out.println("ERROR: No se puede crear el fichero de aulas");
		}catch (IOException e) {
			System.out.println("ERROR en la Entrada/Salida");
			
		}
	}
	

	/*
	 * setReservas()
	 */
	
	private void setReservas(Reservas reservas) {
		if(reservas == null)
			throw new NullPointerException("ERROR: No se pueden copiar reservas nulas.");
		
		coleccionReservas=new ArrayList<>(reservas.coleccionReservas);
	}
	/*
	 * CopiaProfundaReservas()
	 */
	
	private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {
		List<Reserva> reservaCopia = new ArrayList<>();
		for(Reserva reserva : reservas) {
			reservaCopia.add(new Reserva(reserva));
		}
		return reservaCopia;
	}
	
	/*
	 * getReservas
	 */
	@Override
	public  List<Reserva> getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}
	
	
	/*
	 * getNumReservas()
	 */
	@Override
	public int getNumReservas() {
		return coleccionReservas.size();	
	}
	
	/*
	 * Insertar()
	 */
	@Override
	public void insertar (Reserva reservas) throws OperationNotSupportedException {
		if(reservas==null)
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula");
		
		if(!coleccionReservas.contains(reservas)) {
			coleccionReservas.add(new Reserva (reservas));
		}else {
			throw new OperationNotSupportedException("ERROR: Ya existe una reserva nombre.");
		}
	}
	
	/*
	 * Buscar()
	 */
	@Override
	public Reserva buscar(Reserva reservas) {
		if(reservas==null)
			throw new NullPointerException("ERROR: No se puede buscar un reserva nula.");
		
		int indice = coleccionReservas.indexOf(reservas);
		if(indice == -1) {
			return null;
		}else {
			return new Reserva(coleccionReservas.get(indice));
		}
	}
	
	/*
	 * Borrar()
	 */
	@Override
	public void borrar(Reserva reservas) throws OperationNotSupportedException {
		if(reservas==null)
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		
		if(!coleccionReservas.contains(reservas)) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");
		}else {
			coleccionReservas.remove(reservas);
		}	
	}
	
	/*
	 * Representar()
	 */
	
	@Override
	public List<String> representar() {
		List<String> representarReservas = new ArrayList<>();
		Iterator<Reserva> it = coleccionReservas.iterator();
		Reserva reserva;
		while(it.hasNext()) {
			reserva=it.next();
			representarReservas.add(new String(reserva.toString()));	
		}
		return representarReservas;
	}


	
	/*
	 * getReservasProfesor()
	 */
	@Override
	public List<Reserva> getReservasProfesor(Profesor profesor) {
		if(profesor==null)
			throw new NullPointerException("ERROR: El profesor no puede ser nulo ");
		
		List<Reserva> reservaProfesores = new ArrayList<>();
		Iterator<Reserva> it = coleccionReservas.iterator();
		while(it.hasNext()) {
			if(it.next().getProfesor().equals(profesor)) {
				reservaProfesores.add(it.next());
			}
		}
		
		return reservaProfesores;
	}
	
	/*
	 * getReservasAula()
	 */
	@Override
	public List<Reserva> getReservasAula(Aula aula) {
		if(aula==null)
			throw new NullPointerException("ERROR: El aula no puede ser nula ");
		
		List<Reserva> reservaAulas = new ArrayList<>();
		Iterator<Reserva> it = coleccionReservas.iterator();
		while(it.hasNext()) {
			if(it.next().getAula().equals(aula)) {
				reservaAulas.add(it.next());
			}
		}
		return reservaAulas;
	}
	
	/*
	 * getReservasPermanencia()
	 */
	@Override
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		if(permanencia==null)
			throw new NullPointerException("ERROR: La permanencia no puede ser nula ");
		
		List<Reserva> reservaPermanencias = new ArrayList<>();
		Iterator<Reserva> it = coleccionReservas.iterator();
		while(it.hasNext()) {;
			if(it.next().getPermanencia().equals(permanencia)) {
				reservaPermanencias.add(it.next());
			}
			
		}
		
		return reservaPermanencias;
	}
	/*
	 * ConsultarDisponibilidad
	 */
	@Override
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		boolean disponible=true;
		
		Iterator<Reserva> it = coleccionReservas.iterator();
		while(it.hasNext()){
			if(it.next().getAula().equals(aula) && it.next().getPermanencia().equals(permanencia)) {
				disponible=false;
			}
		}
		
		return disponible;
	}

	@Override
	public IAulas crearAulas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IProfesores crearProfesores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IReservas crearReservas() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*
	 * esMesSiguienteOPosterior()
	 */
	
	//private boolean esMesSiguienteOPosterior(Reserva reserva) {}
	
	/*
	 * getReservasProfesorMes()
	 */
	
	//private List<Reserva> getReservasProfesorMes(Profesor profesor, LocalDate dia){}
	
	/*
	 * gestReservaAulaDia()
	 */
	
	//private List<Reserva> gestReservaAulaDia(Profesor profesor, LocalDate dia){}
	
}
