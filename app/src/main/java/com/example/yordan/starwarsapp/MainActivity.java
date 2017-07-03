package com.example.yordan.starwarsapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.PeopleAdapter;
import src.model.TPeopleALL;

public class MainActivity extends AppCompatActivity {

    ListView peopleListView;
    String item1, item2, item3, item4, item5, item6, item7, item8, item9, item10;
    TextView urlprev, urlnext;
    private Button btNext, btPrevious;
    String next = "nada";
    String previous = "nada";


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.urlprev = (TextView) findViewById(R.id.peopleList_previous_url);
        this.urlnext = (TextView) findViewById(R.id.peopleList_next_url);

        this.btNext = (Button) findViewById(R.id.peopleList_next);
        this.btNext.setOnClickListener(new OnClickBtNext());

        this.btPrevious = (Button) findViewById(R.id.peopleList_previous);
        this.btPrevious.setOnClickListener(new OnClickBtPrev());

        peopleListView = (ListView) findViewById(R.id.peopleList_listAllPeople);
        buildPeopleList("http://swapi.co/api/people/?format=json&page=1");

        peopleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.light_saber_on);
                ring.start();
                Intent intentPeopleInsert = new Intent(MainActivity.this, PeopleActivity.class);

                String url = "";
                if(position == 0)
                    url = item1;
                if(position == 1)
                    url = item2;
                if(position == 2)
                    url = item3;
                if(position == 3)
                    url = item4;
                if(position == 4)
                    url = item5;
                if(position == 5)
                    url = item6;
                if(position == 6)
                    url = item7;
                if(position == 7)
                    url = item8;
                if(position == 8)
                    url = item9;
                if(position == 9)
                    url = item10;

                String partes[] = url.split("/");
                int pessoaId = Integer.parseInt(partes[partes.length-1]);

                intentPeopleInsert.putExtra("peoplePosition", pessoaId);

                startActivity(intentPeopleInsert);
            }
        });

    }


    public void buildPeopleList(String url) {
        List<String> peopleList = new ArrayList<String>();

        String retorno = "";
        try {
            retorno = new TPeopleALL().execute(url, "").get();
        } catch (Exception e) {
        e.printStackTrace();
        }


        try {
            JSONObject data = new JSONObject(retorno);

            next = "nada";
            try {
                next = (String) data.get("next");
            } catch (Exception e) {
                // TODO: handle exception
            }

            previous = "nada";
            try {
                previous = (String) data.get("previous");
            } catch (Exception e) {
                // TODO: handle exception
            }

            JSONArray jArray = (JSONArray) data.get("results");

            String allThisPage = "";

            for (int i = 0; i < jArray.length(); ++i) {
                JSONObject rec = jArray.getJSONObject(i);
                String name = (String) rec.get("name");
                String gender = (String) rec.get("gender");
                String birth_year = (String) rec.get("birth_year");
                String urlPerson = (String) rec.get("url");

                String pessoa = name+","+gender+","+birth_year+","+urlPerson;

                if(i == 0)
                    this.item1 = urlPerson;
                if(i == 1)
                    this.item2 = urlPerson;
                if(i == 2)
                    this.item3 = urlPerson;
                if(i == 3)
                    this.item4 = urlPerson;
                if(i == 4)
                    this.item5 = urlPerson;
                if(i == 5)
                    this.item6 = urlPerson;
                if(i == 6)
                    this.item7 = urlPerson;
                if(i == 7)
                    this.item8 = urlPerson;
                if(i == 8)
                    this.item9 = urlPerson;
                if(i == 9)
                    this.item10 = urlPerson;

                peopleList.add(pessoa);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        urlprev.setText(previous);
        urlnext.setText(next);

        if(next.equals("nada")) {
            this.btNext.setVisibility(View.GONE);
        }else{
            this.btNext.setVisibility(View.VISIBLE);
        }

        if(previous.equals("nada")){
            this.btPrevious.setVisibility(View.GONE);
        }else{
            this.btPrevious.setVisibility(View.VISIBLE);
        }

        PeopleAdapter adapter = new PeopleAdapter(this, peopleList);
        peopleListView.setAdapter(adapter);

}


    private class OnClickBtNext implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.blaster);
            ring.start();
            String nx = urlnext.getText().toString();
            buildPeopleList(nx);
        }
    }

    private class OnClickBtPrev implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.blaster);
            ring.start();
            String prev = urlprev.getText().toString();
            buildPeopleList(prev);
        }
    }



}