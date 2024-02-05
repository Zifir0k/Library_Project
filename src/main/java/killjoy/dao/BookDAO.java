package killjoy.dao;

import killjoy.models.Book;
import killjoy.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    public BookDAO(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    public List<Book> index(){
        return jdbcTemplate.query("SELECT* FROM book",new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT* FROM book WHERE id=?",new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save (Book book){
        jdbcTemplate.update("INSERT INTO book (personid, name, author,ear) VALUES (?,?,?,?)",
                book.getPersonId(),
                book.getName(),
                book.getAuthor(),
                book.getEar());
    }

    public void update(int id,Book updateBook){
        jdbcTemplate.update("UPDATE book SET personid = ? WHERE  id =?", updateBook.getId(),id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE  id = ?",id);
    }

}
