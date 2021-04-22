package ru.geekbrains.spring.one.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
@NamedQueries({
        @NamedQuery(name = "withProducts", query = "SELECT c FROM Category c JOIN FETCH c.products WHERE c.title = :title")
})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "title")
    public String title;


    @OneToMany(mappedBy = "category")
    List<Product> products;






}
