package app.zionroad.com.interactivestory.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import app.zionroad.com.interactivestory.R;

public class MainActivity extends AppCompatActivity {
    private EditText mNameFiled;
    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the necessary widget component
        mNameFiled = (EditText) findViewById(R.id.nameEditText);
        mStartButton = (Button) findViewById(R.id.startButton);

        // Set a listener to the start button
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the user name, as string, then welcome her/his
                String name = mNameFiled.getText().toString();
                Toast.makeText(MainActivity.this, "Welcome " + name, Toast.LENGTH_SHORT).show();
                MainActivity.this.StartStory(name);
            }
        });
    }

    private void StartStory(String name) {
        // Declare a new intent from this activity, then add the user name to the StoryActivity with a "username" key
        // (key defined in the string value)
        Intent intent = new Intent(this, StoryActivity.class);
        intent.putExtra(getString(R.string.key_username), name);
        // Start an other activity
        startActivity(intent);

    }

    // When the application resumed, then delete the previous user name
    @Override
    protected void onResume() {
        super.onResume();
        mNameFiled.setText("");
    }
}
