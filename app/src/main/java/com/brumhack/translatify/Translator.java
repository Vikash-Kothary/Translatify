package com.brumhack.translatify;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Created by Vikash Kothary on 25-Oct-15.
 */
public class Translator {
    private Context context;
    public Translator(Context context ){
        this.context = context;
    }

    public void translate(final String text, final Language language, final String str) throws Exception {
        Log.e("Translator", str);
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                // Set the Client ID / Client Secret once per JVM. It is set statically and applies to all services
                Translate.setClientId(CameraActivity .APP_ID);
                Translate.setClientSecret(CameraActivity.APP_SECRET);

                String translatedText = "";

                // English AUTO_DETECT -> French Change this if u wanna other languages
                try {
                    translatedText = Translate.execute(text, language);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return translatedText;
            }

            @Override
            protected void onPostExecute(String text) {
                Toast.makeText(context, str+text, Toast.LENGTH_SHORT).show();
            }
        }.execute(text);
    }
}
