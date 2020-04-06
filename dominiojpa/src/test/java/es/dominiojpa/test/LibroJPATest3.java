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


import es.avalon.dominiojpa.Categoria;
import es.avalon.dominiojpa.Libro;

public class LibroJPATest3 {
	
	static EntityManagerFactory emf;
	EntityManager em;
	
	@BeforeClass
	public static void setUpClass() {
		Persistence.generateSchema("UnidadBiblioteca", null);
		emf = Persistence.createEntityManagerFactory("UnidadBiblioteca");
	}
	
	
	@Before
	public void setUp() {
		em = emf.createEntityManager();
		
	}
	
	@Test
	public void testBusquedaCategoria() {
		
		TypedQuery<Categoria> consulta = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
		
		List<Categoria> lista = consulta.getResultList();
		
		assertThat(lista.size(), greaterThanOrEqualTo(3));
		
	}
	
	@Test
	public void testInsertarCategoriaNet() {
		
		Categoria c = new Categoria("net", "libros de .net");
		
		em.persist(c);
		
		Categoria nueva = em.find(Categoria.class, "net");
		assertNotNull(nueva);
		assertEquals(c,nueva);
	
	}
	
	@Test
	public void testBorrarCategoriaJava() {
		
		Categoria c = em.find(Categoria.class, "java");
		em.remove(c);
		
		Categoria nueva = em.find(Categoria.class, "java");
		assertNull(nueva);

		
		
		//t.rollback();
		
		
	}


	
	
	@After
	public void close() {
		//emf.close();
		em.close();
		//emf = null;
		em = null;
		
	}
	

}
