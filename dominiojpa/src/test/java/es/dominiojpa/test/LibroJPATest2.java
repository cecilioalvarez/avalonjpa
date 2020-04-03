package es.dominiojpa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import es.avalon.dominiojpa.Libro;

public class LibroJPATest2 {
	
	EntityManagerFactory emf;
	EntityManager em;
	
	@Test
	public void testInicialLibros() {
		
		Persistence.generateSchema("UnidadBiblioteca", null);
		
		emf = Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em = emf.createEntityManager();
		//Consulta JPA Query Language
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l", Libro.class);
		
		List<Libro> lista = consulta.getResultList();
		assertEquals(4, lista.size());
		
	}
	@Test
	public void testExisteLibroTituloJava() {
		
		Persistence.generateSchema("UnidadBiblioteca", null);
		
		emf = Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em = emf.createEntityManager();
		//Consulta JPA Query Language
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l", Libro.class);
		
		//Me devuelve la lista de libros
		List<Libro> lista = consulta.getResultList();
		
		/* genero en memoria el libro 1AB
		 * como tenemos sobrecargado el equals y el hashcode
		 * instancio el libro y compruebo
		 * que en la lista viene con el metodo contains
		 */
		Libro libro = new Libro("1AB");
		assertTrue(lista.contains(libro));
		
	}
	
	@Test
	public void testBuscarLibroPorISBN() {
		
		Persistence.generateSchema("UnidadBiblioteca", null);
		
		emf = Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em = emf.createEntityManager();
		
		Libro libro = em.find(Libro.class, "1AB");
		
		assertEquals("1AB", libro.getIsbn());
		assertEquals("Java", libro.getTitulo());
		assertEquals("cecilio", libro.getAutor());
		assertEquals(10, libro.getPrecio());
		assertEquals("java", libro.getCategoria());
		
		
	}

}
