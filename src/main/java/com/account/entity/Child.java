package com.account.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ffdeng2
 */
@Entity
@Table(name = "child")
public class Child implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "class_code", insertable = false, updatable = false)
    private String classCode;

    @OneToOne
    @JoinColumn(name = "class_code", referencedColumnName = "class_code")
    private SchoolClass schoolClass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Child() {
    }

    public Child(Long id, String name, String classCode, SchoolClass schoolClass) {
        this.id = id;
        this.name = name;
        this.classCode = classCode;
        this.schoolClass = schoolClass;
    }
}
