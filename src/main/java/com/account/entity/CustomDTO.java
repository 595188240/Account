package com.account.entity;

/**
 * @author ffdeng2
 * @date 2022-2-12 11:45
 */
public class CustomDTO {

    private Long id;

    private String name;

    public CustomDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
