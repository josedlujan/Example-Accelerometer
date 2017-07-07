package acelerometro.ejemplo.com.ejemploacelerometro;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView ax,ay,az;
    SensorManager sm;
    Sensor ace;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ax = (TextView) findViewById(R.id.ax);
        ay = (TextView) findViewById(R.id.ay);
        az = (TextView) findViewById(R.id.az);
        linearLayout = (LinearLayout) findViewById(R.id.layout);

        sm =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ace = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sm.registerListener(this,ace,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            ax.setText(Float.toString(sensorEvent.values[0]));
            ay.setText(Float.toString(sensorEvent.values[1]));

            az.setText(Float.toString(sensorEvent.values[2]));


            if(sensorEvent.values[2]>9.5){
                linearLayout.setBackgroundColor(Color.parseColor("#FF0000"));
            }
            if(sensorEvent.values[2]<-9.5){
                linearLayout.setBackgroundColor(Color.parseColor("#00FF00"));
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
