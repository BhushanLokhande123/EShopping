package com.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.Author;
import com.bookstore.entity.AuthorId;
import com.bookstore.repository.AuthorRepository;

@Service
@Transactional
public class BookstoreServiceImpl implements BookstoreService {

	// another way of D.I : w/o @AutoWired : using constr based D.I --but no
	// annotation required
	// Supported from Spring 5.x onwards
	private final AuthorRepository authorRepository; //declare final immutable dependency

// implicit parameterized constr based D.I implicitly...
	public BookstoreServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public Author addAuthorWithBooks(Author author) {
		return authorRepository.save(author);// cascade on save
	}

	@Override
	@Transactional(readOnly = true)
	public List<Author> fetchAuthorByName(String name) {
		List<Author> authors = authorRepository.fetchByName(name);
		return authors;
	}

	@Override
	@Transactional
	public String removeBookOfAuthor(String authorName, int age) {
		Author author = authorRepository.fetchAuthorDetailsWithBooks(new AuthorId(authorName, age));
		author.removeBook(author.getBooks().get(0)); //removing 1st book
		return "Removed 1st book";
	}

	@Override
	@Transactional
	public String removeAuthor(String authorName, int age) {
		authorRepository.deleteById(new AuthorId(authorName, age));   //cascade on delete  
		//author entry and its associated books also get deleted...
		return "Deleted author details";
	}

}
