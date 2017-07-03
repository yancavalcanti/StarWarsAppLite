package src.model;


import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class SearchAPI extends AsyncTask<String, Void, String> {
	
	@Override
	protected String doInBackground(String[] citt){
		String pa = citt[0];

        String oo =  pa;
		
    	try {
         
    		   URL url = new URL(oo);  
               
               HttpClient client = new DefaultHttpClient();
               HttpGet requisicao = new HttpGet();
               requisicao.setHeader("Content-type", "application/json");
               requisicao.setURI(new URI(oo));
               HttpResponse resposta = client.execute(requisicao);
               BufferedReader br = new BufferedReader(new InputStreamReader(
                       resposta.getEntity().getContent()));
               StringBuffer sb = new StringBuffer("");
               
               String linha = null;
               while ((linha = br.readLine()) != null) {
                   sb.append(linha);
               }

               br.close();
               linha = sb.toString();

                return linha;

            
        }catch(Exception e){
        	System.out.println("Erro: "+e);        	
        	return "nada";
        }
    }



}

