package com.example.yordan.starwarsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import src.model.SearchAPI;

public class PeopleActivity extends AppCompatActivity {

    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        String result = "";
        String image = "";
        try {
            Intent intent = getIntent();
            position = (int) intent.getSerializableExtra("peoplePosition");
            String url = "http://swapi.co/api/people/"+Integer.toString(position)+"/?format=json";
            result = new SearchAPI().execute(url, "").get();


            TextView detailName = (TextView) findViewById(R.id.detail_name);
            TextView detailMass = (TextView) findViewById(R.id.detail_mass);
            TextView detailGender = (TextView) findViewById(R.id.detail_gender);
            TextView detailHair = (TextView) findViewById(R.id.detail_hair);
            TextView detailEye = (TextView) findViewById(R.id.detail_eye);
            TextView detailBirth = (TextView) findViewById(R.id.detail_birth);
            TextView detailSkin = (TextView) findViewById(R.id.detail_skin);
            TextView detailHomeworld = (TextView) findViewById(R.id.detail_homeworld);
            TextView detailHeight = (TextView) findViewById(R.id.detail_height);
            ImageView detailAvatar = (ImageView) findViewById(R.id.detail_avatar);
            TextView detailFilms = (TextView) findViewById(R.id.detail_films);
            TextView detailSpecies = (TextView) findViewById(R.id.detail_species);
            TextView detailVehicles = (TextView) findViewById(R.id.detail_vehicles);
            TextView detailStarships = (TextView) findViewById(R.id.detail_starships);


            JSONObject data = new JSONObject(result);

            Context c = getApplicationContext();
            int res  = c.getResources().getIdentifier("c"+position, "drawable", c.getPackageName());
            detailAvatar.setImageResource(res);
            /*
            image = new SearchImg().execute((String) data.get("name"), "").get();
            new DownloadImageTask((ImageView) findViewById(R.id.detail_avatar)).execute(image);
            */

            detailName.setText("Name: "+(String) data.get("name"));
            detailBirth.setText("Birth_year: "+(String) data.get("birth_year"));

            detailHair.setText("Hair Color: "+(String) data.get("hair_color"));
            detailMass.setText("Mass: "+(String) data.get("mass")+" kg");
            detailHeight.setText("Height: "+(String) data.get("height")+" cm");
            detailGender.setText("Gender: "+(String) data.get("gender"));
            detailEye.setText("Eye Color: "+(String) data.get("eye_color"));
            detailSkin.setText("Skin Color: "+(String) data.get("skin_color"));

            JSONObject dataPl = new JSONObject(new  SearchAPI().execute((String) data.get("homeworld"), "").get());
            detailHomeworld.setText("Homeworld: "+(String) dataPl.get("name"));

            JSONArray jArray = (JSONArray) data.get("films");
            String ff = "";
            for (int i=0;i<jArray.length();i++){
                if(i > 0)
                    ff += ", ";
                JSONObject dataRq = new JSONObject(new  SearchAPI().execute(jArray.getString(i), "").get());
                ff += (String) dataRq.get("title");
            }
            if(ff.length() == 0)
                ff = "none";
            detailFilms.setText("Films: "+ff);

            jArray = (JSONArray) data.get("species");
            ff = "";
            for (int i=0;i<jArray.length();i++){
                if(i > 0)
                    ff += ", ";
                JSONObject dataRq = new JSONObject(new  SearchAPI().execute(jArray.getString(i), "").get());
                ff += (String) dataRq.get("name");
            }
            if(ff.length() == 0)
                ff = "none";
            detailSpecies.setText("Species: "+ff);

            jArray = (JSONArray) data.get("vehicles");
            ff = "";
            for (int i=0;i<jArray.length();i++){
                if(i > 0)
                    ff += ", ";
                JSONObject dataRq = new JSONObject(new  SearchAPI().execute(jArray.getString(i), "").get());
                ff += (String) dataRq.get("name");
            }
            if(ff.length() == 0)
                ff = "none";
            detailVehicles.setText("Vehicles: "+ff);

            jArray = (JSONArray) data.get("starships");
            ff = "";
            for (int i=0;i<jArray.length();i++){
                if(i > 0)
                    ff += ", ";
                JSONObject dataRq = new JSONObject(new  SearchAPI().execute(jArray.getString(i), "").get());
                ff += (String) dataRq.get("name");
            }
            if(ff.length() == 0)
                ff = "none";
            detailStarships.setText("Starships: "+ff);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}