package com.vmysore.springbase.test;

import javax.persistence.*;
//console connection jdbc:h2:mem:testdb
@Entity
@Table(name="TEST")
public class Test {
    @Column(name="name")
    private String name;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
