package com.test.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demo.entity.Book;
import com.test.demo.entity.Lend;
import com.test.demo.entity.LendStatus;

public interface LendRepository extends JpaRepository<Lend, Long> {
	Optional<Lend> findByBookAndStatus(Book book, LendStatus status);

}
