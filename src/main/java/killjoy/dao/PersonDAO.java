package killjoy.dao;

import killjoy.models.Book;
import killjoy.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT* FROM person",new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT* FROM person WHERE id=?",new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person (name,secondname,surname,age) VALUES (?,?,?,?)",
                person.getName(),
                person.getSecondName(),
                person.getSurname(),
                person.getAge());
    }

    public void update(int id, Person updatePerson){
        jdbcTemplate.update("UPDATE person SET name=?, secondname=?, surname=?,age=? WHERE id=?",
                updatePerson.getName(),
                updatePerson.getSecondName(),
                updatePerson.getSurname(),
                updatePerson.getAge(),
                id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public List<Book> getBook(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE personid = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
