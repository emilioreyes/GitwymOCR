/*
 * Copyright 2011 Robert Theis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.sfsu.cs.orange.ocr.language;

import org.apache.http.Header;
import org.json.simple.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import edu.sfsu.cs.orange.ocr.CaptureActivity;
import edu.sfsu.cs.orange.ocr.R;
import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
//----------------

//-----------------
/**
 * Class to perform translations in the background.
 */
public final class TranslateAsyncTask extends AsyncTask<String, String, Boolean> {

  private static final String TAG = TranslateAsyncTask.class.getSimpleName();

  private CaptureActivity activity;
  private TextView textView;
  private View progressView;
  private TextView targetLanguageTextView;
  private String sourceLanguageCode;
  private String targetLanguageCode;
  private String sourceText;
  private String translatedText = "";

  public TranslateAsyncTask(CaptureActivity activity, String sourceLanguageCode, String targetLanguageCode, 
      String sourceText) {
    this.activity = activity;
    this.sourceLanguageCode = sourceLanguageCode;
    this.targetLanguageCode = targetLanguageCode;
    this.sourceText = sourceText;
    textView = (TextView) activity.findViewById(R.id.translation_text_view);
    progressView = (View) activity.findViewById(R.id.indeterminate_progress_indicator_view);
    targetLanguageTextView = (TextView) activity.findViewById(R.id.translation_language_text_view);
  }
  //-----
  @SuppressLint("NewApi")
  //----------------------------------------------------------------
@Override
  protected Boolean doInBackground(String... arg0) {
   translatedText = Translator.translate(activity, sourceLanguageCode, targetLanguageCode, sourceText);

    // Check for failed translations.
    if (translatedText.equals(Translator.BAD_TRANSLATION_MSG)) {
      return false;
    }
    
    return true;
	  //-----------------------------------------------------------
	 /* AsyncHttpClient client = new AsyncHttpClient();
	  client.get("http://www.google.com", new AsyncHttpResponseHandler() {

		@Override
		public void onFailure(int arg0, Header[] arg1, byte[] arg2,
				Throwable arg3) {
			// TODO Auto-generated method stub
			System.out.println("estamos en el onfailure");
		}

		@Override
		public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
			// TODO Auto-generated method stub
			System.out.println("estamos en el onSuccess");
		}
		  
	  });

	/*  AsyncHttpClient client = new AsyncHttpClient();
	  RequestParams rp = new RequestParams();
	  rp.put("param1", value1);
	  rp.put("param2", value2);
	  client.post("url a la que hacemos la peticion",rp, new JsonHttpResponseHandler(){
	   public void onSuccess(JSONObject jObject){    
	       
	   }   
	   public void onFailure(Throwable arg0){
	       
	   }
	  });*/ 
	  
	 
	/*  translatedText="algo de texto traducido";
	  if (translatedText.trim().length()<0) {
	      return false;
	    }
	  return true;*/
	  //-----------------------------
  }

  @Override
  protected synchronized void onPostExecute(Boolean result) {
    super.onPostExecute(result);
    
    if (result) {
      //Log.i(TAG, "SUCCESS");
      if (targetLanguageTextView != null) {
        targetLanguageTextView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL), Typeface.NORMAL);
      }
      textView.setText(translatedText);
      textView.setVisibility(View.VISIBLE);
      textView.setTextColor(activity.getResources().getColor(R.color.translation_text));

      // Crudely scale betweeen 22 and 32 -- bigger font for shorter text
      int scaledSize = Math.max(22, 32 - translatedText.length() / 4);
      textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaledSize);

    } else {
      Log.e(TAG, "FAILURE");
      targetLanguageTextView.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC), Typeface.ITALIC);
      targetLanguageTextView.setText("Unavailable");

    }
    
    // Turn off the indeterminate progress indicator
    if (progressView != null) {
      progressView.setVisibility(View.GONE);
    }
  }
  //------------------------------------------------------------------
 
  //----------------------------------------------------------------------------
}
