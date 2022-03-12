package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

public enum FactoriaFuenteDatos {
	public static final FactoriaFuenteDatos MEMORIA;
	
	FactoriaFuenteDatos() {
	
	}
	
	public abstract IFuenteDatos crear();
}
