package study.cxm.com.sousuolist;


import java.io.Serializable;

public class ItemModel  implements Serializable {
    private String taskName;
    private String taskDate;
    private String taskState;

    public ItemModel() {
    }

    public ItemModel(String taskName, String taskDate, String taskState) {
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.taskState = taskState;
    }

    public String getTaskName() {
        return taskName == null ? "" : taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDate() {
        return taskDate == null ? "" : taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskState() {
        return taskState == null ? "" : taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }
}