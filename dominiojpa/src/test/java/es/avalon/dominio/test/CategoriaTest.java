package es.avalon.dominio.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
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


public class CategoriaTest {

	
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
		public void testInicialCategoria() {

			TypedQuery<Categoria> consulta = em.createQuery("select c from Categoria c", Categoria.class);
			List<Categoria> lista = consulta.getResultList();

			assertThat(lista.size(), greaterThanOrEqualTo(2));

		}
		@Test
		public void test_Insertar_categoria_net() {

			Categoria c=new Categoria("net","libros de .net");
			em.persist(c);
			
			Categoria nueva=em.find(Categoria.class, "net");
			assertNotNull(nueva);
			assertEquals(c,nueva);
	

		}
		
		@Test
		public void test_Existe_Categoria() {

			TypedQuery<Categoria> consulta = em.createQuery("select c from Categoria c", Categoria.class);
			List<Categoria> lista = consulta.getResultList();
			Categoria categoria = new Categoria("java");

			assertThat(lista,hasItems(categoria));

		}

		@Test
		public void test_Borrar_Nombre() {
			Categoria categoria=em.find(Categoria.class, "java");		
			em.remove(categoria);
			Categoria categoria2=em.find(Categoria.class, "java");
			assertNull(categoria2);
		
		}
		
		@Test
		public void test_Buscar_Libros_Por_Categoria() {

			
//			TypedQuery<Categoria> consulta = em.createQuery("select c from Categoria c where c.nombre=:nombre", Categoria.class);
//			consulta.setParameter("nombre", "web");
//			Categoria c = consulta.getSingleResult();

			Categoria c=em.find(Categoria.class, "java");
			assertThat(c.getLibros().size(),greaterThanOrEqualTo(2));

		}
//		@After
//		public void close() {
//			emf.close();
//			em.close();
//			emf=null;
//			em = null;
//		}

}
