package com.test.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demo.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
