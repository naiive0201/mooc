package io.learning.hs.mooc.controller;

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
    private String name;
    private String role;

    public Writer() {
    }

    Writer(String name, String role) {
        this.name = name;
        this.role = role;
    }
}
