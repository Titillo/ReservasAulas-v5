package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;

public class PermanenciaPorTramo extends Permanencia{
	
	private static final int PUNTOS = 10;
	
	private Tramo tramo;
	
	public PermanenciaPorTramo(LocalDate dia, Tramo tramo) {
		super(dia);
		if(dia==null)
			throw new NullPointerException("ERROR");
		
		setTramo(tramo);
	}
	
	public PermanenciaPorTramo(PermanenciaPorTramo permTramo) {
		super(permTramo);
		if(permTramo==null)
			throw new NullPointerException("ERROR: ");
		setTramo(permTramo.getTramo());
	}
	
	public Tramo getTramo() {
		return tramo;
	}

	private void setTramo(Tramo tramo) {
		if(tramo==null)
			throw new NullPointerException("ERROR: ");
		
		this.tramo = tramo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tramo == null) ? 0 : tramo.hashCode());
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
		PermanenciaPorTramo other = (PermanenciaPorTramo) obj;
		if (tramo != other.tramo)
			return false;
		return true;
	}

	@Override
	public int getPuntos() {
		return PUNTOS;
	}

	@Override
	public String toString() {
		return "PermanenciaPorTramo [tramo=" + tramo + "]";
	}
	
	
	

}
