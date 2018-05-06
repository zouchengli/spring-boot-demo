package site.clzblog.springboot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import site.clzblog.springboot.demo.service.AsyncTaskService;
import site.clzblog.springboot.demo.task.impl.ExampleTask;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author chengli.zou
 * @CreateDate 2018-04-22 20:36
 **/
@RestController
public class PollingController {
    private static int taskCount;
    private List<ExampleTask> taskList = new ArrayList<>(5);

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private AsyncTaskService asyncTaskService;

    @GetMapping("/status")
    public List<ExampleTask> fetchStatus() {
        return taskList;
    }

    @PostMapping("/trigger")
    public void executeTask() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Start execute task" + (i + 1));
            ExampleTask task = new ExampleTask("Task-" + taskCount);
            taskCount += 1;
            taskList.add(task);
            asyncTaskService.doTask(task);
            System.out.println("End execute task" + (i + 1));
        }
    }

    @PostMapping("/poolsize/{size}")
    public void setPoolSize(@PathVariable("size")int size) {
        taskExecutor.setCorePoolSize(size);
    }
}
