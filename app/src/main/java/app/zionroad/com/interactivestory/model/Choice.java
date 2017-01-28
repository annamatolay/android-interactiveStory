package app.zionroad.com.interactivestory.model;

/**
 * Define the base choice class, what have a text and page number field
 * (for usage look the Story calls)
 */
public class Choice {
    private String mText;
    private Integer mNextPage;

    Choice(String text, Integer nextPage) {
        mText = text;
        mNextPage = nextPage;
    }

    public String getText() {
        return mText;
    }

    public Integer getNextPage() {
        return mNextPage;
    }
}
