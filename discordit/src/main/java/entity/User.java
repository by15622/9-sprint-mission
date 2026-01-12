package entity;

import java.util.UUID;

public class User {
    private UUID id;
    private String displayname;
    private String email;
    private String phoneNumber;
    private Long createdAt;
    private Long updatedAt;

    public User(String displayname, String email, String phoneNumber) {
        this.id = UUID.randomUUID();
        this.displayname = displayname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        long now = System.currentTimeMillis();
        this.createdAt = now;
        this.updatedAt = now;
    }
    public void update(String displayname, String email) {
        this.displayname = displayname;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getDisplayname() {
        return displayname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public String toString() {
        return "User [이름=" + displayname + ", 이메일=" + email + ", 전화번호=" + phoneNumber + "]";
    }

    //TODO 메서드 추가
}