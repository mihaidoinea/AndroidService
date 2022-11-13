package ro.csie.en.androidservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    EditText etFirst, etSecond, etResult;
    Button button;

    IService androidService;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            androidService = IService.Stub.asInterface(iBinder);
            Log.d(TAG,"Service was connected!");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            androidService = null;
            Log.d(TAG,"Service was disconnected!");
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        if(androidService == null)
        {
            Intent intent = new Intent(MainActivity.this, RemoteService.class);
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onSTop");
        unbindService(serviceConnection);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirst = findViewById(R.id.etFirst);
        etSecond = findViewById(R.id.etSecond);
        etResult = findViewById(R.id.etResult);
        button = findViewById(R.id.button);

        button.setOnClickListener(view -> {

            String first = etFirst.getText().toString();
            String second = etSecond.getText().toString();
            String result = null;
            try {
                result = androidService.concatenate(first, second);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            etResult.setText(result);
        });

    }
}