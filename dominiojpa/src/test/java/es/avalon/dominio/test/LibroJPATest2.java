package es.avalon.dominio.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
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
		Persistence.generateSchema("UnidadBiblioteca", null);	
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
	}
	
	@Before
	public void setUp() {		
		em=emf.createEntityManager();
	}
	
	@Test
	public void test_lista_todos_los_libros() {

		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l",Libro.class);
		
		List<Libro> lista=consulta.getResultList();
	
		assertThat(lista.size(),greaterThanOrEqualTo(4));
		
		
	}

	@Test
	public void test_Existe_Libro_ISBN_1AB() {

		//Consulta JPA Query Language
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l",Libro.class);
		
		//Me devuelve la lista de libros
		List<Libro> lista=consulta.getResultList();
		//Genero en memoria el libro 1AB, como tenemos sobrecargado el equals y el hashcode
		//instancia el libro y comprueba que en la lista viene con el metodo contains
		Libro libro=new Libro("1AB");
		assertThat(lista,hasItems(libro));		

	}
	
	@Test
	public void test_Buscar_Libro_Por_Autor_Cecilio() {

		//Consulta JPA query language
		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l where l.autor=:autor",Libro.class);
		consulta.setParameter("autor", "cecilio");
		
		List<Libro> lista=consulta.getResultList();
		assertThat(lista.size(),greaterThanOrEqualTo(2));
	}
	
	@Test
	public void test_buscar_libro_por_titulo_y_autor() {

		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l where l.autor=:autor and l.titulo=:titulo",Libro.class);
		consulta.setParameter("autor", "cecilio");
		consulta.setParameter("titulo", "Java");
		
		List<Libro> lista=consulta.getResultList();
		assertThat(lista.size(),greaterThanOrEqualTo(1));                   
		assertThat(lista,hasItem(new Libro("1AB")));
	}
	
	@Test
	public void test_Buscar_Libro_Por_Categoria() {

		TypedQuery<Libro> consulta=em.createQuery("select l from Libro l where l.categoria.nombre=:nombre",Libro.class);
		consulta.setParameter("nombre", "java");
		
		List<Libro> lista=consulta.getResultList();
		assertThat(lista.size(),greaterThanOrEqualTo(2));
		assertThat(lista,hasItems(new Libro("1AB"),new Libro("2AC")));
	}
	
	@Test
	public void test_Buscar_Libro_Por_Isbn_1AB_con_categoria_java() {
		
		Libro libro=em.find(Libro.class,"1AB");
		assertEquals("1AB",libro.getIsbn());		
		assertEquals("Java",libro.getTitulo());		
		assertEquals("cecilio",libro.getAutor());		
		assertEquals(10,libro.getPrecio());		


		Categoria c=libro.getCategoria();
		assertEquals("java",c.getNombre());
		assertEquals("libros de java",c.getDescripcion());
		
	}
	
	@Test
	public void test_Borrar_Libro_Por_Isbn_1AB() {
		
		//Busca la entidad
		Libro libro=em.find(Libro.class,"1AB");
		//hace un assert y comprueba que existe
		assertNotNull(libro);
		//Esta genera una transaccion
		EntityTransaction t=em.getTransaction();
		//Ejecuta una transaccion
		t.begin();
		
		//Elimina el libro
		em.remove(libro);
		//Intenta encontrar el libro
		Libro libro2=em.find(Libro.class, "1AB");
		//Comprueba que ya no existe
		assertNull(libro2);
		//echa la transaccion para atras de tal manera que los datos quedaen como estaban en la base de datos
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