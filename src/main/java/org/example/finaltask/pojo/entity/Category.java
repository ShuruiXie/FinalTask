package org.example.finaltask.pojo.entity;

//entity为实体类，每一个类对应数据库里的一张表
public class Category {
    private Long id;
    private String name;
    private int type;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
