package es.avalon.dominio.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import es.avalon.dominio.Libro;

public class LibroJPATest {

	
	
	static EntityManagerFactory emf;
	EntityManager em;
	
	@Test
	public void testLibroExiste() {
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
	
		em=emf.createEntityManager();
		Libro libro=em.find(Libro.class, "2AC");
		
		assertEquals("2AC",libro.getIsbn());
		assertEquals("Java Web",libro.getTitulo());
		assertEquals("cecilio",libro.getAutor());
		assertEquals(15,libro.getPrecio());
		//assertEquals("programacion",libro.getCategoria());
	}
	@Test
	public void testListaLibros() {
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em=emf.createEntityManager();
		//consulta JPA Query Languaje
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l",Libro.class);
		
		List<Libro> lista=consulta.getResultList();
		assertTrue(lista.size()>2);
	
	}
	@After
	public void close() {
		
		em.close();
		
		em = null;
	}
	@AfterClass
	public static void closemf() {
		 emf.close();

		 emf=null;
	
	}

}
