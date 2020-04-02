package es.avalon.main;



import javax.persistence.*;
import es.avalon.dominiojpa.*;
public class Principal2 {

	public static void main(String[] args) {
		
		EntityManagerFactory factoria= Persistence.createEntityManagerFactory("UnidadBiblioteca");
		EntityManager em= factoria.createEntityManager();
		
		Libro libro= em.find(Libro.class, "120");
		System.out.println(libro.getIsbn());
		System.out.println(libro.getAutor());
		System.out.println(libro.getPrecio());
	
	
	}

}
