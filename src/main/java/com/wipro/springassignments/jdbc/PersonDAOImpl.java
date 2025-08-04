package com.wipro.springassignments.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class PersonDAOImpl implements PersonDAO {

    private JdbcTemplate jdbcTemplate;

    // Setter-based dependency injection
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // SELECT * FROM person
    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonRowMapper());
    }

    // SELECT * FROM person WHERE id = ?
    @Override
    public Person getById(int id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM person WHERE id = ?",
            new PersonRowMapper(),
            id
        );
    }

    // INSERT INTO person VALUES (?, ?, ?, ?)
    @Override
    public void insert(Person person) {
        jdbcTemplate.update(
            "INSERT INTO person (id, age, firstName, lastName) VALUES (?, ?, ?, ?)",
            person.getId(),
            person.getAge(),
            person.getFirstName(),
            person.getLastName()
        );
    }

    // UPDATE person SET age=?, firstName=?, lastName=? WHERE id=?
    @Override
    public void update(Person person) {
        jdbcTemplate.update(
            "UPDATE person SET age = ?, firstName = ?, lastName = ? WHERE id = ?",
            person.getAge(),
            person.getFirstName(),
            person.getLastName(),
            person.getId()
        );
    }

    // DELETE FROM person WHERE id = ?
    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }

    // Inner class for mapping ResultSet to Person object
    private static class PersonRowMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person p = new Person();
            p.setId(rs.getInt("id"));
            p.setAge(rs.getInt("age"));
            p.setFirstName(rs.getString("firstName"));
            p.setLastName(rs.getString("lastName"));
            return p;
        }
    }
}
