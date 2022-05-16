package org.iesalandalus.programacion.reservasaulas.mvc.vista.texto;

public enum Opcion {
	
	INSERTAR_AULA("Insetar aula"){
		public void ejecutar() {
			vista.insertarAula();
		}
	},
	BORRAR_AULA("Borrar aula"){
		public void ejecutar() {
			vista.borrarAula();
		}
	},
	BUSCAR_AULA("Buscar aula"){
		public void ejecutar() {
			vista.buscarAula();
		}
	},
	LISTAR_AULA("Listar aulas"){
		public void ejecutar() {
			vista.listarAulas();
		}
	},
	INSERTAR_PROFESOR("Insetar profesor"){
		public void ejecutar() {
			vista.insertarProfesor();
		}
	},
	BORRAR_PROFESOR("Borrar  profesor"){
		public void ejecutar()  {
			vista.borrarProfesor();
		}
	},
	BUSCAR_PROFESOR("Buscar profesor"){
		public void ejecutar() {
			vista.buscarProfesor();
		}
	},
	LISTAR_PROFESOR("Listar profesores"){
		public void ejecutar() {
			vista.listarProfesores();
		}
	},
	INSERTAR_RESERVA("Insetar reserva"){
		public void ejecutar()  {
			vista.realizarReserva();
		}
	},
	BORRAR_RESERVA("Borrar  reserva") {
		public void ejecutar()  {
			vista.anularReserva();
		}
	},
	LISTAR_RESERVAS("Listar reservas"){
		public void ejecutar() {
			vista.listarReservas();
		}
	},
	LISTAR_RESERVAS_AULA("Listar reservas aula"){
		public void ejecutar() {
			vista.listaReservasAula();
		}
	},
	LISTAR_RESERVAS_PROFESOR("Listar reservas profesor"){
		public void ejecutar() {
			vista.listaReservasProfesor();
		}
	},
	CONSULTAR_DISPONIBILIDAD("Consultar disponibilidad"){
		public void ejecutar() {
			vista.consultarDisponibilidad();
		}
	},
	SALIR("Salir"){
		public void ejecutar() {
			 vista.salir();;
		}
	};
	
	private String mensajeAMostrar;
	private static Vista vista;
	
	private Opcion(String cadena) {
		this.mensajeAMostrar=cadena;
	}

	public String getMensaje() {
		return mensajeAMostrar;
	}
	
	public abstract void ejecutar() ;
	
	protected static void setVista(Vista vista) {
		if(vista==null)
			throw new NullPointerException("ERROR: La vista no pueda ser nula.");
		
		Opcion.vista=vista;
	}
	public static Opcion getOpcionSegunOrdinal(int ordinal) {
		if (!esOrdinalValido(ordinal)) {
			throw new IllegalArgumentException("Ordinal de la opción no válido");
		}
		return values()[ordinal];
	}
	
	public static boolean esOrdinalValido(int ordinal) {
		return (ordinal >= 0 && ordinal <= values().length - 1);
	}
	
	@Override
	public String toString() {
		return String.format("%d.- %s", ordinal(), mensajeAMostrar);
	}
}
