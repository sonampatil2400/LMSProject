package com.lms.bean;

public class User {


    int userId;
    String userName;
    String userType;
    int issuedBookId;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getIssuedBookId() {
        return issuedBookId;
    }

    public void setIssuedBookId(int issuedBookId) {
        this.issuedBookId = issuedBookId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +
                ", issuedBookId=" + issuedBookId +
                '}';
    }
}
