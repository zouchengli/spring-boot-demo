package site.clzblog.springboot.demo.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import site.clzblog.springboot.demo.service.AsyncTaskService;

/**
 * @Description
 * @Author chengli.zou
 * @CreateDate 2018-04-22 20:47
 **/
@Service
public class AsyncTaskServiceImpl implements AsyncTaskService {

    @Async
    @Override
    public void doTask(Runnable runnable) {
        System.out.println("Runnable " + runnable);
        runnable.run();
    }
}
