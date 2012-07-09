package demo.page;

import org.apache.click.control.Table;
import org.apache.click.control.Column;
import org.apache.click.control.Form;
import org.apache.click.control.TextField;
import org.apache.click.control.TextArea;
import org.apache.click.control.Submit;
import org.apache.click.Page;
import org.apache.click.ActionResult;
import demo.Data;
import demo.Comment;

/*
 * The one and only page for the demo app.  This is a small pretend
 * commenting system that supports pulling up user profiles by Ajax.
 */

public class Home extends Page {

    private Form commentForm = new Form("commentForm");
    private Form profileForm = new Form("profileForm");
    private Table commentTable = new Table("commentTable");

    public Home() {

        commentForm.add(new TextArea("comment", "Comment", 60, 10, true));
        commentForm.add(new TextField("name", true));
        commentForm.add(new Submit("Make a Comment"));
        addControl(commentForm);
        commentForm.setListener(this, "onCommentSubmit");

        profileForm.add(new TextArea("profile", "Profile", 60, 10, true));
        profileForm.add(new TextField("name", true));
        profileForm.add(new Submit("Set a Profile"));
        addControl(profileForm);
        profileForm.setListener(this, "onProfileSubmit");

        Column commentColumn = new Column("comment");
        commentColumn.setDataClass("comment");
        commentTable.addColumn(commentColumn);
        Column userColumn = new Column("user");
        userColumn.setDataClass("user");
        commentTable.addColumn(userColumn);
        commentTable.addColumn(new Column("date"));
        addControl(commentTable);
    }

    @Override
    public void onRender() {
        commentTable.setRowList(Data.getComments());
    }

    public boolean onCommentSubmit() {
        if (commentForm.isValid()) {
            Data.addComment(new Comment(commentForm.getFieldValue("name"),
                        commentForm.getFieldValue("comment")));
        }
        commentForm.clearValues();
        return true;
    }

    public boolean onProfileSubmit() {
        if (profileForm.isValid()) {
            Data.putProfile(profileForm.getFieldValue("name"),
                        profileForm.getFieldValue("profile"));
        }
        profileForm.clearValues();
        return true;
    }

    public ActionResult fetchProfile() {
        String user = getContext().getRequestParameter("user");
        return new ActionResult(Data.getProfiles().get(user),
                ActionResult.TEXT);
    }


}
