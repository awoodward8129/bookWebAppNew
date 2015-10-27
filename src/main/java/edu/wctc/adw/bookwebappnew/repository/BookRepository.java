package edu.wctc.adw.bookwebappnew.repository;

import edu.wctc.adw.bookwebappnew.entity.Book;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jlombardo
 */
public interface BookRepository extends JpaRepository<Book, Integer>, Serializable {
    
}
