package killjoy.controllers;

import killjoy.dao.BookDAO;
import killjoy.dao.PersonDAO;
import killjoy.models.Book;
import killjoy.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String Books(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));

        Optional<Person> owner = bookDAO.getOwner(id);

        if (owner.isPresent())
            model.addAttribute("owner",owner.get());
        else
            model.addAttribute("person",personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book){
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book")Book book,@PathVariable("id")int id){
        bookDAO.update(id, book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    public String release(@PathVariable("id")int id){
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

    public String assing(@PathVariable("id")int id,@ModelAttribute("person")Person selectPerson){
        bookDAO.assing(id,selectPerson);
        return "redirect:/books/" + id;
    }
}
