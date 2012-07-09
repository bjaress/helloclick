package demo;

import java.util.Date;

public class Comment {
    private String user;
    private String comment;
    private Date date;

    public Comment(String user, String comment) {
        this.user = user;
        this.comment = comment;
        this.date = new Date();
    }

    public String getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }
}
