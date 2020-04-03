package es.dominiojpa.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.avalon.dominiojpa.Libro;

public class LibroJPATest3 {

	static EntityManagerFactory emf;
	EntityManager em;
	//
	@BeforeClass
	public  static void setUpClass() {
		Persistence.generateSchema("UnidadBiblioteca", null);
		emf=Persistence.createEntityManagerFactory("UnidadBiblioteca");
	}
	//se ejecuta antes de ejecutar cada test
	@Before
	public void setUp() {
	em=emf.createEntityManager();
	}
	
	
	
	@Test
	public void testListaLibros() {
	
		 TypedQuery<Libro> consulta=em.createQuery("select l from Libro l",Libro.class);
		 List<Libro> lista =consulta.getResultList();
		 
		 assertEquals(4,lista.size());
	
	}

	@Test
	public void testExisteLibroJava() {

		 TypedQuery<Libro> consulta=em.createQuery("select l from Libro l",Libro.class);
		 List<Libro> lista =consulta.getResultList();
	
		 Libro libro=new Libro("1AB");
	
		 assertTrue(lista.contains(libro));
	
	}
	@Test
	public void BuscarPorAutor() {

		 TypedQuery<Libro> consulta=em.createQuery("select l from Libro l where l.autor=:autor",Libro.class);
		 consulta.setParameter("autor","cecilio");
		 List<Libro> lista =consulta.getResultList();
	
		assertEquals(2,lista.size());
	
	}
	@After
	public void close() {
		//emf.close();
		em.close();
		//emf=null;
		em=null;
	}
	
}
