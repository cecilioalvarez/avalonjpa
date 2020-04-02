package es.dominiojpatest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

import java.util.*;
import es.avalon.dominiojpa.Libro;

public class LibroJPATest {
	EntityManagerFactory emf;
	EntityManager em;
	
	@Test
	public void test () {
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em=emf.createEntityManager();
		Libro libro=em.find(Libro.class,"5");
		assertEquals("5", libro.getIsbn());
		assertEquals("net", libro.getTitulo());
		assertEquals("cecilio", libro.getAutor());
		assertEquals(24,libro.getPrecio());
		assertEquals("web", libro.getCategoria());
	}
	
	public void testListaLibros () {
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em=emf.createEntityManager();
		//consutla JPA querty Langguage
		TypedQuery<Libro> consulta= em.createQuery("select l from Libro l", Libro.class);
		List<Libro> lista= consulta.getResultList();
		assertEquals(2, lista.size());
	
	}

}
