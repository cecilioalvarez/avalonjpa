package es.dominiojpa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import es.avalon.dominiojpa.Libro;

public class LibroJPATest2 {
	
	static EntityManagerFactory emf;
	EntityManager em;
	
	@BeforeClass
	public static void setUpClass() {
		Persistence.generateSchema("UnidadBiblioteca", null);
		//es general para toda la aplicacion
		emf = Persistence.createEntityManagerFactory("UnidadBiblioteca");
	}
	
	
	@Before
	public void setUp() {
		//existe por cada operacion que hacemos con un grupo de entidades
		em = emf.createEntityManager();
		
	}
	
	@Test
	public void testListaTodosLosLibros() {
		
		//Consulta JPA Query Language
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l", Libro.class);
		
		List<Libro> lista = consulta.getResultList();
		
		//assertEquals(4, lista.size());
		//assertNotNull(lista);
		//assertTrue(lista.size()>=4);
		
		assertThat(lista.size(), greaterThanOrEqualTo(4));
		
	}



	@Test
	public void testExisteLibroTituloJava() {
		
		
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
		
		//assertTrue(lista.contains(libro));
		assertThat(lista,hasItems(libro));
		
	}
	
	@Test
	public void testBuscarLibroPorISBN1AB() {
			
		Libro libro = em.find(Libro.class, "1AB");
		
		assertEquals("1AB", libro.getIsbn());
		assertEquals("Java", libro.getTitulo());
		assertEquals("cecilio", libro.getAutor());
		assertEquals(10, libro.getPrecio());
		assertEquals("java", libro.getCategoria());
		
	}
	
	@Test
	public void testBuscarLibroPorAutorCecilio() {
		
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l where l.autor=:autor", Libro.class);
		consulta.setParameter("autor", "cecilio");
		
		//Me devuelve la lista de libros
		List<Libro> lista = consulta.getResultList();
		
		assertEquals(2, lista.size());
	}
	
	
	@Test
	public void testBorrarLibroPorISBN1AB() {
		
		// busca la entidad
		Libro libro = em.find(Libro.class, "1AB");
		// hace un assert y comprueba que existe
		assertNotNull(libro);
		
		//genera una transaccion
		EntityTransaction t = em.getTransaction();
		//ejecuta la transaccion
		t.begin();
		// elimina el libro
		em.remove(libro);
		
		//intenta encontrar el libro
		Libro libro2=em.find(Libro.class, "1AB");
		
		//comprueba que no existe
		assertNull(libro2);
		
		// echa la transaccion para atrás de forma que los datos queden como
		// estaban en la base de datos
		t.rollback();
		
		
	}
	
	@Test
	public void testBuscarLibroPorCategoriaWeb() {
		
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l where l.categoria=:categoria", Libro.class);
		consulta.setParameter("categoria", "web");
		
		//Me devuelve la lista de libros
		List<Libro> lista = consulta.getResultList();
		
		assertEquals(2, lista.size());
	}
	
	@After
	public void close() {
		//emf.close();
		em.close();
		//emf = null;
		em = null;
		
	}
	

}
