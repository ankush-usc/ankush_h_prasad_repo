package csci571.real_estate_search_ahp;

import java.io.InputStream;
import java.net.URL;


import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

public class Get_Image extends AsyncTask<String,String,Drawable>{ 
String fpath;
public Get_Image(String path){
	fpath = path;
}

@Override
protected Drawable doInBackground(String... params) {
	 try
     {
     InputStream is = (InputStream) new URL(fpath).getContent();
     Drawable d = Drawable.createFromStream(is, "src name");
     return d;
     }catch (Exception e) {
     System.out.println("Exc="+e);
     return null;
     }
}
}