package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.texto.Vista;

public enum FactoriaVista {

	TEXTO {

		public IVista crear() {

			return new Vista();
		}
	},

	GRAFICA {

		public IVista crear() {

			return new VistaGrafica();
		}
	};

	public abstract IVista crear();
	
}