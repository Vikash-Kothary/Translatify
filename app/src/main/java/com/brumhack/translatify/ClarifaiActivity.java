package com.brumhack.translatify;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;

import java.io.File;
import java.util.List;

public class ClarifaiActivity extends Activity {

    private static final String APP_ID = "DqI1mgCUeAXlPPs8fFAq3WV85iPO3K3DJmzaWBxB";
    private static final String APP_SECRET = "tDbjwTRGP6noQ0NlFH2j7FugR3iN_xPvxjqrocRo";
    private static final String APP_TOKEN = "JbD6XHtC8AX28WeChBAt5rK2lZKeIU";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clarifai);

        TextView textView = (TextView) findViewById(R.id.tags);
        ClarifaiClient clarifai = new ClarifaiClient(APP_ID, APP_SECRET);
        File[] imageFrames = {
                new File("kittens.jpg"),
                new File("puppies.png"),
                new File("cubs.gif")
        };
        List<RecognitionResult> results =
                clarifai.recognize(new RecognitionRequest(imageFrames));

        for (Tag tag : results.get(0).getTags()) {
            System.out.println(tag.getName() + ": " + tag.getProbability());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
