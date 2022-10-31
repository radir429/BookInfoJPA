package com.test.demo.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.test.demo.dto.AuthorCreationRequest;
import com.test.demo.dto.BookCreationRequest;
import com.test.demo.dto.BookLendRequest;
import com.test.demo.dto.MemberCreationRequest;
import com.test.demo.entity.Author;
import com.test.demo.entity.Book;
import com.test.demo.entity.Lend;
import com.test.demo.entity.LendStatus;
import com.test.demo.entity.Member;
import com.test.demo.entity.MemberStatus;
import com.test.demo.repository.AuthorRepository;
import com.test.demo.repository.BookRepository;
import com.test.demo.repository.LendRepository;
import com.test.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibraryService {
	
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final LendRepository lendRepository;
	private final MemberRepository memberRepository;
	
	public Book readBook(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		if(book.isPresent()) { 
			return book.get();
		} throw new EntityNotFoundException( "ID값에 해당하는 도서가 없습니다" );		 
	}
	
	public Book readBook(String isbn) {
		Optional<Book> book = bookRepository.findByIsbn(isbn);
		if(book.isPresent()) {
			return book.get(); 
		} throw new EntityNotFoundException("ISBN값에 해당하는 도서가 없습니다");
		 
	}
	
	public List<Book> readBooks(){
		return bookRepository.findAll();
	}
	
	public Book createBook(BookCreationRequest request) {
		Optional<Author> author = authorRepository.findById((request.getAuthorId()));
		if(author.isEmpty()) {
			throw new EntityNotFoundException("존재하지 않는 작가입니다.");
		}
		Book book = new Book();
		BeanUtils.copyProperties(request, book);
		book.setAuthor(author.get());
		
		return bookRepository.save(book);
	}
	
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);		
	}
	
	public Member createMember(MemberCreationRequest request) {
		Member member = new Member();
		BeanUtils.copyProperties(request, member);
		member.setStatus(MemberStatus.ACTIVE);
		
		return memberRepository.save(member);
	}
	
	public Member updateMember(Long id, MemberCreationRequest request) {
		Optional<Member> optionalMember = memberRepository.findById(id);
		if(optionalMember.isEmpty()) {
			throw new EntityNotFoundException("회원정보가 없습니다");
		}
		Member member = optionalMember.get();
		member.setLastName(request.getLastName());
		member.setFirstName(request.getFirstName());
		
		return memberRepository.save(member);
	}
	
	public Author createAuthor(AuthorCreationRequest request) {
		Author author = new Author();
		BeanUtils.copyProperties(request, author);
		
		return authorRepository.save(author);
	}
	
	public List<String> lendABook(BookLendRequest request){
		Optional<Member> memberForId = memberRepository.findById(request.getMemberId());
		if(memberForId.isEmpty()) {
			throw new EntityNotFoundException("회원정보가 없습니다");
		}
		Member member = memberForId.get();
		if(member.getStatus() != MemberStatus.ACTIVE) {
			throw new RuntimeException("회원이 대출가능한 상태가 아닙니다");
		}

		List<String> bookApprovedToBorrow = new ArrayList<>();
		request.getBookIds().forEach(bookId ->{
			Optional<Book> bookForId = bookRepository.findById(bookId);
			if(bookForId.isEmpty()) {
				throw new EntityNotFoundException("해당하는 도서가 없습니다");
			}
			Optional<Lend> borrowedBook = lendRepository.findByBookAndStatus(bookForId.get(), LendStatus.BORROWED);
			if(borrowedBook.isEmpty()) {
				bookApprovedToBorrow.add(bookForId.get().getName());
				Lend lend = Lend	.builder()
						.member(memberForId.get())
						.book(bookForId.get())
						.status(LendStatus.BORROWED)
						.startOn(Instant.now())
						.dueOn(Instant.now().plus(30, ChronoUnit.DAYS))
						.build();
				
				lendRepository.save(lend);				
			}
		});
		
		return bookApprovedToBorrow;
	}
	
	public List<Author> readAuthors() {
		return authorRepository.findAll();
	}
	
	public Book updateBook(Long bookId, BookCreationRequest request) {
		Optional<Author> author = authorRepository.findById(request.getAuthorId());
		if(author.isEmpty()) {
			throw new EntityNotFoundException("해당 작가가 없습니다");
		}
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if(optionalBook.isEmpty()) {
			throw new EntityNotFoundException("해당 책이 없습니다");
		}
		Book book = optionalBook.get();
		book.setIsbn(request.getIsbn());
		book.setName(request.getName());
		book.setAuthor(author.get());
		
		return bookRepository.save(book);
	}
	
	public List<Member> readMembers() {
		return memberRepository.findAll();
	}
	
	

}
