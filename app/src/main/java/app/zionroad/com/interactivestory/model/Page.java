package app.zionroad.com.interactivestory.model;

/**
 * Define th Page class, what have an image id, text, and two choices.
 * The class hve two constructor: one for default page and one for final
 * (for usage look the Story class)
 */
public class Page {
    private Integer mImageId;
    private String mText;
    private Choice mChoice1;
    private Choice mChoice2;
    private Boolean mIsFinal = false;

    Page(Integer imageId, String text, Choice choice1, Choice choice2) {
        mImageId = imageId;
        mText = text;
        mChoice1 = choice1;
        mChoice2 = choice2;
    }

    Page(Integer imageId, String text) {
        mImageId = imageId;
        mText = text;
        mIsFinal = true;
    }

    public Integer getImageId() {
        return mImageId;
    }

    public String getText() {
        return mText;
    }

    public Choice getChoice1() {
        return mChoice1;
    }

    public Choice getChoice2() {
        return mChoice2;
    }

    public Boolean getFinal() {
        return mIsFinal;
    }
}
