package cn.wenmq.clock;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import cn.wenmq.clock.view.ParticleClockView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParticleClockView particleClockView = findViewById(R.id.particle_clock);
        particleClockView.start();
    }
}