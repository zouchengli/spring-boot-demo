package site.clzblog.springboot.demo.model;

/**
 * @Description
 * @Author chengli.zou
 * @CreateDate 2018-04-22 19:59
 **/
public class TaskProgressMessage {
    private String taskName;
    private String state;
    private int progress;

    public TaskProgressMessage(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
