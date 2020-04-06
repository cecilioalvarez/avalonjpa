package es.avalon.dominiojpa;

import es.avalon.dominiojpa.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Libros")
	public class Libro {
		
		@Id
		 private String isbn;
		 private String titulo;
		 private String autor;
		 private int precio;
		 @ManyToOne
		 @JoinColumn(name="categoria")
		 private Categoria categoria;
		 
		 
		 public String getIsbn() {
			return isbn;
		}
		 
		
		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public Libro() {
			super();
		}


		public String getAutor() {
			return autor;
		}
		public void setAutor(String autor) {
			this.autor = autor;
		}
		public int getPrecio() {
			return precio;
		}
		public void setPrecio(int precio) {
			this.precio = precio;
		}
		public Categoria getCategoria() {
			return categoria;
		}
		public void seCategoria (Categoria categoria) {
			this.categoria=categoria;
		}
		
//		public String getCategoria() {
//			return categoria;
//		}
//		public void setCategoria(String categoria) {
//			this.categoria = categoria;
//		}
		 
		public Libro(String isbn, String titulo, String autor, int precio, String categoria) {
			super();
			this.isbn = isbn;
			this.titulo = titulo;
			this.autor = autor;
			this.precio = precio;
			this.categoria = categoria;
		}

		public Libro(String isbn) {
			super();
			this.isbn = isbn;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Libro other = (Libro) obj;
			if (isbn == null) {
				if (other.isbn != null)
					return false;
			} else if (!isbn.equals(other.isbn))
				return false;
			return true;
		}
		}



