package site.clzblog.springboot.demo.task.impl;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import site.clzblog.springboot.demo.model.TaskProgressMessage;
import site.clzblog.springboot.demo.task.WebSocketTask;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author chengli.zou
 * @CreateDate 2018-05-06 14:21
 **/
public class WebSocketTaskImpl implements WebSocketTask {
    private int loops = 10;
    private AtomicInteger progress = new AtomicInteger();
    private String state = "NEW";
    private String taskName;
    private Random random = new Random();
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public int getProgress() {
        return progress.get();
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    @Override
    public void run() {
        state = "RUNNING";
        sendProgress();
        for (double i = 0.0; i <= loops; i++) {
            try {
                Thread.sleep(1000);
                Thread.sleep(random.nextInt(5000));
                progress.set((int) ((i/loops)*100));
                sendProgress();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        state = "COMPLETE";
        sendProgress();
    }

    public void sendProgress() {
        TaskProgressMessage temp = new TaskProgressMessage(getTaskName());
        temp.setProgress(progress.get());
        temp.setState(getState());
        simpMessagingTemplate.convertAndSend("/topic/state", temp);
    }

    public WebSocketTaskImpl(String taskName, SimpMessagingTemplate simpMessagingTemplate) {
        this.taskName = taskName;
        this.simpMessagingTemplate = simpMessagingTemplate;
        sendProgress();
    }
}
