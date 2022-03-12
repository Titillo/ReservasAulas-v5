package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IAulas;

public class Aulas implements IAulas {
	/*
	 * Atributos
	 */ 

	private List<Aula> coleccionAulas;
	
	/*
	 * Constructores
	 */ 
	
	public Aulas() {
		coleccionAulas = new ArrayList<>();
	}
	
	public Aulas(Aulas aulas) {
		setAulas(aulas);
	}
	
	/*
	 * setAulas()
	 */
	
	private void setAulas(Aulas aulas) {
		if(aulas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar aulas nulas.");
		}else {
		coleccionAulas = new ArrayList<>(aulas.coleccionAulas);
		}
	}
	
	/*
	 * getAulas()
	 */
	@Override
	public List<Aula> getAulas(){
		return copiaProfundaAulas(coleccionAulas);
	}
	
	/*
	 * CopiaProfundaAulas()
	 */
	
	private List<Aula> copiaProfundaAulas(List<Aula> aulas) {
		List<Aula> aulaCopia = new ArrayList<>();	
		for(Aula aula:aulas) {
			aulaCopia.add(new Aula(aula));
		}
		return aulaCopia;
	}
	
	/*
	 * getNumAulas()
	 */
	
	@Override
	public int getNumAulas() {
		return coleccionAulas.size();	
	}
		
	/*
	 * Insertar()
	 */
	
	@Override
	public void insertar (Aula aula) throws OperationNotSupportedException {
		if(aula==null)
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		
		if(!coleccionAulas.contains(aula)){
			coleccionAulas.add(new Aula(aula));
		}else {
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
		}
	}
	
	/*
	 * Buscar()
	 */
	
	@Override
	public Aula buscar(Aula aula) {
		if(aula==null)
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		
		int indice = coleccionAulas.indexOf(aula);
		if(indice == -1) {
			return null;
		}else {
			return new Aula(coleccionAulas.get(indice));
		}
	}
	
	/*
	 * Borrar()
	 */
	@Override
	public void borrar(Aula aula) throws OperationNotSupportedException {
		if(aula==null)
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		
		if(!coleccionAulas.contains(aula)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}else {
			coleccionAulas.remove(aula);
		}	
	}
	
	/*
	 * Representar()
	 */
	
	@Override
	public List<String> representar() {
		List<String> representarAula = new ArrayList<>();
		
		Iterator<Aula> it = coleccionAulas.iterator();
		Aula aula;
		while(it.hasNext()) {
			aula =it.next();
			representarAula.add(new String(aula.toString()));
		}
		return representarAula;
	}

}
