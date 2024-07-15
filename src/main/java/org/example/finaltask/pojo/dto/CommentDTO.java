package org.example.finaltask.pojo.dto;


//DTO 用于实体化前端收到的对象
public class CommentDTO {
    private Long contentId;
    private String content;
    private Long UserId;

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }
}
