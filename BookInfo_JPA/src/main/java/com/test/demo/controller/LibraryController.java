package com.test.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.dto.AuthorCreationRequest;
import com.test.demo.dto.BookCreationRequest;
import com.test.demo.dto.BookLendRequest;
import com.test.demo.dto.MemberCreationRequest;
import com.test.demo.entity.Author;
import com.test.demo.entity.Book;
import com.test.demo.entity.Member;
import com.test.demo.service.LibraryService;
import com.test.demo.swagger.SwaggerConfiguration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {SwaggerConfiguration.BOOK_TAG})
@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class LibraryController {
	private final LibraryService libraryService;
	
	@ApiOperation(value="도서 목록 조회", notes="도서 목록을 조회한다")
	@GetMapping("/book")
	public ResponseEntity<?> readBooks(@RequestParam(required = false) String isbn){
		if(isbn == null) {
			return new ResponseEntity<>(libraryService.readBooks(), HttpStatus.OK);
		}
		return new ResponseEntity<>(libraryService.readBook(isbn), HttpStatus.OK);
	}
	
	@ApiOperation(value="도서 정보 조회", notes="도서 정보를 조회한다")
	@GetMapping("/book/{bookId}")
	public ResponseEntity<Book> readBook(@PathVariable Long bookId){
		return new ResponseEntity<>(libraryService.readBook(bookId), HttpStatus.OK);
	}
	
	@ApiOperation(value="도서 정보 등록", notes="도서 정보를 등록한다")
	@PostMapping("/book")
	public ResponseEntity<Book> createBook(@RequestBody BookCreationRequest request){
		return new ResponseEntity<>(libraryService.createBook(request), HttpStatus.OK);
	}
	
	@ApiOperation(value="도서 정보 변경", notes="도서 정보를 변경한다")
	@PatchMapping("/book/{bookId}")
	public ResponseEntity<Book> updateBook(
			@PathVariable("bookId") Long bookId, @RequestBody BookCreationRequest request){
		return new ResponseEntity<>(libraryService.updateBook(bookId, request),HttpStatus.OK);
	}
	
	@ApiOperation(value="도서 정보 삭제", notes="도서 정보를 삭제한다")
	@DeleteMapping("/book/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long bookId){
		libraryService.deleteBook(bookId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value="회원 목록 조회", notes="회원 목록을 조회한다")
	@GetMapping("/member")
	public ResponseEntity<List<Member>> readMembers(){
		return new ResponseEntity<>(libraryService.readMembers(),HttpStatus.OK);
	}
	
	@ApiOperation(value="회원 정보 등록", notes="회원 정보를 등록한다")
	@PostMapping("/member")
	public ResponseEntity<Member> createMember(@RequestBody MemberCreationRequest request){
		return new ResponseEntity<>(libraryService.createMember(request), HttpStatus.OK);
	}
	
	@ApiOperation(value="회원 정보 변경", notes="회원 정보를 변경한다")
	@PatchMapping("/member/{memberId}")
	public ResponseEntity<Member> updateMember(
			@RequestBody MemberCreationRequest request, @PathVariable Long memberId){
		return new ResponseEntity<>(libraryService.updateMember(memberId, request), HttpStatus.OK);
	}
	
	@ApiOperation(value="도서 대여", notes="도서를 대여한다")
	@PostMapping("/book/lend")
	public ResponseEntity<List<String>> lendABook(@RequestBody BookLendRequest requests){
		return new ResponseEntity<>(libraryService.lendABook(requests), HttpStatus.OK);
	}
	
	@ApiOperation(value="저자 목록 조회", notes="저자 목록을 조회한다")
	@GetMapping("/author")
	public ResponseEntity<List<Author>> readAuthors(){
		return new ResponseEntity<>(libraryService.readAuthors(),HttpStatus.OK);
	}
	
	@ApiOperation(value="저자 정보 등록", notes="저자 정보를 등록한다")
	@PostMapping("/author")
	public ResponseEntity<Author> createAuthor(@RequestBody AuthorCreationRequest request){
		return new ResponseEntity<>(libraryService.createAuthor(request), HttpStatus.OK);
	}

}
