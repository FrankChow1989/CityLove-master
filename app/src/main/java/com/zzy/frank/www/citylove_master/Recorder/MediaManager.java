package com.zzy.frank.www.citylove_master.Recorder;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;

import java.io.IOException;

public class MediaManager
{

    private static MediaPlayer mMediaPlayer;

    private static boolean isPause;

    public static void playSound(
            Context context, String filePath,
            OnCompletionListener onCompletionListener)
    {
        // TODO Auto-generated method stub
        if (mMediaPlayer == null)
        {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setOnErrorListener(new OnErrorListener()
            {

                @Override
                public boolean onError(MediaPlayer mp, int what, int extra)
                {
                    // TODO Auto-generated method stub
                    mMediaPlayer.reset();
                    return false;
                }
            });

        } else
        {
            mMediaPlayer.reset();
        }

        Uri uri = Uri.parse(filePath);
        try
        {
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnCompletionListener(onCompletionListener);
            mMediaPlayer.setDataSource(context,uri);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void pause()
    {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying())
        {
            mMediaPlayer.pause();
            isPause = true;
        }
    }

    public static void resume()
    {
        if (mMediaPlayer != null && isPause)
        {
            mMediaPlayer.start();
            isPause = true;
        }
    }

    public static void release()
    {
        if (mMediaPlayer != null)
        {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
