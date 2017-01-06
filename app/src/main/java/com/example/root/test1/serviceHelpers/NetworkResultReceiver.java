package com.example.root.test1.serviceHelpers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by kic on 1/6/17.
 */

@SuppressLint("ParcelCreator")
public class NetworkResultReceiver extends ResultReceiver {

        public interface Receiver {
            public void onReceiveResult(int resultCode, Bundle resultData);

        }

        private Receiver mReceiver;

        public NetworkResultReceiver(Handler handler) {
            super(handler);
        }


        public void setReceiver(Receiver receiver) {
            mReceiver = receiver;
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (mReceiver != null) {
                mReceiver.onReceiveResult(resultCode, resultData);
            }
        }

}
