package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

public enum Tramo {

	MANANA("Horario Mañana"), TARDE("Horario Tarde");
	
	//Atributo
	
	private String cadenaAMostrar;
	
	/*
	 * Constructor
	 */
	
	private Tramo(String cadena) {
		this.cadenaAMostrar=cadena;
	}
	
	/*
	 * toString
	 */
	
	public String toString() {
		return "Cadena: "+cadenaAMostrar;
	}
}
