package site.clzblog.springboot.demo.task;

import site.clzblog.springboot.demo.model.TaskProgressMessage;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author chengli.zou
 * @CreateDate 2018-04-22 19:43
 **/
public class ExampleTask implements DetailedTask {

    private int loops = 10;
    private AtomicInteger progress = new AtomicInteger();
    private String state = "NEW";
    private String taskName;
    private Random random = new Random();

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
        TaskProgressMessage temp = new TaskProgressMessage(taskName);
        temp.setProgress(progress.get());
        temp.setState(state);
    }

    public ExampleTask(String taskName) {
        this.taskName = taskName;
        sendProgress();
    }
}
