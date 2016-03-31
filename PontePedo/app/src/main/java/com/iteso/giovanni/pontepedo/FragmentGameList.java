package com.iteso.giovanni.pontepedo;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by giovanni on 24/03/2016.
 */
public class FragmentGameList extends ListFragment{
    
    int[] titles = new int[] {(R.string.game_A), (R.string.game_2), (R.string.game_3), (R.string.game_4),
            (R.string.game_5), (R.string.game_6), (R.string.game_7), (R.string.game_8), (R.string.game_9),
            (R.string.game_10), (R.string.game_J), (R.string.game_Q), (R.string.game_K)};
    int[] icons = new int[]{R.drawable.yo_nunca_nunca, R.drawable.animales, R.drawable.historia, R.drawable.cascada,
            R.drawable.palabra_prohibida, R.drawable.preguntas, R.drawable.pinga_pepa, R.drawable.dedo, R.drawable.discrepo,
            R.drawable.mesero, R.drawable.marcianitos2, R.drawable.caricachupas, R.drawable.el_abuelo};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<13;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("title", getString(titles[i]));
            hm.put("icon", Integer.toString(icons[i]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "icon","title"};

        // Ids of views in listview_layout
        int[] to = { R.id.list_game_image,R.id.list_game_title};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.adapter_game_list, from, to);

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getActivity().getApplicationContext(), ActivityDetail.class);
        intent.putExtra("value", position);
        startActivity(intent);
    }
}
