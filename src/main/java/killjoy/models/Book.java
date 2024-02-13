package killjoy.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class Book {

    public Book() {
    }

    private int id;
    private int personId;
    @NotEmpty
    @Max(value = 100)
    private String name;
    @NotEmpty
    @Max(value = 100)
    private String author;
    @NotEmpty
    @Min(value = 0,message = "Число слишком маленькое")
    @Max(value = 2014,message = "Число слишком большое")
    private int ear;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEar() {
        return ear;
    }

    public void setEar(int ear) {
        this.ear = ear;
    }

    public Book(int id, int personId, String name, String author, int ear) {
        this.personId = personId;
        this.name = name;
        this.author = author;
        this.ear = ear;
    }
}
