
package br.com.atila.mygame2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;
import java.text.DecimalFormat;


public class GameOn extends AppCompatActivity implements SensorEventListener {

    SensorManager sm;
    Sensor acelerometro;

    TextView accX = null;
    TextView accY = null;
    TextView accZ = null;
    View view;

    DecimalFormat decimal = new DecimalFormat("0.###");
    private Random numeroAleatorio;


    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_on);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE); //Acessando os sensores
        acelerometro = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //Acessando o acelerometro

        accX = (TextView) findViewById(R.id.X);
        accY = (TextView) findViewById(R.id.Y);
        accZ = (TextView) findViewById(R.id.Z);
        view = findViewById(R.id.Tela);


        numeroAleatorio = new Random();


        Button pedra = (Button) findViewById(R.id.button1);

        pedra.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                findViewById(R.id.imageView1).setBackgroundResource(R.drawable.pedra);

                realizaJogadaPC(1);

            }

        });


        Button papel = (Button) findViewById(R.id.button2);

        papel.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                findViewById(R.id.imageView1).setBackgroundResource(R.drawable.papel);

                realizaJogadaPC(2);

            }

        });


        Button tesoura = (Button) findViewById(R.id.button3);

        tesoura.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                findViewById(R.id.imageView1).setBackgroundResource(R.drawable.tesoura);

                realizaJogadaPC(3);

            }

        });


    }

    public void realizaJogadaPC(int jogadaPlayer) {

        int jogadaPC;

        jogadaPC = numeroAleatorio.nextInt(3) + 1;

        if (jogadaPC == 1) {

            findViewById(R.id.imageView3).setBackgroundResource(R.drawable.pedra);


            if (jogadaPlayer == 1)

                findViewById(R.id.imageView2).setBackgroundResource(R.drawable.empatou);

            else if (jogadaPlayer == 2)

                findViewById(R.id.imageView2).setBackgroundResource(R.drawable.venceu);

            else

                findViewById(R.id.imageView2).setBackgroundResource(R.drawable.perdeu);

        } else if (jogadaPC == 2) {

            findViewById(R.id.imageView3).setBackgroundResource(R.drawable.papel);

            if (jogadaPlayer == 1)

                findViewById(R.id.imageView2).setBackgroundResource(R.drawable.perdeu);

            else if (jogadaPlayer == 2)

                findViewById(R.id.imageView2).setBackgroundResource(R.drawable.empatou);

            else

                findViewById(R.id.imageView2).setBackgroundResource(R.drawable.venceu);

        } else if (jogadaPC == 3) {

            findViewById(R.id.imageView3).setBackgroundResource(R.drawable.tesoura);

            if (jogadaPlayer == 1)

                findViewById(R.id.imageView2).setBackgroundResource(R.drawable.venceu);

            else if (jogadaPlayer == 2)

                findViewById(R.id.imageView2).setBackgroundResource(R.drawable.perdeu);

            else

                findViewById(R.id.imageView2).setBackgroundResource(R.drawable.empatou);

        }

    }


    @Override

    protected void onResume() {
        super.onResume();
        sm.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL); //Inicia o programa

    }

    @Override

    protected void onPause() {

        super.onPause();
        sm.unregisterListener(this);// para tudo
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        double ax = (event.values[0]);
        double ay = (event.values[1]);
        double az = (event.values[2]);

        String AX = decimal.format(ax);
        String AY = decimal.format(ay);
        String AZ = decimal.format(az);

        accX.setText("X:" + (AX));
        accY.setText("Y:" + (AY));
        accZ.setText("Z:" + (AZ));

        if (Math.abs(ay) > 9.0) {
            //view.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            findViewById(R.id.imageView3).setBackgroundResource(R.drawable.pedra);
            realizaJogadaPC(3);

        }

        if (Math.abs(ax) > 9.0) {
            // view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            realizaJogadaPC(1);
        }

        if (Math.abs(az) > 9.0) {
            //view.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
            realizaJogadaPC(2);


        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
