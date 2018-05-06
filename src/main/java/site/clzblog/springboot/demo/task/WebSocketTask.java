package site.clzblog.springboot.demo.task;

public interface WebSocketTask extends Runnable {
    int getProgress();
    String getState();
    String getTaskName();
}
