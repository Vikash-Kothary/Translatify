package com.brumhack.translatify;

import android.os.AsyncTask;
import android.widget.TextView;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Created by Vikash Kothary on 25-Oct-15.
 */
public class Translator {
    public static void translate(final String text, final Language language, final TextView textView) throws Exception {
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
                textView.append(text);
//                textView.setText("text is " + text);
            }
        }.execute(text);
    }
}
