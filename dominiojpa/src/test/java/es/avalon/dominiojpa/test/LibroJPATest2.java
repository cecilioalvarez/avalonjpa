package es.avalon.dominiojpa.test;

import static org.junit.Assert.*;

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

import es.avalon.dominiojpa.Libro;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LibroJPATest2 {

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
	public void test_Lista_Todos_Los_Libros() {
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l", Libro.class);

		List<Libro> lista = consulta.getResultList();

		assertThat(lista.size(),greaterThanOrEqualTo(4));
	}
	@Test
	public void test_Existe_Libro_ISBN_1AB() {
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l", Libro.class);

		List<Libro> lista = consulta.getResultList();

		Libro libro = new Libro("1AB");
		
		assertThat(lista,hasItems(libro));
	}
	
	public void test_Buscar_Con_Find_Libro_Por_ISBN() {
		Libro libro= em.find(Libro.class, "1AB");
		
		assertEquals("1AB", libro.getIsbn());
		assertEquals("Java", libro.getTitulo());
		assertEquals("cecilio", libro.getAutor());
		assertEquals(10, libro.getPrecio());
		assertEquals("java", libro.getCategoria());
		
		
	}
	
	public void test_Buscar_Libro_Por_Autor_Cecilio() {
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l where l.autor=autor", Libro.class);

		consulta.setParameter("autor", "cecilio");
		List<Libro> lista = consulta.getResultList();

		assertThat(lista.size(),greaterThanOrEqualTo(2));
		
		
	}
	
	public void test_Buscar_Libro_Por_Categoria_Web() {
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l where l.categoria=categoria", Libro.class);

		consulta.setParameter("categoria", "web");
		List<Libro> lista = consulta.getResultList();

		assertThat(lista.size(),greaterThanOrEqualTo(2));
	}
	
	@Test
	public void test_Borrar_Libro_Por_ISBN_1AB() {
		Libro libro= em.find(Libro.class, "1AB");
		assertNotNull(libro);
		EntityTransaction t=em.getTransaction();
		t.begin();
		em.remove(libro);
		
		Libro libro2=em.find(Libro.class, "1AB");
		assertNull(libro2);
		t.rollback();
		
		
	}
	
	@After
	public void close() {
		
		//emf.close();
		em.close();
		//emf=null;
		em=null;
		
	}
}
