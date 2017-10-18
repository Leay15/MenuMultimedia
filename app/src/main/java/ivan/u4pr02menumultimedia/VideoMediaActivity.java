package ivan.u4pr02menumultimedia;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class VideoMediaActivity extends AppCompatActivity {

    SurfaceHolder surfaceHolder;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_media);

        //Creamos un URI para cargar el objeto mediaPlayer
        Uri miUri = Uri.parse("android:resource://"+getPackageName()+"/"+R.raw.landscape);
        mediaPlayer=MediaPlayer.create(this,R.raw.landscape);
        //loop
        mediaPlayer.setLooping(true);
        /*try{
            mediaPlayer.setDataSource(getApplicationContext(),miUri);

        }catch (IOException io){
            io.printStackTrace();
        }catch (IllegalArgumentException ia){
            ia.printStackTrace();
        }catch(SecurityException se){
            se.printStackTrace();
        }*/

        //Cargamos el video al surface View
        SurfaceView surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        surfaceHolder=surfaceView.getHolder();

        //Evitar que el dispositivo entre en suspencion
        surfaceView.setKeepScreenOn(true);

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                mediaPlayer.setDisplay(surfaceHolder);
                try{
                    mediaPlayer.prepare();
                }catch (IOException io){
                    io.printStackTrace();
                }
                mediaPlayer.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                mediaPlayer.stop();
            }
        });
    }
}
