package com.example.root.test1.serviceHelpers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

import java.io.UnsupportedEncodingException;

/**
 * Created by kic on 1/6/17.
 */

@SuppressLint("ParcelCreator")
public class NetworkResultReceiver extends ResultReceiver {

        public interface Receiver {
            public void onReceiveResult(int resultCode, Bundle resultData) throws UnsupportedEncodingException, InterruptedException;

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
                try {
                    mReceiver.onReceiveResult(resultCode, resultData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

}
