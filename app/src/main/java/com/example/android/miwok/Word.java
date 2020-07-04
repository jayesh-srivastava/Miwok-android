package com.example.android.miwok;

public class Word
{
    private String MiwokTranslation;
    private String DefaultTranslation;
    private int ImageResource=image;
    private static final int image=-1;
    private int music;
    public Word(String MiwokTranslation, String DefaultTranslation,int music)
    {
        this.MiwokTranslation=MiwokTranslation;
        this.DefaultTranslation=DefaultTranslation;
        this.music=music;
    }
    public Word(String MiwokTranslation, String DefaultTranslation,int music, int ImageResource)
    {
        this(MiwokTranslation,DefaultTranslation,music);
        this.ImageResource=ImageResource;
    }
    public String getMiwokTranslation()
    {
        return MiwokTranslation;
    }
    public String getDefaultTranslation()
    {
        return DefaultTranslation;
    }
    public int getImageResource()
    {
        return ImageResource;
    }
    public boolean hasimage()
    {
        boolean result;
        if(ImageResource!=image)
            result=true;
        else
            result=false;
        return result;
    }
    public int getMusic()
    {

        return music;
    }
}
