package org.lingxi.youxi.web.model;

public class MemberRegDTO {
    private Integer id;

    private String name;

    private String level;

    private String role;

    private Integer gangId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Integer getGangId() {
        return gangId;
    }

    public void setGangId(Integer gangId) {
        this.gangId = gangId;
    }
}