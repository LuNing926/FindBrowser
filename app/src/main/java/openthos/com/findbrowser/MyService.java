package openthos.com.findbrowser;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyThread thread = new MyThread();
        thread.isAlive();
    }

    private IBinder mBinder = new IMyAidlInterface.Stub() {
        @Override
        public List<ResolveInfo> getInfo(int tag) throws RemoteException {
            List<String> list = new ArrayList<>();
            return null;
        }
    };

    class MyThread extends Thread {

        @Override
        public void run() {
            super.run();
            getString(R.string.app_name);

            Handler handler = null;
            handler.postDelayed(new MyThread(), 60 * 1000);
        }
    }
}
