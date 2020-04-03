package es.dominiojpa.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import com.avalon.dominiojpa.Libro;

public class LibroJPATest3 {

	EntityManagerFactory emf;
	EntityManager em;
	
	@Test
	public void testListaLibros() {
		Persistence.generateSchema("UnidadBiblioteca", null);
    	emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
    	
		em=emf.createEntityManager();
		 TypedQuery<Libro> consulta=em.createQuery("select l from Libro l",Libro.class);
		 List<Libro> lista =consulta.getResultList();
	
	
		assertEquals(4,lista.size());
	
	}

	@Test
	public void testExisteLibroJava() {
		Persistence.generateSchema("UnidadBiblioteca", null);
    	emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
    	
		em=emf.createEntityManager();
		 TypedQuery<Libro> consulta=em.createQuery("select l from Libro l",Libro.class);
		 List<Libro> lista =consulta.getResultList();
	
		 Libro libro=new Libro("1A");
	
		assertTrue(lista.contains(libro));
	
	}
	
}
