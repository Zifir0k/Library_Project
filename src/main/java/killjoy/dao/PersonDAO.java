package killjoy.dao;

import killjoy.models.Book;
import killjoy.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index(){
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id){
        Session session =sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }

    @Transactional
    public void save(Person person){
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson){
        Session session = sessionFactory.getCurrentSession();
        Person toUpdatePerson = session.get(Person.class,id);

        toUpdatePerson.setName(updatePerson.getName());
        toUpdatePerson.setSecondName(updatePerson.getSecondName());
        toUpdatePerson.setSurname(updatePerson.getSurname());
        toUpdatePerson.setAge(updatePerson.getAge());
    }

    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }

    //Пока ещё не тестил. Всё остальное работает
    @Transactional
    public List<Book> getBook(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select b from Book b where personId = ?").getResultList();
    }
    //jdbcTemplate.query("SELECT * FROM book WHERE personid = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
}
