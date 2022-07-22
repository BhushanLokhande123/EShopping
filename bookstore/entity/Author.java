package com.bookstore.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "authors")
@Getter
@Setter
@ToString(exclude = "books")
@NoArgsConstructor

	public class Author {
	@EmbeddedId // or @Id also works ! @Generated value annotation never came with @EmbeddedId /composite key annotation...
//@GeneratedValue  never comes with composite pk...as composite keys are not generated they are assigned type... 
	private AuthorId authorId;
	
	@Column(length = 30)
	private String genre;
	
//mappedBy attribute is used to define the referencing side (non-owning side/inverse side) of the relationship.
//if you dont add mappedBy keyword then hibernate will create add two foreign key column on both table and in many to many relation
//it will create two join tables.....
//cascade = CascadeType.ALL --> Defines the set of cascadable operations that are propagated to the associated entity.The value 
	// cascade=ALL is equivalent to cascade={PERSIST, MERGE, REMOVE, REFRESH, DETACH}. 
	
@OneToMany(cascade = CascadeType.ALL, mappedBy = "author" , orphanRemoval = true )
		private List<Book> books = new ArrayList<>();   //i can get books by accessng author class.

        public Author(AuthorId authorId, String genre) {
		super();
		this.authorId = authorId;
		this.genre = genre;
	}
//add helper methods...
	public void addBook(Book book) {
		this.books.add(book);//establishing a link between Author ---> Book (adding book in author)
		book.setAuthor(this);//establishing reverse link between Book ---> Author  //if not done then unidirectional and fk fields will se t to null...
	}

	public void removeBook(Book book) {
		this.books.remove(book);//delinking : Author ---X--- Book
		book.setAuthor(null);//delinking : Book ---X--- Author
		
	}

	public void removeBooks() {
		Iterator<Book> iterator = this.books.iterator();

		while (iterator.hasNext()) {
			Book book = iterator.next();

			book.setAuthor(null);
			iterator.remove();
		}
	}

}
