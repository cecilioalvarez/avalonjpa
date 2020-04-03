package dominiojpa;

import static org.junit.Assert.*;

import org.junit.Test;

import es.avalon.dominiojpa.Libro;
public class LibrosTest2 {

	@Test
	public void testDosIGualDos() {
		
		//cpnfirmar a =b
		assertEquals(2,2);
}
	
	@Test
	public void testElLibroNoEsNulo() {
		Libro libro= new Libro ("10", "java","pedro", 20, "programacion");
		assertNotNull(libro);
	}
	@Test
	public void testElLibroTieneInfoCorrecta() {
		Libro libro= new Libro ("10", "java","pedro", 20, "programacion");
		assertEquals("10",libro.getIsbn());
		assertEquals("java",libro.getTitulo());
		assertEquals("pedro",libro.getAutor());
		assertEquals(20,libro.getPrecio());
		assertEquals("programacian",libro.getCategoria());
	}
}
