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

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PhrasesActivity extends AppCompatActivity {
    MediaPlayer media;
    AudioManager audio;
    AudioManager.OnAudioFocusChangeListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        final List<Word> list=new ArrayList<Word>();
        list.add(new Word("minto wuksus","Where are you going?",R.raw.phrase_where_are_you_going));
        list.add(new Word("tinnә oyaase'nә","What is your name?",R.raw.phrase_what_is_your_name));
        list.add(new Word("oyaaset...","My name is...",R.raw.phrase_my_name_is));
        list.add(new Word("michәksәs?","How are you feeling?",R.raw.phrase_how_are_you_feeling));
        list.add(new Word("kuchi achit","I’m feeling good.",R.raw.phrase_im_feeling_good));
        list.add(new Word("әәnәs'aa?","Are you coming?",R.raw.phrase_are_you_coming));
        list.add(new Word("hәә’ әәnәm","Yes, I’m coming.",R.raw.phrase_yes_im_coming));
        list.add(new Word("әәnәm","I'm coming.",R.raw.phrase_im_coming));
        list.add(new Word("yoowutis","Let’s go.",R.raw.phrase_lets_go));
        list.add(new Word("әnni'nem","Come here.",R.raw.phrase_come_here));

        WordAdapter adapter=new WordAdapter(this,list,R.color.category_phrases);
        ListView listview=(ListView)findViewById(R.id.rootviewphrases);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=list.get(position);
                releaseM();
                audio=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
                listener=new AudioManager.OnAudioFocusChangeListener() {
                    @Override
                    public void onAudioFocusChange(int focusChange) {
                        if(focusChange==AudioManager.AUDIOFOCUS_GAIN)
                        {
                            media.start();
                        }
                        else if(focusChange==AudioManager.AUDIOFOCUS_LOSS)
                        {
                            releaseM();
                        }
                        else if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                        {
                            media.pause();
                            media.seekTo(0);
                        }
                    }
                };
                int result=audio.requestAudioFocus(listener , AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    media = MediaPlayer.create(PhrasesActivity.this, word.getMusic());
                    media.start();
                    media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseM();
                        }
                    });
                }
            }
        });
    }
    private void releaseM()
    {
        if(media!=null) {
            media.release();
            media = null;
            audio.abandonAudioFocus(listener);
        }
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        releaseM();
    }
}
