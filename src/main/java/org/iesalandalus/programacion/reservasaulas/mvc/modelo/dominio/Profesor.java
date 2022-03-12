package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

public class Profesor {
	
	/*
	 * Constantes
	 */
	final static private String ER_TELEFONO="(6|7)[ -]*([0-9][ -]*){8}";
	final static private String ER_CORREO="[A-Za-z0-9+_.-]+@(.+)$";
	
	/*
	 * Atributos
	 */ 
	
	private String nombre;
	private String correo;
	private String telefono;
	
	/*
	 * Constructores
	 */ 
	
	public Profesor(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
	}
	public Profesor(String nombre, String correo, String telefono) {
		setNombre(nombre);
		setCorreo(correo);
		setTelefono(telefono);
	}
	public Profesor(Profesor profesor) {
		if(profesor==null)
			throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
		
		setNombre(profesor.nombre);
		setCorreo(profesor.correo);
		setTelefono(profesor.telefono);
	}
	
	/*
	 * Get y Set nombre
	 */
	
	public String getNombre() {
		return nombre;
	}
	private void setNombre(String nombre) {
		if(nombre==null)
			throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
		if(nombre=="")
			throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");
		
		this.nombre=formateNombre(nombre);
	}
	
	/*
	 * Get y Set correo
	 */
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		if(correo==null)
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
		if(correo=="")
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		if(correo.matches(ER_CORREO)) {
			this.correo = correo;
		}else {
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		}
	}
	
	/*
	 * Get y Set telefono
	 */
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		if(telefono=="")
			throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
		if(telefono.matches(ER_TELEFONO)) {
			this.telefono = telefono;
		}else {
			throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
		}
	}
	
	/*
	 * formateaNombre
	 */
	
	private String formateNombre(String nombre) {
		if(nombre==null)
			throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
		char [] caracterresNombre = nombre.toCharArray();
		for(int i=0; i<caracterresNombre.length-2;i++) {
			if(caracterresNombre[i]== ' ' || caracterresNombre[i]=='.' || caracterresNombre[i]==',') {
				caracterresNombre[i]=Character.toUpperCase(caracterresNombre[i+1]);
			}
		}
		return new String(caracterresNombre);
	}
	
	
	//getProfesorFicticio()
	
	public static Profesor getProfesorFicticio(String correo) {	
		if(correo==null)
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
		if(correo=="")
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		if(!correo.matches(ER_CORREO))
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		//Profesor prof = new Profesor(nombre,correo);
	
		return new Profesor(correo,correo);
	}
	
	
	/*
	 * HashCode e Equals
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
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
		Profesor other = (Profesor) obj;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		return true;
	}
	
	/*
	 * toString
	 */
	
	@Override
	public String toString() {
		return "Profesor: [nombre =" + nombre + ", correo =" + correo + ", telefono =" + telefono + " ]";
	}
	
	
	
	
	
	
	
}
