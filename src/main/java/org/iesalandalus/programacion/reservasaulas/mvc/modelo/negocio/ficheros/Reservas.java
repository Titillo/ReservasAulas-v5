package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.ficheros;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IReservas;

public class Reservas implements IReservas{
	/*
	 * Atributos
	 */ 
	private static final float MAX_PUNTOS_PROFESOR_MES = 200.f;
	private static final String NOMBRE_FICHERO_RESERVAS="datos/reservas.dat";
	private List<Reserva> coleccionReservas;
	
	/*
	 * Constructores
	 */ 
	
	public Reservas() {
		coleccionReservas = new ArrayList<>();
	}
	
	public Reservas(Reservas reservas) {
		if (reservas == null) 
			throw new NullPointerException("ERROR: No se pueden copiar reservas nulas.");
		
		setReservas(reservas);
	}
	
	@Override
	public void comenzar() {
		leer();
		
	}
	private void leer() {
		File ficheroReserva= new File(NOMBRE_FICHERO_RESERVAS);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroReserva))){
			Reserva reserva = null;
			do {
				reserva= (Reserva) entrada.readObject();
				insertar(reserva);
			}while(reserva!= null);
			
		}catch (ClassNotFoundException e) {
			System.out.println("ERROR: No puedo encontrar la clase que tengo que leer");
		}catch (FileNotFoundException e) {
			System.out.println("ERROR: No se puede abir el fichero de aulas");
		}catch (EOFException e) {
			System.out.println("Fichero leido correctamente");
		}catch (IOException e) {
			System.out.println("ERROR en la Entrada/Salida");
		}catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void terminar() {
		
		escribir();
	}
	
	private void escribir() {
		File ficheroReserva = new File(NOMBRE_FICHERO_RESERVAS);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroReserva))){
			for(Reserva reserva : coleccionReservas) {
				salida.writeObject(reserva);
			}
		}catch (FileNotFoundException e) {
			System.out.println("ERROR: No se puede crear el fichero de aulas");
		}catch (IOException e) {
			System.out.println("ERROR en la Entrada/Salida");
			
		}
	}
	

	/*
	 * setReservas()
	 */
	
	private void setReservas(Reservas reservas) {
		if(reservas == null)
			throw new NullPointerException("ERROR: No se pueden copiar reservas nulas.");
		
		coleccionReservas=new ArrayList<>(reservas.coleccionReservas);
	}
	/*
	 * CopiaProfundaReservas()
	 */
	
	private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {
		List<Reserva> reservaCopia = new ArrayList<>();
		for(Reserva reserva : reservas) {
			reservaCopia.add(new Reserva(reserva));
		}
		return reservaCopia;
	}
	
	/*
	 * getReservas
	 */
	@Override
	public  List<Reserva> getReservas() {
		List<Reserva> copiaReservas = copiaProfundaReservas(coleccionReservas);

		Comparator<Reserva> comparator = (Reserva r1, Reserva r2) -> {
			int resultadoPorNombreAula = r1.getAula().getNombre().compareTo(r2.getAula().getNombre());
			if (resultadoPorNombreAula == 0) {
				int resultadoPorDia = r1.getPermanencia().getDia().compareTo(r2.getPermanencia().getDia());
				if (resultadoPorDia == 0) {
					if (r1.getPermanencia().getPuntos() == 10) {
						return ((PermanenciaPorTramo) r1.getPermanencia()).getTramo()
								.compareTo(((PermanenciaPorTramo) r2.getPermanencia()).getTramo());
					}
					return ((PermanenciaPorHora) r1.getPermanencia()).getHora()
							.compareTo(((PermanenciaPorHora) r2.getPermanencia()).getHora());
				}
				return resultadoPorDia;
			}
			return resultadoPorNombreAula;
		};

		Collections.sort(copiaReservas, comparator);

		return copiaReservas;
	}
	
	
	/*
	 * getNumReservas()
	 */
	@Override
	public int getNumReservas() {
		return coleccionReservas.size();	
	}
	
	/*
	 * Insertar()
	 */
	@Override
	public void insertar (Reserva reservas) throws OperationNotSupportedException {
		if (reservas == null) 
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		

		Reserva reservaGuardada = getReservaAulaDia(reservas.getAula(), reservas.getPermanencia().getDia());
		if (reservaGuardada != null) {

			if (reservaGuardada.getPermanencia() instanceof PermanenciaPorTramo
					&& reservas.getPermanencia() instanceof PermanenciaPorHora) {
				throw new OperationNotSupportedException(
						"ERROR: Ya se ha realizado una reserva de otro tipo de permanencia para este día.");
			}
			if (reservaGuardada.getPermanencia() instanceof PermanenciaPorHora
					&& reservas.getPermanencia() instanceof PermanenciaPorTramo) {
				throw new OperationNotSupportedException(
						"ERROR: Ya se ha realizado una reserva de otro tipo de permanencia para este día.");
			}
		}

		if (!esMesSiguienteOPosterior(reservas)) {
			throw new OperationNotSupportedException(
					"ERROR: Sólo se pueden hacer reservas para el mes que viene o posteriores.");
		}

		if (getPuntosGastadosReserva(reservas) > MAX_PUNTOS_PROFESOR_MES) {
			throw new OperationNotSupportedException(
					"ERROR: Esta reserva excede los puntos máximos por mes para dicho profesor.");
		}
		if (coleccionReservas.contains(reservas)) {
			throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
		}
		else {
			coleccionReservas.add(new Reserva(reservas));
		}
	}
	
	/*
	 * Buscar()
	 */
	@Override
	public Reserva buscar(Reserva reservas) {
		if(reservas==null)
			throw new NullPointerException("ERROR: No se puede buscar un reserva nula.");
		
		int indice = coleccionReservas.indexOf(reservas);
		if(indice == -1) {
			return null;
		}else {
			return new Reserva(coleccionReservas.get(indice));
		}
	}
	
	/*
	 * Borrar()
	 */
	@Override
	public void borrar(Reserva reservas) throws OperationNotSupportedException {
		if (reservas == null) {
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}

		if (!esMesSiguienteOPosterior(reservas)) {
			throw new OperationNotSupportedException(
					"ERROR: SÃ³lo se pueden anular reservas para el mes que viene o posteriores.");
		}

		Reserva reservaEncontrada = buscar(reservas);
		if (reservaEncontrada == null) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna reserva igual.");
		}

		coleccionReservas.remove(reservaEncontrada);
		System.out.println("La reserva " + reservaEncontrada + " se ha borrado con Ã©xito.");
	}
	
	/*
	 * Representar()
	 */
	
	@Override
	public List<String> representar() {
		List<String> representarReservas = new ArrayList<>();
		Iterator<Reserva> it = coleccionReservas.iterator();
		Reserva reserva;
		while(it.hasNext()) {
			reserva=it.next();
			representarReservas.add(new String(reserva.toString()));	
		}
		return representarReservas;
	}

	/*
	 * getPuntosGastadosReserva
	 */
	
	private float getPuntosGastadosReserva(Reserva reserva) {
		float puntosGastados = 0;
		for (Iterator<Reserva> it = getReservasProfesorMes(reserva.getProfesor(), reserva.getPermanencia().getDia())
				.iterator(); it.hasNext();) {
			Reserva reservaProfesor = it.next();
			puntosGastados += reservaProfesor.getPuntos();
		}
		return puntosGastados + reserva.getPuntos();
	}
	
	
	/*
	 * esMesSiguienteOPosterior()
	 */
	
	private boolean esMesSiguienteOPosterior(Reserva reserva) {
		LocalDate diaReserva = reserva.getPermanencia().getDia();
		
		LocalDate dentroDeUnMes = LocalDate.now().plusMonths(1);
	
		LocalDate primerDiaMesSiguiente = LocalDate.of(dentroDeUnMes.getYear(), dentroDeUnMes.getMonth(), 1);
	
		return diaReserva.isAfter(primerDiaMesSiguiente) || diaReserva.equals(primerDiaMesSiguiente);
	}
	
	/*
	 * getReservasProfesorMes()
	 */
	
	private List<Reserva> getReservasProfesorMes(Profesor profesor, LocalDate dia){
		if (profesor == null)
			throw new NullPointerException("No se pueden buscar reservas de un profesor nulo.");

		List<Reserva> reservasMes = new ArrayList<>();
		for (Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {
			Reserva reserva = it.next();
			LocalDate diaReserva = reserva.getPermanencia().getDia();
	
			if (reserva.getProfesor().equals(profesor) && diaReserva.getMonthValue() == dia.getMonthValue()
					&& diaReserva.getYear() == dia.getYear()) {
				
				reservasMes.add(new Reserva(reserva));
			}

		}
		
		return reservasMes;

	}
	
	/*
	 * gestReservaAulaDia()
	 */
	
	private Reserva getReservaAulaDia(Aula aula, LocalDate dia){
		if (dia == null) 
			throw new NullPointerException("No se puede buscar reserva para un día nulo.");

		Reserva reserva = null;
		for (Iterator<Reserva> it = coleccionReservas.iterator(); it.hasNext();) {
			reserva = it.next();
			if (reserva.getAula().equals(aula) && reserva.getPermanencia().getDia().equals(dia)) {
				return reserva;
			}

		}
		return null;

	}
	
	/*
	 * getReservasProfesor()
	 */
	@Override
	public List<Reserva> getReservasProfesor(Profesor profesor) {
		if(profesor==null)
			throw new NullPointerException("ERROR: El profesor no puede ser nulo ");
		
		List<Reserva> reservaProfesores = new ArrayList<>();
		Iterator<Reserva> it = coleccionReservas.iterator();
		while(it.hasNext()) {
			if(it.next().getProfesor().equals(profesor)) {
				reservaProfesores.add(it.next());
			}
		}
		
		return reservaProfesores;
	}
	
	/*
	 * getReservasAula()
	 */
	@Override
	public List<Reserva> getReservasAula(Aula aula) {
		if(aula==null)
			throw new NullPointerException("ERROR: El aula no puede ser nula ");
		
		List<Reserva> reservaAulas = new ArrayList<>();
		Iterator<Reserva> it = coleccionReservas.iterator();
		while(it.hasNext()) {
			if(it.next().getAula().equals(aula)) {
				reservaAulas.add(it.next());
			}
		}
		return reservaAulas;
	}
	
	/*
	 * getReservasPermanencia()
	 */
	@Override
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		if(permanencia==null)
			throw new NullPointerException("ERROR: La permanencia no puede ser nula ");
		
		List<Reserva> reservaPermanencias = new ArrayList<>();
		Iterator<Reserva> it = coleccionReservas.iterator();
		while(it.hasNext()) {;
			if(it.next().getPermanencia().equals(permanencia)) {
				reservaPermanencias.add(it.next());
			}
			
		}
		
		return reservaPermanencias;
	}
	/*
	 * ConsultarDisponibilidad
	 */
	@Override
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		} else if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}
		boolean disponible = true;
		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			Reserva auxiliar = iterador.next();
			if (!esMesSiguienteOPosterior(Reserva.getReservaFicticia(aula, permanencia))) {
				disponible = false;
			} else if (aula.equals(auxiliar.getAula()) && permanencia.getDia().equals(auxiliar.getPermanencia().getDia())) {
				if ((permanencia instanceof PermanenciaPorHora && auxiliar.getPermanencia() instanceof PermanenciaPorTramo)
						|| (permanencia instanceof PermanenciaPorTramo && auxiliar.getPermanencia() instanceof PermanenciaPorHora)) {
					disponible = false;
				} else if (permanencia instanceof PermanenciaPorHora && auxiliar.getPermanencia() instanceof PermanenciaPorHora) {
					if (((PermanenciaPorHora) permanencia).getHora().equals(((PermanenciaPorHora) auxiliar.getPermanencia()).getHora())) {
						disponible = false;
					}
				} else if (permanencia instanceof PermanenciaPorTramo&& auxiliar.getPermanencia() instanceof PermanenciaPorTramo) {
					if (((PermanenciaPorTramo) permanencia).getTramo().equals(((PermanenciaPorTramo) auxiliar.getPermanencia()).getTramo())) {
						disponible = false;
					}
				}
			}
		}
		return disponible;
	}

	
	
}
