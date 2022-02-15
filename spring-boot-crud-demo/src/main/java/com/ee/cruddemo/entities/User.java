package com.ee.cruddemo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "present_addr_id")
    private Address presentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_addr_id")
    private Address permanentAddress;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Todo> todos;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="user_role",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private Set<Role> roles = new HashSet<>();

    public void addTodo(Todo todo) {
        if(this.todos == null) {
            this.todos = new HashSet<>();
        }
        todo.setUser(this);
        this.todos.add(todo);
    }

    public void addRole(Role role) {
        if(this.roles == null) {
            this.roles = new HashSet<>();
        }
        if(role.getUsers() == null) {
            role.setUsers(new HashSet<>());
        }
        role.getUsers().add(this);
        this.roles.add(role);
    }
}
