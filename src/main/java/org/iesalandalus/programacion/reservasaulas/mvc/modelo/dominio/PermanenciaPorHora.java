package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class PermanenciaPorHora extends Permanencia {
	
	private static final int PUNTOS=3;
	private static final LocalTime HORA_INICIO= LocalTime.of(8, 0);
	private static final LocalTime HORA_FIN= LocalTime.of(22, 0);
	protected static final DateTimeFormatter FORMATO_HORA= DateTimeFormatter.ofPattern("^[0-2][0-9]:[0-5][0-9]$");
	
	private LocalTime hora;
	
	//Constructores
	public PermanenciaPorHora(LocalDate dia, LocalTime hora) 
	{
		super(dia);
		setHora(hora);
		
	}
	
	public PermanenciaPorHora(PermanenciaPorHora permHora) 
	{
		super(permHora);
		if(permHora==null)
			throw new NullPointerException("ERROR");
		
		setHora(permHora.getHora());
		
	}
	

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	@Override
	public int getPuntos() {
		return PUNTOS;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
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
		PermanenciaPorHora other = (PermanenciaPorHora) obj;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PermanenciaPorHora [hora=" + hora + "]";
	}

	
	
	
}
