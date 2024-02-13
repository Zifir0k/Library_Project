package killjoy.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty
    @Size(min = 2,max = 100,message = "Имя не может быть пустым")
    private String name;
    @NotEmpty
    @Size(min = 2,max = 100,message = "Фамилия не может быть пустой")
    private String secondName;
    @NotEmpty
    @Size(min = 2,max = 100,message = "Отчество не может быть пустым")
    private String surname;
    @NotEmpty
    @Min(value = 14,message = "Клиент должен быть платеспособным")
    @Max(value = 120,message = "Люди столько не живут АЛЛО!")
    private int age;

    public Person() {
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(int id, String name, String secondName, String surname, int age) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.age = age;
    }
}
