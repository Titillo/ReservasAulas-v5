package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Permanencia {
	/*
	 * Atributos
	 */
	
	protected static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDate dia;
	
	/*
	 * Constructores
	 */
	
	public Permanencia(LocalDate dia) {
		setDia(dia);
	}
	public Permanencia(Permanencia per) {
		if(per==null)
			throw new NullPointerException("ERROR: El tramo de una permanencia no puede ser nulo.");
		
		setDia(per.dia);
	}
	
	/*
	 * Get y Set dia
	 */

	public LocalDate getDia() {
		return dia;
	}
	private void setDia(LocalDate dia) {
		if(dia==null)
			throw new NullPointerException("ERROR: El d√≠a de una permanencia no puede ser nulo.");
		

		this.dia = dia;
	}
	
	//MÈtodos abstractos
	
	public abstract int getPuntos();
	public abstract int hashCode();
	public abstract boolean equals(Object object);
	
	
	/*
	 * toString
	 */
	
	@Override
	public String toString() {
		return "Permanencia: [ dia= " + dia.format(FORMATO_DIA)+ "]";
	}
	
	
	
	
	
}
