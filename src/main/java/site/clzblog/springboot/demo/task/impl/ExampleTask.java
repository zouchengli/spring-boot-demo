package site.clzblog.springboot.demo.task.impl;

import site.clzblog.springboot.demo.task.DetailedTask;

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
        for (double i = 0.0; i <= loops; i++) {
            try {
                Thread.sleep(1000);
                Thread.sleep(random.nextInt(5000));
                progress.set((int) ((i / loops) * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        state = "COMPLETE";
    }

    public ExampleTask(String taskName) {
        this.taskName = taskName;
    }
}
