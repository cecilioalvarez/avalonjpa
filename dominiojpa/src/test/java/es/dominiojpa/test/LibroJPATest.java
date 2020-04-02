package es.dominiojpa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import es.avalon.dominiojpa.Libro;

public class LibroJPATest {
	
	EntityManagerFactory emf;
	EntityManager em;
	

	@Test
	public void testLibroExiste() {
		
		emf = Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em = emf.createEntityManager();
		Libro libro = em.find(Libro.class, "1");
		
		assertEquals("1",libro.getIsbn());
		assertEquals("Java",libro.getTitulo());
		assertEquals("Juanito",libro.getAutor());
		assertEquals(15,libro.getPrecio());
		assertEquals("Programacion",libro.getCategoria());
		
	}
	
	@Test
	public void testListaLibros() {
		
		emf = Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em = emf.createEntityManager();
		
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l", Libro.class);
		
		List<Libro> lista = consulta.getResultList();
		
		assertEquals(7, lista.size());
		
	}

}
