package com.crudexample.springbootthymeleafdb.model;

import javax.persistence.*;

@Entity
@Table(name = "idea")
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String tag;

//    private LocalDateTime createdOn;
//    private LocalDateTime updatedOn;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person author;

    public Idea() {
    }

    public Idea(String name, String description, String tag, Person author) {
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public String getAuthorName() {
        return author !=null ? author.getFirstName() : "default";
    }
}
