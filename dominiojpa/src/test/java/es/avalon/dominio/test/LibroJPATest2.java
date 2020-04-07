package es.avalon.dominio.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.avalon.dominio.Categoria;
import es.avalon.dominio.Libro;

public class LibroJPATest2 {

	static EntityManagerFactory emf;
	EntityManager em;
	
	@BeforeClass
	public static void setUpClass() {
		Persistence.generateSchema("UnidadBiblioteca",null);
		// es general para toda la aplicacion
	}

	@Before
	public void setUp() {
		
		// entity manager existe por cada operacion
		// que hacemos con un grupo de entidades
		emf = Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em = emf.createEntityManager();
		
	}
	
	
	@Test
	public void test_Lista_Todos_los_Libros() {

		// consulta JPA Query Lnaguage
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l", Libro.class);

		List<Libro> lista = consulta.getResultList();
		// assertEquals(4, lista.size());
		assertThat(lista.size(),greaterThanOrEqualTo(4));

	}

	@Test
	public void test_Existe_Libro_Isbn_1AB() {

		// consulta JPA Query Lnaguage
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l", Libro.class);

		// me devuelve la lista de libros
		List<Libro> lista = consulta.getResultList();
		// genero en memoria el libro 1AB
		// como tenemos sobrecarado el equals y el hashcode
		// instancio el libro y comprueba que en la lista viene con el metodo contains
		Libro libro = new Libro("1AB");
		
		assertThat(lista,hasItems(libro));

	}

	@Test
	public void test_Buscar_Con_Find_Libro_por_Isbn_1AB() {

		Libro libro = em.find(Libro.class, "1AB");

		assertEquals("1AB", libro.getIsbn());
		assertEquals("Java", libro.getTitulo());
		assertEquals("cecilio", libro.getAutor());
		assertEquals(10, libro.getPrecio());
		//assertEquals("java", libro.getCategoria());

	}
	
	@Test
	public void test_Buscar_Libro_por_Isbn_1AB_con_Categoria_Java() {
		
		Libro libro = em.find(Libro.class, "1AB");
		
		assertEquals("1AB", libro.getIsbn());
		assertEquals("Java", libro.getTitulo());
		assertEquals("cecilio", libro.getAutor());
		assertEquals(10, libro.getPrecio());;
		
		Categoria c=libro.getCategoria();
		assertEquals("java",c.getNombre());
		assertEquals("libros de java", c.getDescripcion());
	}
	
	/*
	@Test
	public void test_Buscar_Libro_por_Categoria() {
		
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l where l.categoria.nombre=:nombre",Libro.class);
		consulta.setParameter("nombre", "java");
		List<Libro> lista=consulta.getResultList();
		
		assertThat(lista.size(),greaterThanOrEqualTo(2));
		assertThat(lista,hasItems(new Libro("1AB"), new Libro("2AC")));

	}
		
	 */
	
	@Test
	public void test_Borrar_Libro_por_Isbn() {
		
		// busca la entidad
		Libro libro = em.find(Libro.class, "1AB");
		// otra linea hace un assert y comprueba qeu existe
		assertNotNull(libro);
		// esta genera una transacion
		EntityTransaction t=em.getTransaction();
		// ejecuta una transaccion
		t.begin();
		// elimina el libro
		em.remove(libro);
		// esta intenta encontrarle
		Libro libro2=em.find(Libro.class, "1AB");
		//comprueba que ya no existe
		assertNull(libro2);
		// exha la transaccion para atras de tal forma que los datos quedan como estaban en la base de datos
		t.rollback();
		
	}
	@Test
	public void test__Buscar_Libro_por_Autor_cecilio() {
		
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l where l.autor=:autor",Libro.class);
		consulta.setParameter("autor", "cecilio");
		
		List<Libro> lista=consulta.getResultList();
		
		assertEquals(2,lista.size());
		
	}
	
	/*
	@Test
	public void test__Buscar_Libro_por_Categoria_web() {
				
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l where l.categoria=:categoria",Libro.class);
		consulta.setParameter("categoria", "web");
		
		List<Libro> lista=consulta.getResultList();
		
		assertEquals(2,lista.size());
		
	}
	 */
	
	@Test
	public void test__Buscar_Libro_por_Titulo_y_Autor() {
		
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l where l.autor=:autor and l.titulo=:titulo",Libro.class);
		consulta.setParameter("autor", "cecilio");
		consulta.setParameter("titulo", "Java");
		
		List<Libro> lista=consulta.getResultList();
		
		assertThat(lista.size(),greaterThanOrEqualTo(1));
		assertThat(lista,hasItem(new Libro("1AB")));
		
	}
	
	@After
	public void close() {
		
		//emf.close();
		em.close();
		//emf=null;
		em=null;
	}
	

}
