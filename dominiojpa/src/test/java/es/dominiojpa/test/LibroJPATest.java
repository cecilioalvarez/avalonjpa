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
		Libro libro=em.find(Libro.class, "1");
		assertEquals("1AB",libro.getIsbn());
		assertEquals("Java",libro.getTitulo());
		assertEquals("cecilio",libro.getAutor());
		assertEquals(10,libro.getPrecio());
		assertEquals("java",libro.getCategoria());	
		
	}
	@Test
	public void testListaLibros() {

		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em=emf.createEntityManager();
		//Consulta JPA Query Language
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l",Libro.class);
		
		List<Libro> lista=consulta.getResultList();
		//Confirmo que 3 =tamaño de la lista de libros (que es 3 tambien)
		assertEquals(3,lista.size());
		
	}

}
