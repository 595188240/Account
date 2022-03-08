package com.account.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ffdeng2
 */
@Entity
@Table(name = "school_class")
public class SchoolClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name")
    private String className;

    @Column(name = "class_code")
    private String classCode;

    @Column(name = "school_code")
    private String schoolCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public SchoolClass() {
    }

    public SchoolClass(Long id, String className, String classCode, String schoolCode) {
        this.id = id;
        this.className = className;
        this.classCode = classCode;
        this.schoolCode = schoolCode;
    }
}
