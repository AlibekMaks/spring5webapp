package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepositories;
import guru.springframework.spring5webapp.repositories.BookRepositories;
import guru.springframework.spring5webapp.repositories.PublisherRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final BookRepositories bookRepositories;
    private final AuthorRepositories authorRepositories;
    private final PublisherRepositories publisherRepositories;

    public BootstrapData(BookRepositories bookRepositories, AuthorRepositories authorRepositories,
                         PublisherRepositories publisherRepositories) {
        this.bookRepositories = bookRepositories;
        this.authorRepositories = authorRepositories;
        this.publisherRepositories = publisherRepositories;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("TipoGraph");
        publisher.setCity("Uralsk");
        publisher.setAddressLine1("Eskalieva");
        publisherRepositories.save(publisher);


        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1231231");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublishers(publisher);
        publisher.getBooks().add(ddd);

        authorRepositories.save(eric);
        bookRepositories.save(ddd);
        publisherRepositories.save(publisher);
        System.out.println("number books " + bookRepositories.count());
        System.out.println("publisher's number books " + publisher.getBooks().size());

    }
}
