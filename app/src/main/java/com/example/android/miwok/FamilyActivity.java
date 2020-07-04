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

public class FamilyActivity extends AppCompatActivity {
    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        final List<Word> list=new ArrayList<Word>();
        list.add(new Word("әpә","father",R.raw.family_father,R.drawable.family_father));
        list.add(new Word("әṭa","mother",R.raw.family_mother,R.drawable.family_mother));
        list.add(new Word("angsi","son",R.raw.family_son,R.drawable.family_son));
        list.add(new Word("tune","daughter",R.raw.family_daughter,R.drawable.family_daughter));
        list.add(new Word("taachi","older brother",R.raw.family_older_brother,R.drawable.family_older_brother));
        list.add(new Word("chalitti","younger brother",R.raw.family_younger_brother,R.drawable.family_younger_brother));
        list.add(new Word("teṭe","older sister",R.raw.family_older_sister,R.drawable.family_older_sister));
        list.add(new Word("kolliti","younger sister",R.raw.family_younger_sister,R.drawable.family_younger_sister));
        list.add(new Word("ama","grandmother",R.raw.family_grandmother,R.drawable.family_grandmother));
        list.add(new Word("paapa","grandfather",R.raw.family_grandfather,R.drawable.family_grandfather));

        WordAdapter adapter=new WordAdapter(this,list,R.color.category_family);
        ListView listview=(ListView)findViewById(R.id.rootviewfamily);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=list.get(position);
                releaseM();
                media=MediaPlayer.create(FamilyActivity.this, word.getMusic());
                media.start();
                releaseM();
                media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast toast=Toast.makeText(FamilyActivity.this, "Done!",Toast.LENGTH_SHORT);
                        toast.show();
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
