package com.bookstore.service;

import java.util.List;

import com.bookstore.entity.Author;

public interface BookstoreService {

	Author addAuthorWithBooks(Author author);

	List<Author> fetchAuthorByName(String name);

	String removeBookOfAuthor(String authorName, int age);

	String removeAuthor(String authorName, int age);

}