package com.example.jakarta.data;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.StreamSupport;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    private BookRepository repository;

    @GET
    public List<Book> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .toList();
    }

    @POST
    public Book create(Book book) {
        return repository.save(book);
    }

    @GET
    @Path("/{id}")
    public Book get(@PathParam("id") Long id) {
        return repository.findById(id).orElse(null);
    }

    @GET
    @Path("/isbn/{isbn}")
    public Book getByISBN(@PathParam("isbn") String isbn) {
        return repository.searchBookByISBN(isbn);
    }

    @GET
    @Path("/search")
    public List<Book> search(@QueryParam("title") String title, @QueryParam("author") String author) {

        if (title != null && !title.isEmpty()) {
            title = "%" + title + "%";
            return repository.searchBooksContainsTitle(title);
        } else if (author != null && !author.isEmpty()) {
            author = "%" + author + "%";
            return repository.searchBooksContainsAuthor(author);
        }

        return List.of();
    }
}