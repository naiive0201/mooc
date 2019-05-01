package io.learning.hs.mooc;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Hyeonsoo
 * @version 1.0
 * @description Do nothing
 */
@Data
@Entity
public class Writer {

    private @Id @GeneratedValue Long id;
    private String firstname;
    private String lastname;
    private String role;

    Writer() {

    }

    public Writer(String firstname, String lastname, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }

    public String getName() {
        return this.firstname + " " + this.lastname;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstname = parts[0];
        this.lastname = parts[1];
    }
}
