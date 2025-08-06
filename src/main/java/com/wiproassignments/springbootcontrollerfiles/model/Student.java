package com.wiproassignments.springbootcontrollerfiles.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Student {

    @NotBlank(message = "{name.notblank}")
    @Size(min = 2, max = 30, message = "{name.size}")
    private String name;

    @NotBlank(message = "{email.notblank}")
    @Email(message = "{email.valid}")
    private String email;

    @NotBlank(message = "{course.notblank}")
    private String course;

    @NotBlank(message = "{sex.notblank}")
    private String sex;

    @NotBlank(message = "{section.notblank}")
    private String section;

    @NotBlank(message = "{country.notblank}")
    private String country;

    // --- Getters and Setters ---

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
