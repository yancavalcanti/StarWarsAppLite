package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yordan.starwarsapp.R;

import java.util.List;

import src.model.SearchImg;

public class PeopleAdapter extends BaseAdapter {

    private final Context context;
    private final List<String> retorno;
    private SearchImg img;

    public PeopleAdapter(Context context, List<String> retorno) {
        this.context = context;
        this.retorno = retorno;
    }

    @Override
    public int getCount() {
        return retorno.size();
    }

    @Override
    public Object getItem(int position) {
        return retorno.get(0);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.people_list, parent, false);
        }

        try {

        ImageView avatar = (ImageView) view.findViewById(R.id.peopleList_avatar);
        TextView name = (TextView) view.findViewById(R.id.peopleList_textViewName);
        TextView homeworld = (TextView) view.findViewById(R.id.peopleList_textViewHomeworld);
        TextView yob = (TextView) view.findViewById(R.id.peopleList_textViewBirth);
        TextView url = (TextView) view.findViewById(R.id.peopleList_textViewURL);

        String pessoa[] = retorno.get(i).split(",");
        name.setText(pessoa[0]);
        homeworld.setText(pessoa[1]);
        yob.setText(pessoa[2]);
        url.setText(pessoa[3]);

        String partes[] = url.getText().toString().split("/");
        int pessoaId = Integer.parseInt(partes[partes.length-1]);
        int res  = context.getResources().getIdentifier("c"+pessoaId, "drawable", context.getPackageName());
        avatar.setImageResource(res);

        /*
        String image = new SearchImg().execute(pessoa[0], "").get();
        new DownloadImageTask((ImageView) view.findViewById(R.id.peopleList_avatar)).execute(image);
        */

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    public Context getContext() {
        return context;
    }

}
