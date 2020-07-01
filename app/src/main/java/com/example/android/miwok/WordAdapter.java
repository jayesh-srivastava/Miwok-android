package com.example.android.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word>
{

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects) {
        super(context, 0,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemview=convertView;
        if(convertView==null)
        {
            listitemview= LayoutInflater.from(getContext()).inflate(R.layout.numbers_listitem,parent,false);
        }
        Word pos=getItem(position);
        TextView miwok=(TextView) listitemview.findViewById(R.id.miwok_number);
        miwok.setText(pos.getMiwokTranslation());
        TextView def=(TextView) listitemview.findViewById(R.id.number);
        def.setText(pos.getDefaultTranslation());
        return listitemview;
    }
}
