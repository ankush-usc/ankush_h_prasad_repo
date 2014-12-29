package csci571.real_estate_search_ahp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class Processjson extends AsyncTask<String,String,JSONObject>{
	private String homedetails;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String latitude;
	private String longitude;
	private String useCode;
	private String lastSoldPrice;
	private String yearBuilt;
	private String lastSoldDate;
	private String lotSizeSqFt;
	private String estimateLastUpdate;
	private String estimateAmount;
	private String finishedSqFt;
	private String estimateValueChangeSign;
	private String imgn;
	private String imgp;
	private String estimateValueChange;
	private String estimateValuationRangeLow;
	private String estimateValuationRangeHigh;
	private String restimateLastUpdate;
	private String restimateAmount;
	private String taxAssessmentYear;
	private String restimateValueChangeSign;
	private String restimateValueChange;
	private String taxAssessment;
	private String restimateValuationRangeLow;
	private String restimateValuationRangeHigh;
	private String year_1;
	private String year_5;
	private String year_10;
	JSONObject result=null;
	String url;
public String gethomedetails() {
    return homedetails;
}
public void setLimit(String homedetails) {
    this.homedetails = homedetails;
}
public String getstreet() {
    return street;
}
public void setNext(String street) {
    this.street = street;
}
public String getcity() {
return city;
}
public void setcity(String city) {
this.city = city;
}

public String getstate() {
return state;
}
public void setstate(String state) {
this.state = state;
}

public String getzipcode() {
return zipcode;
}
public void setzipcode(String zipcode) {
this.zipcode = zipcode;
}

public String getlatitude() {
return latitude;
}
public void setlatitude(String latitude) {
this.latitude = latitude;
}

public String getlongitude() {
return longitude;
}
public void setlongitude(String longitude) {
this.longitude = longitude;
}

public String getuseCode() {
return useCode;
}
public void setuseCode(String useCode) {
this.useCode = useCode;
}

public String getlastSoldPrice() {
return lastSoldPrice;
}
public void setlastSoldPrice(String lastSoldPrice) {
this.lastSoldPrice = lastSoldPrice;
}

public String getyearBuilt() {
return yearBuilt;
}
public void setyearBuilt(String yearBuilt) {
this.yearBuilt = yearBuilt;
}

public String getlastSoldDate() {
return lastSoldDate;
}
public void setlastSoldDate(String lastSoldDate) {
this.lastSoldDate = lastSoldDate;
}

public String getlotSizeSqFt() {
return lotSizeSqFt;
}
public void setlotSizeSqFt(String lotSizeSqFt) {
this.lotSizeSqFt = lotSizeSqFt;
}

public String getestimateLastUpdate() {
return estimateLastUpdate;
}
public void setestimateLastUpdate(String estimateLastUpdate) {
this.estimateLastUpdate = estimateLastUpdate;
}

public String getestimateAmount() {
return estimateAmount;
}
public void setestimateAmount(String estimateAmount) {
this.estimateAmount = estimateAmount;
}

public String getfinishedSqFt() {
return finishedSqFt;
}
public void setfinishedSqFt(String finishedSqFt) {
this.finishedSqFt = finishedSqFt;
}

public String getestimateValueChangeSign() {
return estimateValueChangeSign;
}
public void setestimateValueChangeSign(String estimateValueChangeSign) {
this.estimateValueChangeSign = estimateValueChangeSign;
}

public String getimgn() {
return imgn;
}
public void setimgn(String imgn) {
this.imgn = imgn;
}

public String getimgp() {
return imgp;
}
public void setimgp(String imgp) {
this.imgp = imgp;
}

public String getestimateValueChange() {
return estimateValueChange;
}
public void setestimateValueChange(String estimateValueChange) {
this.estimateValueChange = estimateValueChange;
}

public String getestimateValuationRangeLow() {
return estimateValuationRangeLow;
}
public void setestimateValuationRangeLow(String estimateValuationRangeLow) {
this.estimateValuationRangeLow = estimateValuationRangeLow;
}

public String getestimateValuationRangeHigh() {
return estimateValuationRangeHigh;
}
public void setestimateValuationRangeHigh(String estimateValuationRangeHigh) {
this.estimateValuationRangeHigh = estimateValuationRangeHigh;
}

public String getrestimateLastUpdate() {
return restimateLastUpdate;
}
public void setrestimateLastUpdate(String restimateLastUpdate) {
this.restimateLastUpdate = restimateLastUpdate;
}

public String getrestimateAmount() {
return restimateAmount;
}
public void setrestimateAmount(String restimateAmount) {
this.restimateAmount = restimateAmount;
}

public String gettaxAssessmentYear() {
return taxAssessmentYear;
}
public void settaxAssessmentYear(String taxAssessmentYear) {
this.taxAssessmentYear = taxAssessmentYear;
}

public String getrestimateValueChangeSign() {
return restimateValueChangeSign;
}
public void setrestimateValueChangeSign(String restimateValueChangeSign) {
this.restimateValueChangeSign = restimateValueChangeSign;
}

public String getrestimateValueChange() {
return restimateValueChange;
}
public void setrestimateValueChange(String restimateValueChange) {
this.restimateValueChange = restimateValueChange;
}

public String gettaxAssessment() {
return taxAssessment;
}
public void settaxAssessment(String taxAssessment) {
this.taxAssessment = taxAssessment;
}

public String getrestimateValuationRangeLow() {
return restimateValuationRangeLow;
}
public void setrestimateValuationRangeLow(String restimateValuationRangeLow) {
this.restimateValuationRangeLow = restimateValuationRangeLow;
}

public String getrestimateValuationRangeHigh() {
return restimateValuationRangeHigh;
}
public void setrestimateValuationRangeHigh(String restimateValuationRangeHigh) {
this.restimateValuationRangeHigh = restimateValuationRangeHigh;
}

public String getyear_1() {
return year_1;
}
public void setyear_1(String year_1) {
this.year_1 = year_1;
}

public String getyear_5() {
return year_5;
}
public void setyear_5(String year_5) {
this.year_5 = year_5;
}

public String getyear_10() {
return year_10;
}
public void setyear_10(String year_10) {
this.year_10 = year_10;
}

public JSONObject getJSONFromUrl() {
    this.execute(url);
    return result;
}

public Processjson(String url){
   this.url = url;
}
@SuppressWarnings("finally")
@Override
protected JSONObject doInBackground(String... params) {
	//String url = params[0];
	
	JSONObject jObj=null;
	try {
		HttpEntity entity=null;
		try{
	  HttpClient httpclient = new DefaultHttpClient();
      HttpGet httppost = new HttpGet(url);
      httppost.setHeader("Content-type", "application/json");
	  HttpResponse response = httpclient.execute(httppost);
      entity = response.getEntity();
	 }
      catch(Exception e){
          System.out.println("Error during connection and execution"+e.toString());
  	}
	  
      InputStream is = entity.getContent();
      BufferedReader reader = new BufferedReader(new InputStreamReader(is,HTTP.UTF_8));
      StringBuilder sb = new StringBuilder();
      
      //Build JSON String equivalent
      String line = null;
      try {
          while ((line = reader.readLine()) != null) {
              sb.append((line + "\n"));
          }
      } catch (IOException e) {
          e.printStackTrace();
      } finally {
          try {
              is.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      //System.out.println("String version = "+sb.toString());
      try {
          jObj = new JSONObject(sb.toString());
          //JSONObject json2 = jObj.getJSONObject("results");
          /*JSONObject json_arrayObj = jObj.getJSONObject("result");
          String homedetails_data="unassigned";
          for (int i=0; i < json_arrayObj.length(); i++)
          {
              	  System.out.println(json_arrayObj);
                  //JSONObject oneObject = json_arrayObj.getJSONObject(i);
                  // Pulling items from the array
                  homedetails_data = json_arrayObj.getString("homedetails");
                  //String oneObjectsItem2 = oneObject.getString("anotherSTRINGNAMEINtheARRAY");
          }
          System.out.println("Converted JSON in Process Json =" + homedetails_data );
          */
      } catch (JSONException e) {
          Log.e("JSON Parser", "Error parsing data " + e.toString());
          System.out.println("Error during conversion");   
          }
      /*String aJsonString = jObj.getString("homedetails");
      System.out.println("JSON result:" +aJsonString);*/
      return jObj;
      /*
      Gson gson = new Gson(); // Or use new GsonBuilder().create();
      MyClass myClass = gson.fromJson(json, MyClass.class);*/
     
	}
	catch(Exception e){
System.out.println("GEneric error "+e.toString());	}
	//start the next activity
	return jObj;
}


}
