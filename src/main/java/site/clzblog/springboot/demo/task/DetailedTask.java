package site.clzblog.springboot.demo.task;

/**
 * @Description
 * @Author chengli.zou
 * @CreateDate 2018-04-22 19:46
 **/
public interface DetailedTask extends Runnable {
    int getProgress();

    String getState();

    String getTaskName();
}
