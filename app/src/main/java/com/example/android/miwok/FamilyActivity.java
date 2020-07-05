
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

public class FamilyActivity extends AppCompatActivity {
    MediaPlayer media;
    AudioManager audio;
    AudioManager.OnAudioFocusChangeListener listener;

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
                    media = MediaPlayer.create(FamilyActivity.this, word.getMusic());
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
