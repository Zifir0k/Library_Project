package killjoy.controllers;

import killjoy.dao.BookDAO;
import killjoy.models.Book;
import killjoy.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;

    public BookController(BookDAO bookDAO) {this.bookDAO = bookDAO;}

    @GetMapping()
    public String Books(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
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

    /*TODO:1.Сделать так чтоб книги можно было удалить
    *      2.Сделать так чтоб после удаления возращалась страница со всеми книгами
    *      3.Добавить отображение всех книг пользователя на странице people/show в виде списка
    */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
