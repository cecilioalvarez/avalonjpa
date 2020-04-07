package es.avalon.dominio.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import es.avalon.dominio.Libro;

public class LibrosTest {

	@Test
	public void testDosIgualDos() {
		//confirma que 2=2
		assertEquals(2,2);
	}

	@Test
	public void testElLibroNoesNulo() {
		
		Libro libro=new Libro("10","java","pedro",20);
		assertNotNull(libro);
	}
	@Test
	public void testElLibroTieneInformacionCorrecta() {
		
		Libro libro=new Libro("10","java","pedro",20);
		assertEquals("10",libro.getIsbn());
		assertEquals("java",libro.getTitulo());
		assertEquals("pedro",libro.getAutor());
		assertEquals(20,libro.getPrecio());
		assertEquals(null,libro.getCategoria());
	}

}
