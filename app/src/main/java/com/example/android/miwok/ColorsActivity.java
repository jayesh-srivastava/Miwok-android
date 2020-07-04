/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ColorsActivity extends AppCompatActivity {
    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final List<Word> list=new ArrayList<Word>();
        list.add(new Word("weṭeṭṭi","red",R.raw.color_red,R.drawable.color_red));
        list.add(new Word("chokokki","green",R.raw.color_green,R.drawable.color_green));
        list.add(new Word("ṭakaakki","brown",R.raw.color_brown,R.drawable.color_brown));
        list.add(new Word("ṭopoppi","gray",R.raw.color_gray,R.drawable.color_gray));
        list.add(new Word("kululli","black",R.raw.color_black,R.drawable.color_black));
        list.add(new Word("kelelli","white",R.raw.color_white,R.drawable.color_white));
        list.add(new Word("ṭopiisә","dusty yellow",R.raw.color_dusty_yellow,R.drawable.color_dusty_yellow));
        list.add(new Word("chiwiiṭә","mustard yellow",R.raw.color_mustard_yellow,R.drawable.color_mustard_yellow));

        WordAdapter adapter= new WordAdapter(this,list,R.color.category_colors);
        ListView listview=(ListView)findViewById(R.id.rootviewcolors);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=list.get(position);
                releaseM();
                media=MediaPlayer.create(ColorsActivity.this, word.getMusic());
                media.start();
                media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast toast=Toast.makeText(ColorsActivity.this, "Done!",Toast.LENGTH_SHORT);
                        toast.show();
                        releaseM();
                    }
                });
            }
        });
    }
    private void releaseM()
    {
        if(media!=null)
            media.release();
        media=null;
    }

}
