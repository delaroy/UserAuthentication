package com.delaroystudios.userauthentication.model;

/**
 * Created by delaroy on 7/14/18.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tasks implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("tasks")
    @Expose
    private List<Task> tasks = null;
    public final static Parcelable.Creator<Tasks> CREATOR = new Creator<Tasks>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Tasks createFromParcel(Parcel in) {
            return new Tasks(in);
        }

        public Tasks[] newArray(int size) {
            return (new Tasks[size]);
        }

    }
            ;

    protected Tasks(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.tasks, (com.delaroystudios.userauthentication.model.Task.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Tasks() {
    }

    /**
     *
     * @param error
     * @param tasks
     */
    public Tasks(Boolean error, List<Task> tasks) {
        super();
        this.error = error;
        this.tasks = tasks;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeList(tasks);
    }

    public int describeContents() {
        return 0;
    }

}
