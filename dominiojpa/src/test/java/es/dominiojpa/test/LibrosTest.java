package es.dominiojpa.test;

import static org.junit.Assert.*;

import org.junit.Test;

import es.avalon.dominiojpa.Libro;

public class LibrosTest {

	@Test
	public void testDosIgualaDos() {
		
		// confirmar que 2=2
		assertEquals(2,2);
	}
	
	@Test
	public void testElLibroNoesNulo() {
		Libro libro=new Libro("10","Java","Pedro",22,"Programacion");
		
		assertNotNull(libro);
	}
	
	@Test
	public void testElLibroTieneInformacionCorrecta() {
		Libro libro=new Libro("10","Java","Pedro",22,"Programacion");
		
		assertEquals("10", libro.getIsbn());
		assertEquals("Java", libro.getTitulo());
		assertEquals("Pedro", libro.getAutor());
		assertEquals(22, libro.getPrecio());
		assertEquals("Programacion", libro.getCategoria());
	}

}
