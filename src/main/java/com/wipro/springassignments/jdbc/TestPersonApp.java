package com.wipro.springassignments.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestPersonApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        PersonDAO dao = context.getBean("personDAO", PersonDAO.class);

        // Insert a new person
        Person newPerson = new Person(1, 25, "Tharun", "Kumar");
     
        Person p2 = new Person(2, 30, "Rahul", "Sharma");
        dao.insert(p2);

        Person p3 = new Person(3, 22, "Priya", "Verma");
        dao.insert(p3);

        Person p4 = new Person(4, 27, "Amit", "Kumar");
        dao.insert(p4);

        dao.insert(newPerson);
        System.out.println("Inserted: " + newPerson);

        // Update the person
        newPerson.setAge(26);
        newPerson.setLastName("K.");
        dao.update(newPerson);
        System.out.println("Updated: " + newPerson);

        // Get by ID
        Person p = dao.getById(1);
        System.out.println("Fetched by ID: " + p);

        // Get all
        List<Person> people = dao.getAll();
        System.out.println("All people:");
        for (Person person : people) {
            System.out.println(person);
        }

        // Delete
        dao.delete(1);
        System.out.println("Deleted person with ID 1");
    }
}
