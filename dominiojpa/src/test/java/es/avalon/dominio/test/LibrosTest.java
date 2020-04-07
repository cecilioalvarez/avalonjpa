package es.avalon.dominio.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import es.avalon.dominio.Libro;

public class LibrosTest {

	@Test
	public void testDosIgualaDos() {
	
		// confirmar que 2=2
		assertEquals(2,2);
		
	}
	
	@Test
	public void testElLibroNoesNulo() {
	
		Libro libro= new Libro("10","java","pedro",20);
		assertNotNull(libro);
		
	}
	
	
	@Test
	public void testElLibroTieneInformacionCorrecta() {
	
		Libro libro= new Libro("11","java","pedro",20);
		
		assertEquals("11", libro.getIsbn());
		assertEquals("java", libro.getTitulo());
		assertEquals("pedro", libro.getAutor());
		assertEquals(20, libro.getPrecio());
		//assertEquals("programacion", libro.getCategoria());
		
	}
	

}
