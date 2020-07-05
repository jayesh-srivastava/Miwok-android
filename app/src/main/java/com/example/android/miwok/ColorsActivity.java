
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

public class ColorsActivity extends AppCompatActivity {
    MediaPlayer media;
    AudioManager audio;
    AudioManager.OnAudioFocusChangeListener listener;

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
                    media = MediaPlayer.create(ColorsActivity.this, word.getMusic());
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
