package es.avalon.dominio.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.avalon.dominio.Categoria;
import es.avalon.dominio.Libro;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoriaJPATest {

	static EntityManagerFactory emf;
	EntityManager em;
	
	@BeforeClass
	public static void setUpClass() {
		Persistence.generateSchema("UnidadBiblioteca", null);
	}
	
	@Before
	public void setUp() {
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		em=emf.createEntityManager();
	}
	
	@Test
	public void test_Lista_Todas_las_Categorias() {
		TypedQuery<Categoria> consulta=em.createQuery("select c from Categoria c",Categoria.class);
		
		List<Categoria> lista=consulta.getResultList();
		assertThat(lista.size(),greaterThanOrEqualTo(3));
	}
	
	@Test
	public void test_Borrar_Categoria_por_Nombre() {
		
		Categoria cat=em.find(Categoria.class, "java");
		assertNotNull(cat);
		em.remove(cat);
		Categoria cat2=em.find(Categoria.class, "java");
		assertNull(cat2);
	}
	
	@Test
	public void test_Insertar_Categoria_net() {
		
		Categoria cat= new Categoria("net","libros de .net");
		em.persist(cat);
		Categoria cat2=em.find(Categoria.class, "net");
		assertNotNull(cat2);
		
	}
	@Test
	public void test_Buscar_Categoria_con_sus_Libros() {
		
		TypedQuery<Categoria> consulta=em.createQuery("select c from Categoria c where c.nombre=:nombre",Categoria.class);
		consulta.setParameter("nombre", "java");
		Categoria cat=consulta.getSingleResult();
		
		assertThat(cat.getLibros().size(),greaterThanOrEqualTo(2));
	}
	
	
	@After
	public void close() {
		
		//emf.close();
		em.close();
		//emf=null;
		em=null;
		emf.close();
		emf=null;
	}
}
