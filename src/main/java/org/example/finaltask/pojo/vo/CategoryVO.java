package org.example.finaltask.pojo.vo;

//userVO 封装后端查询的数据 并返回给前端的类 统称为XXXVO类 给后端用
public class CategoryVO {
    private Long id;
    private String name;

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
