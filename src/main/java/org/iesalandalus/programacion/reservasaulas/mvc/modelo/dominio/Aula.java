package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

public class Aula {

	/*
	 * Atributos
	 */
	private static final float PUNTOS_POR_PUESTO = 0.5f;
	private static final int MN_PUESTOS = 10;
	private static final int MAX_PUESTOS = 50;
	
	private String nombre;
	private int puestos;
	/*
	 * Constructores
	 */
	
	public Aula(String nombre, int puestos) {
		setNombre(nombre);
		setPuestos(puestos);
	}
	
	public Aula (Aula aula) {
		if(aula==null)
			throw new NullPointerException("ERROR: El nombre del aula no puede ser nulo.");
		
		setNombre(aula.nombre);
	}

	/*
	 * Get y Set dia y puestos
	 */
	
	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getPuestos() {
		return puestos;	
	}
	
	private void setPuestos(int puestos) 
	{
		if(puestos<MN_PUESTOS && puestos>MAX_PUESTOS) {
			throw new IllegalArgumentException("ERROR: El número de puestos no es correcto.");
		}else {
			this.puestos=puestos;
		}	
	}
	
	public float getPuntos() {
		return PUNTOS_POR_PUESTO*puestos;
	}
	
	public static Aula getAulaFicticia(String aula) {
		return new Aula(aula,20);
	}

	/*
	 * HashCode e Equals
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Aula other = (Aula) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	/*
	 * toString
	 */

	@Override
	public String toString() {
		return "Aula: [ nombre= " + nombre + " ]";
	}
	
	
}
