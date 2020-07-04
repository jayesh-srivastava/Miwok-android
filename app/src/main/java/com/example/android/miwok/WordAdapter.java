package com.example.android.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word>
{
    private int mcolor;
    Word pos;

    public WordAdapter(@NonNull Context context,@NonNull List<Word> objects,int mcolor) {
        super(context,0,objects);
        this.mcolor=mcolor;
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemview=convertView;
        if(convertView==null)
        {
            listitemview= LayoutInflater.from(getContext()).inflate(R.layout.listitem,parent,false);
        }
        pos=getItem(position);

        TextView miwok=(TextView) listitemview.findViewById(R.id.miwok_number);
        miwok.setText(pos.getMiwokTranslation());
        int textcolor=ContextCompat.getColor(getContext(),R.color.tan_background);
        miwok.setTextColor(textcolor);
        TextView def=(TextView) listitemview.findViewById(R.id.number);
        def.setText(pos.getDefaultTranslation());
        def.setTextColor(textcolor);
        ImageView img=(ImageView) listitemview.findViewById(R.id.image);
        if(pos.hasimage()) {
            img.setImageResource(pos.getImageResource());
            img.setVisibility(View.VISIBLE);
        }
        else
        {
            img.setVisibility(View.GONE);
        }


        View color=listitemview.findViewById(R.id.text);
        int setcolor= ContextCompat.getColor(getContext(),mcolor);
        color.setBackgroundColor(setcolor);


        return listitemview;

    }
}