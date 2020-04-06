package es.dominiojpa.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertEquals;

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
		em= emf.createEntityManager();
		Libro libro=em.find(Libro.class, "1AB");
		assertEquals("1AB",libro.getIsbn());
		assertEquals("Java",libro.getTitulo());
		assertEquals("cecilio",libro.getAutor());
		assertEquals(10,libro.getPrecio());
		//assertEquals("java",libro.getCategoria());
		
		
		
	}
	
	
	@Test
	public void testListaLibros() {
		
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em= emf.createEntityManager();
		//consulta JPA Query Language
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l",Libro.class);
		
		List<Libro> lista= consulta.getResultList();
		assertThat(lista.size(),greaterThanOrEqualTo(4));
		
		
	}

}
