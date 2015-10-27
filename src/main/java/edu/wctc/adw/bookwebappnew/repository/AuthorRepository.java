package edu.wctc.adw.bookwebappnew.repository;
import edu.wctc.adw.bookwebappnew.entity.Author;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jlombardo
 */
public interface AuthorRepository extends JpaRepository<Author, Integer>, Serializable {
    
    
  @Query("SELECT a FROM Author a JOIN FETCH a.bookSet WHERE a.authorID = (:id)")
  public Author findByIdAndFetchBooksEagerly(@Param("id") Integer id);
}
