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

import es.avalon.dominiojpa.Categoria;
import es.avalon.dominiojpa.Libro;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CategoriaJPATest {

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
	public void test_Lista_Todas_Las_Categorias() {
		TypedQuery<Categoria> consulta = em.createQuery("select c from Categoria c", Categoria.class);

		List<Categoria> lista = consulta.getResultList();

		assertThat(lista.size(),greaterThanOrEqualTo(3));
	}
	@Test
	public void test_Existe_Categoria_Nombre_Web() {
		TypedQuery<Categoria> consulta = em.createQuery("select c from Categoria c", Categoria.class);

		List<Categoria> lista = consulta.getResultList();

		Categoria categoria = new Categoria("Web");
		
		assertThat(lista,hasItems(categoria));
	}
	
	@Test
	public void test_Borrar_Categoria_Por_Nombre_Web() {
		Categoria categoria= em.find(Categoria.class, "Web");
		assertNotNull(categoria);
		EntityTransaction t=em.getTransaction();
		t.begin();
		em.remove(categoria);
		
		Categoria categoria2=em.find(Categoria.class, "Web");
		assertNull(categoria2);
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
