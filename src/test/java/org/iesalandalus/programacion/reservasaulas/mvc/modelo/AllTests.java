package org.iesalandalus.programacion.reservasaulas.mvc.modelo;


import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.AulaTest;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHoraTest;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.ProfesorTest;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.ReservaTest;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.TramoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ProfesorTest.class, AulaTest.class, TramoTest.class, PermanenciaPorHoraTest.class, ReservaTest.class })
public class AllTests {

}