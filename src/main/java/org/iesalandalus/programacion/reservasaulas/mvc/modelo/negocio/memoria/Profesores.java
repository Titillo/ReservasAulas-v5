package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IProfesores;

public class Profesores implements IProfesores{
	/*
	 * Atributos
	 */ 
	
	private List<Profesor> coleccionProfesores;
	
	/*
	 * Constructores
	 */ 
	
	public Profesores() {
		coleccionProfesores = new ArrayList<>();
	}
	
	public Profesores(Profesores profesores) {
		setProfesores(profesores);
	}

	/*
	 * setProfesores()
	 */
	
	private void setProfesores(Profesores profesores) {
		if(profesores == null)
			throw new NullPointerException("ERROR: No se pueden copiar profesores nulos.");
		
		coleccionProfesores=new ArrayList<>(profesores.coleccionProfesores);
	}
	/*
	 * CopiaProfundaProfesor()
	 */
	
	private List<Profesor> copiaProfundaProfesores(List<Profesor> profesores) {
		List<Profesor> profesorCopia = new ArrayList<>();
		for(Profesor profesor : profesores) {
			profesorCopia.add(new Profesor(profesor));
		}
		return profesorCopia;
	}
	
	/*
	 * getProfesores
	 */
	@Override
	public  List<Profesor> getProfesores() {
		return copiaProfundaProfesores(coleccionProfesores);
	}
	
	
	/*
	 * getNumProfesores()
	 */
	@Override
	public int getNumProfesores() {
		return coleccionProfesores.size();	
	}
	

	/*
	 * Insertar()
	 */
	@Override
	public void insertar (Profesor profesor) throws OperationNotSupportedException {
		if(profesor==null)
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		
		if(!coleccionProfesores.contains(profesor)) {
			coleccionProfesores.add(new Profesor (profesor));
		}else {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");
		}
	}
	
	/*
	 * Buscar()
	 */
	@Override
	public Profesor buscar(Profesor profesor) {
		if(profesor==null)
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		
		int indice = coleccionProfesores.indexOf(profesor);
		if(indice == -1) {
			return null;
		}else {
			return new Profesor(coleccionProfesores.get(indice));
		}
	}
	
	/*
	 * Borrar()
	 */
	@Override
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if(profesor==null)
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		
		if(!coleccionProfesores.contains(profesor)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}else {
			coleccionProfesores.remove(profesor);
		}	
	}

	/*
	 * Representar()
	 */
	
	@Override
	public List<String> representar() {
		List<String> representarProfesores = new ArrayList<>();
		Iterator<Profesor> it = coleccionProfesores.iterator();
		Profesor profesor;
		while(it.hasNext()) {
			profesor=it.next();
			representarProfesores.add(new String(profesor.toString()));	
		}
		return representarProfesores;
	}

	@Override
	public Aulas crearAulas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profesores crearProfesores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservas crearReservas() {
		// TODO Auto-generated method stub
		return null;
	}
}