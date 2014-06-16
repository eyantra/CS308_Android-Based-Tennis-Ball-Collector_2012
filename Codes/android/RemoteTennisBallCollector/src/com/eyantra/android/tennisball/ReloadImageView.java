package com.eyantra.android.tennisball;

import java.io.InputStream;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;



public class ReloadImageView extends TimerTask {
	Activity context;
    Timer timer;
    ImageView iv;
    TextView tv;
    int t=0;
    String URL;
    
	private Drawable grabImageFromUrl(String url) throws Exception {
		return Drawable.createFromStream((InputStream)new URL(url).getContent(), "src");
	}
	   
    public ReloadImageView(Activity context, int milliseconds, ImageView iv, String URL, TextView tv) {
        this.context = context;
        this.iv = iv;
        this.tv = tv;
        this.URL= URL;

        timer = new Timer();
        timer.schedule(this,
                0,  			// initial delay
                milliseconds);  // subsequent rate
    }

	@Override
    public void run() {
        if(context == null || context.isFinishing()) {
            // Activity killed
            this.cancel();
            return;
        }
        context.runOnUiThread(new Runnable() {
            public void run() {
            	t++;
            	String iurl = URL+"?dummy="+ t;
                tv.setText("Displaying "+ iurl);
            	try {
        		    iv.setImageDrawable(grabImageFromUrl(iurl));
        		} catch(Exception e) {
        			iv.setImageResource(R.drawable.notfound);
        		    tv.setText("Error: Exception");
        		}
            }
        });
    }
}