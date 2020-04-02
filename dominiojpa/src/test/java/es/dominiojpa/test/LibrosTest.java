package es.dominiojpa.test;

import static org.junit.Assert.*;

import org.junit.Test;

import es.avalon.dominiojpa.Libro;

public class LibrosTest {

	@Test
	public void testDosIgualaDos() {

		//Confirmar que 2=2
		assertEquals(2,2);		
	}
	
	@Test
	public void testElLibroNoesNulo() {

		Libro libro=new Libro("10","java","pedro",20,"programacion");
		
		assertNotNull(libro);
	}
	
	@Test
	public void testElLibroTieneInformacionCorrecta() {

		Libro libro=new Libro("10","java","pedro",20,"programacion");
		
		assertEquals("10", libro.getIsbn());
		assertEquals("java", libro.getTitulo());
		assertEquals("pedro", libro.getAutor());
		assertEquals(20, libro.getPrecio());
		assertEquals("programacion", libro.getCategoria());
	}
}
