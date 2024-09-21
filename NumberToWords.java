public class TimerService extends Service {
    private long startTime;
    private boolean isRunning;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startTime = System.currentTimeMillis();
        isRunning = true;
        startForeground(1, getNotification());
        new TimerTask().execute();
        return START_STICKY;
    }

    private Notification getNotification() {
        // Build and return your notification here
    }

    private class TimerTask extends AsyncTask<Void, Long, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            while (isRunning) {
                publishProgress(System.currentTimeMillis() - startTime);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            // Update notification with elapsed time
        }
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        super.onDestroy();
    }
}
