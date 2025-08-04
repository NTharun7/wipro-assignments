package com.wipro.springassignments.jdbc;

import java.util.List;

public interface PersonDAO {
    List<Person> getAll();             // Get all records
    Person getById(int id);            // Get one record by ID
    void insert(Person person);        // Insert a new person
    void update(Person person);        // Update an existing person
    void delete(int id);               // Delete person by ID
}
