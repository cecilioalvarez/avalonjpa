package es.dominiojpa.test;

import static org.junit.Assert.*;

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
		
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em=emf.createEntityManager();
		Libro libro=em.find(Libro.class, "24");
		assertEquals("24",libro.getIsbn());
		assertEquals("Web",libro.getTitulo());
		assertEquals("Juan",libro.getAutor());
		assertEquals(36,libro.getPrecio());
		assertEquals("Web",libro.getCategoria());
	}
	
	@Test
	public void testListaLibros() {
		
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em=emf.createEntityManager();
		// consulta JPA Query Lnaguage
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l",Libro.class);

		List<Libro> lista=consulta.getResultList();
		assertEquals(2,lista.size());
	}
	
	

}
