package src.model;


import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchImg extends AsyncTask<String, Void, String> {
	
	@Override
	protected String doInBackground(String[] citt){
		String pa = citt[0];

		pa = pa.replace(" ", "%20");
		
		String dl = "http://www.google.com.br/search?hl=pt-BR&safe=off&gbv=2&sout=1&biw=1024&bih=548&tbm=isch&sa=1&q="+pa+"%20star%20wars";
		
		try {
		    DefaultHttpClient defaultClient = new DefaultHttpClient();
		    HttpGet httpGetRequest = new HttpGet(dl);
		    HttpResponse httpResponse = defaultClient.execute(httpGetRequest);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(),"UTF-8"));

		    String search = reader.readLine();
	        String img = "";
		    String expression = "src=\"https?://(www.)?[-a-zA-Z0-9@:%._+~#=]{2,256}.[-a-zA-Z0-9@:%_+.~#?&//=]{2,256}"; 
	        Pattern pattern = Pattern.compile(expression);    
	        Matcher matcher = pattern.matcher(search);    

	        if (matcher.find(0)) {    
	        	String h[] = matcher.group().split("src=\"");
	        	img = h[1];    
	        }    

            String	retorno = img;
                      
            return retorno;
            
        }catch(Exception e){
        	System.out.println("Erro: "+e);        	
        	return "nada";
        }
    }



}

