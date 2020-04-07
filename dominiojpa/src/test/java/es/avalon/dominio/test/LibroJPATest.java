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
		emf = Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em = emf.createEntityManager();
		Libro libro = em.find(Libro.class, "1");

		assertEquals("1", libro.getIsbn());
		assertEquals("java", libro.getTitulo());
		assertEquals("ana", libro.getAutor());
		assertEquals(10, libro.getPrecio());
		assertEquals("programacion", libro.getCategoria());
	}

	@Test
	public void testListaLibros() {
		emf = Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em = emf.createEntityManager();
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l", Libro.class);

		List<Libro> lista = consulta.getResultList();

		assertEquals(5, lista.size());
	}
}
