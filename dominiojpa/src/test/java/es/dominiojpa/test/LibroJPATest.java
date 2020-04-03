package es.dominiojpa.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import com.avalon.dominiojpa.Libro;

public class LibroJPATest {

	EntityManagerFactory emf;
	EntityManager em;
	
	@Test
	public void testLibroExiste() {
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
	
		em=emf.createEntityManager();
		Libro libro=em.find(Libro.class, "2");
		
		assertEquals("2",libro.getIsbn());
		assertEquals("net",libro.getTitulo());
		assertEquals("cecilio",libro.getAutor());
		assertEquals(20,libro.getPrecio());
		assertEquals("programacion",libro.getCategoria());
	}
	@Test
	public void testListaLibros() {
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em=emf.createEntityManager();
		//consulta JPA Query Languaje
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l",Libro.class);
		
		List<Libro> lista=consulta.getResultList();
		assertEquals(2,lista.size());
	
	}


}
