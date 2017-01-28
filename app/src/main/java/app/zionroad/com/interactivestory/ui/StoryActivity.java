package app.zionroad.com.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import app.zionroad.com.interactivestory.R;
import app.zionroad.com.interactivestory.model.Page;
import app.zionroad.com.interactivestory.model.Story;

public class StoryActivity extends AppCompatActivity {
    // Define tag for logging
    private final static String TAG = StoryActivity.class.getSimpleName();

    // --- Declare the necessary fields ---
    // for android widgets
    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoice1;
    private Button mChoice2;
    // for my models (for more info: look their own java file)
    private String mName;
    private Story mStory = new Story();
    private Page mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // Get an intent (from MainActivity), based on the given "name" key
        Intent intent = getIntent();
        mName = intent.getStringExtra(getString(R.string.key_username));

        // If the user doesn't give any name
        if (mName.equals("")) {
            mName = "Stranger";
        }
        // logging
        Log.i(TAG, mName+"<<<NAME STORED>>>");

        // get all widgets
        mImageView = (ImageView) findViewById(R.id.storyImageView);
        mTextView = (TextView) findViewById(R.id.storyTextView);
        mChoice1 = (Button) findViewById(R.id.choiceButton1);
        mChoice2 = (Button) findViewById(R.id.choiceButton2);

        // load the first page
        loadPage(0);
    }

    private void loadPage(Integer choice){
        // get the current page based on the user choice (the first one is my choice)
        mCurrentPage = mStory.getPage(choice);

        // draw the new image in the screen
        //TODO: search the newest solution!
        Drawable drawable = getResources().getDrawable(mCurrentPage.getImageId());
        mImageView.setImageDrawable(drawable);

        // format the text with user name, where it necessary
        // (look at the story class and search this string: %1$s)
        String pageText = mCurrentPage.getText();
        pageText = String.format(pageText, mName);
        mTextView.setText(pageText);

        // check the page is final or not
        if (mCurrentPage.getFinal()) {
            // if true, then hide the upper button on the screen, change the button text
            // add a new listener to the second button, what finish this activity, and navigate back to the MainActivity
            mChoice1.setVisibility(View.INVISIBLE);
            mChoice2.setText(R.string.play_again);
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StoryActivity.this.finish();
                }
            });

        } else {
            // if false, then get the current page choices and set it to the button text
            mChoice1.setText(mCurrentPage.getChoice1().getText());
            mChoice2.setText(mCurrentPage.getChoice2().getText());

            // --- set the own listener for the buttons ---
            // get the next page based on user choice and the story class
            // finally load it
            mChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer nextPage = mCurrentPage.getChoice1().getNextPage();
                    StoryActivity.this.loadPage(nextPage);
                }
            });

            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer nextPage = mCurrentPage.getChoice2().getNextPage();
                    StoryActivity.this.loadPage(nextPage);
                }
            });
        }
    }
}
