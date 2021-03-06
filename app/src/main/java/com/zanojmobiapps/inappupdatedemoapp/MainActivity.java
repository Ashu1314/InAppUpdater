package com.zanojmobiapps.inappupdatedemoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.sanojpunchihewa.updatemanager.UpdateManager;
import com.sanojpunchihewa.updatemanager.UpdateManager.onVersionCheckListener;
import com.sanojpunchihewa.updatemanager.UpdateManagerConstant;

public class MainActivity extends AppCompatActivity {

    // Declare the UpdateManager
    UpdateManager mUpdateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtCurrentVersion = findViewById(R.id.txt_current_version);
        TextView txtAvailableVersion = findViewById(R.id.txt_available_version);

        txtCurrentVersion.setText(String.valueOf(BuildConfig.VERSION_CODE));

        // Initialize the Update Manager with the Activity and the Update Mode
        mUpdateManager = UpdateManager.Builder(this);

        // Callback from Available version code
        mUpdateManager.getAvailableVersionCode(new onVersionCheckListener() {
            @Override
            public void onReceiveVersionCode(final int code) {
                txtAvailableVersion.setText(String.valueOf(code));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Continue updates when resumed
        mUpdateManager.continueUpdate();
    }

    public void callFlexibleUpdate(View view) {
        // Start a Flexible Update
        mUpdateManager.mode(UpdateManagerConstant.FLEXIBLE).start();
    }

    public void callImmediateUpdate(View view) {
        // Start a Immediate Update
        mUpdateManager.mode(UpdateManagerConstant.IMMEDIATE).start();
    }
}
