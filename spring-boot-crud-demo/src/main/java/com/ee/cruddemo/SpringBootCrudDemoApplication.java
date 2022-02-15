package com.ee.cruddemo;

import com.ee.cruddemo.entities.Address;
import com.ee.cruddemo.entities.Role;
import com.ee.cruddemo.entities.Todo;
import com.ee.cruddemo.entities.User;
import com.ee.cruddemo.repositories.RoleRepository;
import com.ee.cruddemo.repositories.TodoRepository;
import com.ee.cruddemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringBootCrudDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudDemoApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        //final Set<Todo> todos = userRepository.findById(7L).get().getTodos();
        final Set<Todo> todos = userRepository.findByIdWithTodos(7L).getTodos();
        //final List<Todo> todos = todoRepository.findByUserId(7L);
        System.out.println(todos);
    }

    private void insertSampleData() {
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);


        User user1 = new User();
        user1.setEmail("siva@gmail.com");
        user1.setPassword("siva123");
        user1.setUsername("siva2");

        Address addr1 = new Address();
        addr1.setAddrLine1("KPHB");
        addr1.setCity("Hyd");
        addr1.setState("TS");
        user1.setPermanentAddress(addr1);

        Address addr2 = new Address();
        addr2.setAddrLine1("IndraNagar");
        addr2.setCity("Bangalore");
        addr2.setState("KS");
        user1.setPresentAddress(addr2);

        Todo todo1 = new Todo();
        todo1.setContent("one");
        //todo1.setUser(user1);

        Todo todo2 = new Todo();
        todo2.setContent("Two");
        //todo2.setUser(user1);

        //user1.setTodos(Set.of(todo1, todo2));
        user1.addTodo(todo1);
        user1.addTodo(todo2);

        user1.addRole(adminRole);
        user1.addRole(userRole);
        userRepository.save(user1);
    }
}
