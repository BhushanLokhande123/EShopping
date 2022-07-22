package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString(exclude = "author")  //extra carefull...
//you should not have any relationship keys in toString...BOLD ITALIC o/w stack overflow & recursion..
@NoArgsConstructor

//owning side  ---> owning side corresponds to the side that contains the corresponding foreign key. 
public class Book
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100)
	private String title;
	
	@Column(length = 100)
	private String isbn;
	
	@ManyToOne(fetch = FetchType.LAZY)  //else mapping exception...many to one default fetch type
	//is eager so if i fire query  i will get both author and book details...so hence set fetch type to lazy...
	//but that can make prone to lazyintializationexception... 

	
	//@JoinColumns ---> specifies mapping of composite foreign keys...
	//The @JoinColumn annotation helps us specify the column we'll use for 
	//joining an entity association or element collection.
	//Optional BUT reco annotation to specify comp. FKs below down...
	@JoinColumns({ @JoinColumn(name = "name",referencedColumnName = "name"),
			@JoinColumn(name = "age",referencedColumnName = "age") })
	private Author author;  // i can get author by accessing book class.
	
	public Book(String title, String isbn) {
		super();
		this.title = title;
		this.isbn = isbn;
	}
	
}
