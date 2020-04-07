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

public class CategoriaJPATest {

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
	public void test_lista_todas_las_Categorias() {

		TypedQuery<Categoria> consulta=em.createQuery("select c from Categoria c",Categoria.class);
		
		List<Categoria> lista=consulta.getResultList();
	
		assertThat(lista.size(),greaterThanOrEqualTo(3));

	}
	
	@Test
	public void test_insertar_categoria_net() {
		
		Categoria categoria=new Categoria("net","libros de .net");
		em.persist(categoria);
		
		Categoria nueva=em.find(Categoria.class, "net");
		assertNotNull(nueva);
		assertEquals(categoria, nueva);

			
	}
	
	@Test
	public void test_Borrar_Libro_Por_Categoria_java() {
		
		Categoria categoria=em.find(Categoria.class,"java");
		
		em.remove(categoria);
		Categoria categoria2=em.find(Categoria.class, "1AB");
		assertNull(categoria2);

	}
	
	@Test
	public void test_buscar_categoria_con_libros() {
		
//		TypedQuery<Categoria> consulta=em.createQuery("select c from Categoria c where c.nombre=:nombre",Categoria.class);
//		consulta.setParameter("nombre", "java");
//		Categoria categoria=consulta.getSingleResult();
//		assertThat(categoria.getLibros().size(),greaterThanOrEqualTo(3));
		
		Categoria c=em.find(Categoria.class, "java");
		assertThat(c.getLibros().size(),greaterThanOrEqualTo(3));


	}
	
	
	@After
	public void close() {
		//emf.close();
		em.close();
		
		//emf=null;
		em=null;
	}

}
