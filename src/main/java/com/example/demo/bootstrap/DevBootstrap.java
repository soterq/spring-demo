package com.example.demo.bootstrap;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Publisher;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.PublisherRepository;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    public void initData() {
        Publisher publisher = new Publisher();
        publisher.setName("celic");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Cimadanschii");
        Book book1 = new Book("Eric Library", "1", publisher);
        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);


        authorRepository.save(eric);
        bookRepository.save(book1);

        Author chris = new Author("Chris", "Poul");
        Book pointGuard = new Book("Point Guard", "112", publisher);
        chris.getBooks().add(pointGuard);
        authorRepository.save(chris);
        bookRepository.save(pointGuard);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
