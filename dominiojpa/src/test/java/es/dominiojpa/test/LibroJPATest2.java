package es.dominiojpa.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.avalon.dominiojpa.Libro;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LibroJPATest2 {

	static EntityManagerFactory emf;
	EntityManager em;
	
	@BeforeClass
	public static void setUpClass() {
		Persistence.generateSchema("UnidadBiblioteca", null);	
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
	}
	
	@Before
	public void setUp() {		
		em=emf.createEntityManager();
	}
	
	@Test
	public void testInicialLibros() {

		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l",Libro.class);
		
		List<Libro> lista=consulta.getResultList();
	
		assertThat(lista.size(),greaterThanOrEqualTo(4));
		
		
	}

	@Test
	public void test_Existe_Libro_Titulo_Java() {

		//Consulta JPA Query Language
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l",Libro.class);
		
		//Me devuelve la lista de libros
		List<Libro> lista=consulta.getResultList();
		//Genero en memoria el libro 1AB, como tenemos sobrecargado el equals y el hashcode
		//instancia el libro y comprueba que en la lista viene con el metodo contains
		Libro libro=new Libro("1AB");
		assertTrue(lista.contains(libro));		

	}
	
	@Test
	public void test_Buscar_Libro_por_autor_cecilio() {

		//Consulta JPA query language
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l where l.autor=:autor",Libro.class);
		consulta.setParameter("autor", "cecilio");
		
		List<Libro> lista=consulta.getResultList();
		assertTrue(lista.size()>=2);
	}
	
	@Test
	public void test_Buscar_Libro_por_categoria_web() {

		//Consulta JPA query language
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l where l.categoria=:categoria",Libro.class);
		consulta.setParameter("categoria", "web");
		
		List<Libro> lista=consulta.getResultList();
		assertEquals(2,lista.size());
	}
	
	@Test
	public void test_Buscar_Libro_por_Isbn() {
		
		Libro libro=em.find(Libro.class,"1AB");
		assertEquals("1AB",libro.getIsbn());		
		assertEquals("Java",libro.getTitulo());		
		assertEquals("cecilio",libro.getAutor());		
		assertEquals(10,libro.getPrecio());		
		assertEquals("java",libro.getCategoria());		
	}
	
	@After
	public void close() {
		//emf.close();
		em.close();
		
		//emf=null;
		em=null;
	}
}
