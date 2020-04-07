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
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
		// es general para toda la aplicacion
	}
	@Before
	public void setUp() {
		// entity manager existe por cada operacion
		//que hacemos con un grupo de entidades

		em= emf.createEntityManager();	
	}
	
	@Test
	public void test_Lista_Todas_Las_Categorias() {
		
		
		TypedQuery<Categoria> consulta=em.createQuery("select c from Categoria c",Categoria.class);
		
		List<Categoria> lista= consulta.getResultList();
		
		// sacar que el listado es correcto
		//no pierdo mucho el tiempo
		
		assertThat(lista.size(),greaterThanOrEqualTo(3));
		
		// esta parte es importante entonces afino mas los test
		
		assertThat(lista,hasItems(new Categoria("java"),new Categoria("web")));
		
	}
	
	@Test
	public void test_insertar_categoria_net() {
		
		
		Categoria c= new Categoria ("net","libros de .net");
		em.persist(c);
		Categoria nueva= em.find(Categoria.class,"net");
		assertNotNull(nueva);
		assertEquals(c, nueva);
		
		
		
	}
	
	@Test
	public void test_borrar_categoria_net() {
		
		
		Categoria c= em.find(Categoria.class,"java");
		em.remove(c);
		Categoria nueva= em.find(Categoria.class,"java");
		assertNull(nueva);
			
	}
	
	@Test
	public void test_buscar_libros_de_categoria() {
		
		
		Categoria c= em.find(Categoria.class,"java");
		assertThat(c.getLibros().size(),greaterThanOrEqualTo(2));
	}
	
	

}
