package com.account.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ffdeng2
 */
@Entity
@Table(name = "school")
public class School implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String schoolName;

    private String schoolCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public School() {
    }

    public School(Long id, String schoolName, String schoolCode) {
        this.id = id;
        this.schoolName = schoolName;
        this.schoolCode = schoolCode;
    }
}
