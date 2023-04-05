package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author john = new Author();
        john.setFirstName("John");
        john.setLastName("Corner");
        Book ddd =new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("121-AA-2345");
        Author johnSave = authorRepository.save(john);
        Book dddSave = bookRepository.save(ddd);

        Author tanjemul =new Author();
        tanjemul.setFirstName("Tanjemul");
        tanjemul.setLastName("Islam");
        Book ds =new Book();
        ds.setTitle("Design History");
        ds.setIsbn("1234567-BB-90");
        Author tanjemulSave = authorRepository.save(tanjemul);
        Book dsSave = bookRepository.save(ds);

        johnSave.getBooks().add(dddSave);
        tanjemulSave.getBooks().add(dsSave);
        dddSave.getAuthors().add(johnSave);
        dsSave.getAuthors().add(tanjemulSave);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("My Own Publisher");
        publisher.setAddress("123 Main Street");
        Publisher publisherSave = publisherRepository.save(publisher);

        dddSave.setPublisher(publisherSave);
        dsSave.setPublisher(publisherSave);

        System.out.println("Author count: "+authorRepository.count());
        System.out.println("Book count: "+ bookRepository.count());

        System.out.println("Book count: "+ publisherRepository.count());

    }
}
