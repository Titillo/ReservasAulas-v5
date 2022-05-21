package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.io.Serializable;

public class Reserva implements Serializable{
	
	/*
	 * Atributos
	 */
	
	private Permanencia permanencia;
	private Aula aula;
	private Profesor profesor;
	
	/*
	 * Constructores
	 */
	
	public Reserva (Profesor profesor, Aula aula, Permanencia permanencia) {
		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);
	}
	
	public Reserva( Reserva reserva) {
		if(reserva==null)
			throw new NullPointerException("ERROR: No se puede copiar una reserva nula.");
		
		setProfesor(reserva.profesor);
		setAula(reserva.aula);
		setPermanencia(reserva.permanencia);
	}
	
	/*
	 * Get y Set permanencia
	 */
	
	
	public Permanencia getPermanencia() {
		return permanencia;
	}
	private void setPermanencia(Permanencia permanencia) {
		if(permanencia==null)
			throw new NullPointerException("ERROR: La reserva se debe hacer para una permanencia concreta.");
		
		if (permanencia instanceof PermanenciaPorTramo) {
			this.permanencia = new PermanenciaPorTramo(permanencia.getDia(),
					((PermanenciaPorTramo) permanencia).getTramo());
		} else if (permanencia instanceof PermanenciaPorHora) {
			this.permanencia = new PermanenciaPorHora(permanencia.getDia(),
					((PermanenciaPorHora) permanencia).getHora());
		}
		
		this.permanencia = permanencia;
	}
	
	/*
	 * Get y Set aula
	 */
	
	public Aula getAula() {
		return aula;
	}
	private void setAula(Aula aula) {
		if(aula==null)
			throw new NullPointerException("ERROR: La reserva debe ser para un aula concreta.");
		
		this.aula = aula;
	}
	
	/*
	 * Get y Set profesores
	 */
	
	public Profesor getProfesor() {
		return profesor;
	}
	private void setProfesor(Profesor profesor) {
		if(profesor==null)
			throw new NullPointerException("ERROR: La reserva debe estar a nombre de un profesor.");
		
		this.profesor = profesor;
	}
	
	/*
	 * getReservaFicticioa
	 */
	
	
	public static Reserva getReservaFicticia(Aula aula, Permanencia permanencia) 
	{
		if(permanencia==null)
			throw new NullPointerException("ERROR: La reserva se debe hacer para una permanencia concreta.");
		if(aula==null)
			throw new NullPointerException("ERROR: La reserva debe ser para un aula concreta.");
		
		
		String nombre = "Profesor Ficticio";
		String correo = "CorreoProfesor@gamil.com";		
		Profesor profesores = new Profesor(nombre,correo);
		
		return new Reserva(profesores,aula, permanencia);
	}
	
	/*
	 * getPuntos
	 */

	public float getPuntos() {
		return getAula().getPuntos() + getPermanencia().getPuntos();
	}
	
	/*
	 * HashCode e Equals
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aula == null) ? 0 : aula.hashCode());
		result = prime * result + ((permanencia == null) ? 0 : permanencia.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		if (aula == null) {
			if (other.aula != null)
				return false;
		} else if (!aula.equals(other.aula))
			return false;
		if (permanencia == null) {
			if (other.permanencia != null)
				return false;
		} else if (!permanencia.equals(other.permanencia))
			return false;
		return true;
	}
	
	/*
	 * toString
	 */
	
	@Override
	public String toString() {
		return "Reserva [permanencia=" + permanencia + ", aula=" + aula + ", profesor=" + profesor + "]";
	}
	
	
	
	
	
	
	
	

}
