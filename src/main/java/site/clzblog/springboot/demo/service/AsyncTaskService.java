package site.clzblog.springboot.demo.service;

/**
 * @Description
 * @Author chengli.zou
 * @CreateDate 2018-04-22 20:46
 **/
public interface AsyncTaskService {
    void doTask(Runnable runnable);
}
