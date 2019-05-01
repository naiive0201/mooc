package io.learning.hs.mooc.writing;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Hyeonsoo
 * @version 1.0
 * @description Do nothing
 */
@Data
@Entity
@Table(name = "MY_WRITING")
public class Writing {

    private @Id @GeneratedValue Long id;

    private String subject;
    private String content;
    private Status status;


    Writing() {
    }

    public Writing(String subject, String content, Status status) {
        this.subject = subject;
        this.content = content;
        this.status = status;
    }
}
