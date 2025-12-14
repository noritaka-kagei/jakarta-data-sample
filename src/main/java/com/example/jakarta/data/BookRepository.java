package com.example.jakarta.data;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Find
    Book searchBookByISBN(String isbn);

    @Query("select Book from Book where title like ?1")
    List<Book> searchBooksContainsTitle(String pattern);

    @Query("where author like :pattern")
    List<Book> searchBooksContainsAuthor(String pattern);
}
