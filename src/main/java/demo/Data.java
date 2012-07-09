package demo;

import demo.Comment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.click.Context;

/*
 * Dummy data for the demo app.  A real app would have a database or
 * something.  This just caches things in the session.
 */

public class Data {

    private static Context getContext() {
        return Context.getThreadLocalContext();
    }

    public static void addComment(Comment comment) {
        List<Comment> comments = getComments();
        comments.add(comment);
        getContext().getSession().setAttribute("comments", comments);
    }

    public static List<Comment> getComments() {
        //totally bogus caching of data in session
        //we wouldn't do this in a real app
        List<Comment> comments = ((List<Comment>)
                getContext().getSession()
                .getAttribute("comments"));
        if (null == comments) {
            comments = new ArrayList<Comment>();
            comments.add(new Comment("Alice",
                        "Here I am, you lucky people!"));
            comments.add(new Comment("Bob",
                        "Who can I find on happily my?"));
            comments.add(new Comment("Carl",
                        "Please see the rules on commenting."));
            comments.add(new Comment("Marcel",
                "For a long time I used to go to bed " +
                "early. Sometimes, when I had put out my " +
                "candle, my eyes would close so quickly " +
                "that I had not even time to say \"I'm " +
                "going to sleep.\" And half an hour later " +
                "the thought that it was time to go to " +
                "sleep would awaken me; I would try to " +
                "put away the book which, I imagined, " +
                "was still in my hands, and to blow out " +
                "the light; I had been thinking all the " +
                "time, while I was asleep, of what I had " +
                "just been reading, but my thoughts had " +
                "run into a channel of their own, until " +
                "I myself seemed actually to have become " +
                "the subject of my book: a church, a " +
                "quartet, the rivalry between François " +
                "I and Charles V. This impression would " +
                "persist for some moments after I was " +
                "awake; it did not disturb my mind, but " +
                "it lay like scales upon my eyes and " +
                "prevented them from registering the " +
                "fact that the candle was no longer " +
                "burning. Then it would begin to seem " +
                "unintelligible, as the thoughts of a " +
                "former existence must be to a " +
                "reincarnate spirit; the subject of my " +
                "book would separate itself from me, " +
                "leaving me free to choose whether I " +
                "would form part of it or no; and at the " +
                "same time my sight would return and I " +
                "would be astonished to find myself in a " +
                "state of darkness, pleasant and restful " +
                "enough for the eyes, and even more, " +
                "perhaps, for my mind, to which it " +
                "appeared incomprehensible, without a " +
                "cause, a matter dark indeed."));
            getContext().getSession().setAttribute(
                    "comments", comments);
        }

        return comments;
    }

    public static void putProfile(String user, String profile) {
        Map<String, String> profiles = getProfiles();
        profiles.put(user, profile);
        getContext().getSession().setAttribute("profiles", profiles);
    }

    public static Map<String, String> getProfiles() {
        Map<String, String> profiles = ((Map<String, String>)
                getContext().getSession().
                getAttribute("profiles"));
        if (null == profiles) {
            profiles = new HashMap<String, String>();
            profiles.put("Alice", "Internet commenter extraordinaire!");
            profiles.put("Carl", "Comment enthusiast and resident expert");
            profiles.put("Bob", "I'm Bob");
            profiles.put("Marcel", "Novelist, critic, essayist.");
            getContext().getSession()
                .setAttribute("profiles", profiles);
        }
        return profiles;
    }

}
