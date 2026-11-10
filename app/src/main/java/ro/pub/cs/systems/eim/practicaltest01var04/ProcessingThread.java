package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    private String studentName;
    private String studentGroup;

    final public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.practicaltest01.name",
            "ro.pub.cs.systems.eim.practicaltest01.group"
    };

    public ProcessingThread(Context context, String name, String group) {
        this.context = context;

        studentName = name;
        studentGroup = group;
    }

    @Override
    public void run() {
        while (isRunning) {
            sendMessage();
            sleep();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(actionTypes[random.nextInt(actionTypes.length)]);
        intent.putExtra("BROADCAST_RECEIVER_EXTRA",
                new Date(System.currentTimeMillis()) + " " + studentName + " " + studentName);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}