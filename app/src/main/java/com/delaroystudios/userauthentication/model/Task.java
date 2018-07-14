package com.delaroystudios.userauthentication.model;

/**
 * Created by delaroy on 7/14/18.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Task implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("taskdate")
    @Expose
    private String taskdate;
    @SerializedName("tasktime")
    @Expose
    private String tasktime;
    @SerializedName("task")
    @Expose
    private String task;
    public final static Parcelable.Creator<Task> CREATOR = new Creator<Task>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return (new Task[size]);
        }

    };

    protected Task(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.taskdate = ((String) in.readValue((String.class.getClassLoader())));
        this.tasktime = ((String) in.readValue((String.class.getClassLoader())));
        this.task = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Task() {
    }

    /**
     *
     * @param id
     * @param username
     * @param title
     * @param taskdate
     * @param task
     * @param tasktime
     */
    public Task(Integer id, String title, String username, String taskdate, String tasktime, String task) {
        super();
        this.id = id;
        this.title = title;
        this.username = username;
        this.taskdate = taskdate;
        this.tasktime = tasktime;
        this.task = task;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTaskdate() {
        return taskdate;
    }

    public void setTaskdate(String taskdate) {
        this.taskdate = taskdate;
    }

    public String getTasktime() {
        return tasktime;
    }

    public void setTasktime(String tasktime) {
        this.tasktime = tasktime;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(username);
        dest.writeValue(taskdate);
        dest.writeValue(tasktime);
        dest.writeValue(task);
    }

    public int describeContents() {
        return 0;
    }

}
