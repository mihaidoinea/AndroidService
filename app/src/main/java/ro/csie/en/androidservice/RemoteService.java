package ro.csie.en.androidservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class RemoteService extends Service {

    private final IService.Stub mBinder = new IService.Stub() {
        @Override
        public String concatenate(String first, String second) throws RemoteException {
            return first + " - " + second;
        }
    };

    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }
}