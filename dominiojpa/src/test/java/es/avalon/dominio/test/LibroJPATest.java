package es.avalon.dominio.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import es.avalon.dominio.Libro;

public class LibroJPATest {

	EntityManagerFactory emf;
	EntityManager em;
	
	@Test
	public void testLibroExiste() {
		
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em=emf.createEntityManager();
		Libro libro=em.find(Libro.class, "1AB");
		assertEquals("1AB",libro.getIsbn());
		assertEquals("Java",libro.getTitulo());
		assertEquals("cecilio",libro.getAutor());
		assertEquals(10,libro.getPrecio());
		//assertEquals("Web",libro.getCategoria());
	}
	
	@Test
	public void testListaLibros() {
		
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em=emf.createEntityManager();
		// consulta JPA Query Lnaguage
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l",Libro.class);

		List<Libro> lista=consulta.getResultList();
		assertTrue(lista.size()>2);
	}
	
	

}
