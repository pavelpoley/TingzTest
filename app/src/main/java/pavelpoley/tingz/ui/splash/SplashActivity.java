package pavelpoley.tingz.ui.splash;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import pavelpoley.tingz.R;
import pavelpoley.tingz.ui.MainActivity;
import pavelpoley.tingz.system.Navigation;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        SplashViewModel splashViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);

        //in this case whatever the response success or failure the app will continue to MainActivity and load from local db
        splashViewModel.getData().observe(this, status -> {
            switch (status) {
                case NEXT:
                    startMainActivityAndFinish();
                    break;

                case ERROR:
                    startMainActivityAndFinish();
                    break;
            }
        });;

    }

    private void startMainActivityAndFinish() {
        Navigation.startActivityAndFinish(this, MainActivity.class);
    }
}
