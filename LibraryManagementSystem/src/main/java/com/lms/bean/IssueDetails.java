package com.lms.bean;

import java.sql.Date;

public class IssueDetails {


    int issueId;
    int bookId;
    int userId;
    Date issueDate;
    Date returnDate;
    int period;

    public IssueDetails() {
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "IssueDetails{" +
                "issueId=" + issueId +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                ", period=" + period +
                '}';
    }
}
