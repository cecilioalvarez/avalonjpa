package es.dominiojpatest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import es.avalon.dominiojpa.Libro;

public class LibroJPATest2 {
	static EntityManagerFactory emf;
	EntityManager em;
	
	
	@BeforeClass
	public static void setUpClass() {
		//se supone que afecta a la clse entera , solo antes de hacer todos los test
		//general para toda app
		Persistence.generateSchema("UnidadBiblioteca",null);
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		
		
	}
	
	@Before
	public void setUp(){
		//antes de todos los test cada vez
		//existe manager por cada op que hacemos con un grupo de ent
		em=emf.createEntityManager();
	}
	
	@Test
	public void testListaTodosLibros () {
		
		//consutla JPA querty Langguage
		TypedQuery<Libro> consulta= em.createQuery("select l from Libro l", Libro.class);
		List<Libro> lista= consulta.getResultList();
		//assertNotNull(lista);
		//assertTrue(lista.size()>=4);
		assertThat(lista.size(),greaterThanOrEqualTo(4));
	}
	public void testListaLibros () {
		
		//consutla JPA querty Langguage
		TypedQuery<Libro> consulta= em.createQuery("select l from Libro l", Libro.class);
		//me devuelve la lista de libros
		List<Libro> lista= consulta.getResultList();
		//gerno en memorioa el libro ab como tenemos hashcode e equals
		//instancio libro y compruebo que en la lista viene
		Libro libro= new Libro ("AB");
		//assertTrue(lista.contains(libro));
		assertThat(lista,hasItems(libro));
		
		

	}
	public void testBuscarLibrosISBN () {
		
		Libro libro=em.find(Libro.class, "1AB");
		assertEquals("1AB",libro.getIsbn());
		assertEquals("java",libro.getIsbn());
		assertEquals("cecilio",libro.getIsbn());
		assertEquals(10,libro.getIsbn());
		assertEquals("java",libro.getIsbn());
		
}
	public void testBuscarLibrosAutor() {
	
		TypedQuery<Libro> consulta =em.createQuery("select l from Libro l where l.autor=:autor",Libro.class);
		consulta.setParameter("autor","cecilio");
		List<Libro> lista=consulta.getResultList();
		//assertNotNull(lista);
		//assertTrue(lista.size()>=2);
		
		assertThat(lista.size(),greaterThanOrEqualTo(2));
}
	public void testBuscarLibrosCategoria() {
	
		TypedQuery<Libro> consulta =em.createQuery("select l from Libro l where l.categoria=:categoria",Libro.class);
		consulta.setParameter("categoria","web");
		List<Libro> lista=consulta.getResultList();
		//assertEquals(2,lista.size());
		assertThat(lista.size(),greaterThanOrEqualTo(2));
		
}
	public void testBorrarLibrosISBN () {
		
		//busca entidad
		Libro libro=em.find(Libro.class, "1AB");
		//asegura queexiste
		assertNotNull(libro);
		//comienza transaccion (accion contra bd)
		EntityTransaction t= em.getTransaction();
		//ejecuta transaccoion
		t.begin();
		//lo elimina
		em.remove(libro);
		//intenta incontrarlo
		Libro libro2=em.find(Libro.class, "1AB");
		//asegura que no esta
		assertNull(libro2);
		//echa para atras dejando todo como estaba antes
		t.rollback();
}
	
	
	
	@After
	public void Close() {
		//emf=null;
		em=null;
	}
}
