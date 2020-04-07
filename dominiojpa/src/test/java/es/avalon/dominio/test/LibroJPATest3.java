package es.avalon.dominio.test;

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

import es.avalon.dominio.Categoria;
import es.avalon.dominio.Libro;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LibroJPATest3 {

	static EntityManagerFactory emf;
	EntityManager em;

	//
	@BeforeClass
	public static void setUpClass() {
		Persistence.generateSchema("UnidadBiblioteca", null);
		emf = Persistence.createEntityManagerFactory("UnidadBiblioteca");
	}

	// se ejecuta antes de ejecutar cada test
	@Before
	public void setUp() {
		em = emf.createEntityManager();
	}

	@Test
	public void testInicialLibros() {

		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l", Libro.class);
		List<Libro> lista = consulta.getResultList();

		assertThat(lista.size(), greaterThanOrEqualTo(4));

	}

	@Test
	public void test_Existe_Libro_ISBN_1AB() {

		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l", Libro.class);
		List<Libro> lista = consulta.getResultList();
		Libro libro = new Libro("1AB");

		assertThat(lista,hasItems(libro));

	}
@Test
public void test_Borrar_Libro_PorISBN_1AB() {
	Libro libro=em.find(Libro.class, "1AB");
	assertNotNull(libro);
	EntityTransaction t=em.getTransaction();
	t.begin();
	
	em.remove(libro);
	Libro libro2=em.find(Libro.class, "1AB");
	assertNull(libro2);
	t.rollback();
}
	public void testBuscarLibrosISBN() {
		Libro libro = em.find(Libro.class, "1AB");

		assertEquals("1AB", libro.getIsbn());
		assertEquals("java", libro.getTitulo());
		assertEquals("cecilio", libro.getAutor());
		assertEquals(10, libro.getPrecio());
		assertEquals("java", libro.getCategoria());

	}

	@Test
	public void test_buscar_Libro_Por_Autor_Cecilio() {

		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l where l.autor=:autor", Libro.class);
		consulta.setParameter("autor", "cecilio");
		List<Libro> lista = consulta.getResultList();

		assertThat(lista.size(), greaterThanOrEqualTo(2));

	}
	@Test
	public void test_buscar_Libro_Por_Autor_y_titulo() {

		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l where l.autor=:autor and l.titulo=:titulo", Libro.class);
		consulta.setParameter("autor", "cecilio");
		consulta.setParameter("titulo", "java");
		List<Libro> lista = consulta.getResultList();

		assertThat(lista.size(), greaterThanOrEqualTo(1));
		assertThat(lista,hasItem(new Libro("1AB")));

	}

	@Test
	public void test_Buscar_Libro_Por_categoria() {

		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l where l.categoria.nombre=:nombre", Libro.class);
		consulta.setParameter("nombre", "Java");
		List<Libro> lista = consulta.getResultList();

		assertThat(lista.size(),greaterThanOrEqualTo(2));
		assertThat(lista,hasItems(new Libro("1AB"),new Libro("2AC")));

	}
	
	@Test
	public void testBuscarLibrosISBNconCategoriaJava() {
		Libro libro = em.find(Libro.class, "1AB");

		assertEquals("1AB", libro.getIsbn());
		assertEquals("Java", libro.getTitulo());
		assertEquals("cecilio", libro.getAutor());
		assertEquals(10, libro.getPrecio());
		
		Categoria c=libro.getCategoria();
		assertEquals("java",c.getNombre());
		assertEquals("libros de java",c.getDescripcion());

	}

	@After
	public void close() {
		// emf.close();
		em.close();
		// emf=null;
		em = null;
	}

}
