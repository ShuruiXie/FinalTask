package org.example.finaltask.pojo.dto;

public class UserUpdateDTO {

    private String nickname;
    private String imgUrl;
    private String email;
    private Long id;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserUpdateDTO{" +
                "nickname='" + nickname + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
