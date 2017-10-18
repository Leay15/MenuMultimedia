package ivan.u4pr02menumultimedia;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class AudioActivity extends AppCompatActivity {

    ImageButton btnPlay, btnPause, btnStop,btnNext,btnPrevious;
    int pos=0;
    ArrayList<Integer> listaCanciones = new ArrayList<>();
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        btnPlay=(ImageButton)findViewById(R.id.btnPlay);
        btnPause=(ImageButton)findViewById(R.id.btnPause);
        btnStop=(ImageButton)findViewById(R.id.btnStop);
        btnNext=(ImageButton)findViewById(R.id.btnNext);
        btnPrevious=(ImageButton)findViewById(R.id.btnPrev);

        cargarCanciones();
        //Reproductor de audio
        mediaPlayer = MediaPlayer.create(getApplicationContext(), listaCanciones.get(pos));
        //mediaPlayer.start();
        //Toast.makeText(this,"Reproduciendo",Toast.LENGTH_SHORT).show();

        //Boton Play
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                   // mediaPlayer.pause();//Toast.makeText(AudioActivity.this,"Pausando",Toast.LENGTH_SHORT).show();

                }else{
                    mediaPlayer.start();
                    Toast.makeText(AudioActivity.this,"Reproduciendo",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Boton Pause
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    Toast.makeText(AudioActivity.this,"Pausando",Toast.LENGTH_SHORT).show();

                }
            }
        });

        //Boton Stop
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    Toast.makeText(AudioActivity.this,"Audio Detenido",Toast.LENGTH_SHORT).show();
                    try{
                        mediaPlayer.prepare();
                    }catch(IOException io){
                        io.printStackTrace();
                    }
                }

                //Como se detuvo la reproduccion, se debe volver a preparar el proceso para volver a reproducir


            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    adelantar();
                    try{
                        mediaPlayer.prepare();
                    }catch(IOException io){
                        io.printStackTrace();
                    }
                    mediaPlayer=MediaPlayer.create(getApplicationContext(), listaCanciones.get(pos));
                    mediaPlayer.start();
                }else{
                    mediaPlayer.stop();
                    adelantar();
                    try{
                        mediaPlayer.prepare();
                    }catch(IOException io){
                        io.printStackTrace();
                    }
                    mediaPlayer=MediaPlayer.create(getApplicationContext(), listaCanciones.get(pos));
                    mediaPlayer.start();
                }
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                        atrasar();
                        try{
                            mediaPlayer.prepare();
                        }catch(IOException io){
                            io.printStackTrace();
                        }
                        mediaPlayer=MediaPlayer.create(getApplicationContext(), listaCanciones.get(pos));
                        mediaPlayer.start();
                    }else{
                        mediaPlayer.stop();
                        atrasar();
                        try{
                            mediaPlayer.prepare();
                        }catch(IOException io){
                            io.printStackTrace();
                        }
                        mediaPlayer=MediaPlayer.create(getApplicationContext(), listaCanciones.get(pos));
                        mediaPlayer.start();
                    }
                }
        });
    }

    private void atrasar() {
        if((pos-1)<0){
            pos=3;
        }else{
            pos--;
        }
    }

    private void cargarCanciones() {
        listaCanciones.add(R.raw.centerstage);
        listaCanciones.add(R.raw.blameonme);
        listaCanciones.add(R.raw.getlucky);
        listaCanciones.add(R.raw.patience);

    }


    private void adelantar() {
        if((pos+1)>3){
            pos=0;
        }else{
            pos++;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}
