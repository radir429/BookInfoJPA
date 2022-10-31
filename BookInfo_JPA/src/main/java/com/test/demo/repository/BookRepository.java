package com.test.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demo.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	Optional<Book> findByIsbn(String isbn);

}
