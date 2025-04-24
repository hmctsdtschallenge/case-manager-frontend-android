package com.dts.casemanagerfrontendandroid.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import java.time.LocalDateTime;

public class Task extends BaseObservable implements Parcelable {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String createdDate;
    private String dueDate;
    private static final String TAG = "task";

    public Task(Long id, String title, String description, String status, String createdDate, String dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
    }

    public Task() {
    }

    protected Task(Parcel in) {
        id = in.readLong();
        title = in.readString();
        description = in.readString();
        status = in.readString();
        createdDate = in.readString();
        dueDate = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            Task taskFromParcel = new Task(in);
            Log.i(TAG, "createFromParcel: taskFromParcel: " + taskFromParcel.toString());
            return taskFromParcel;
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Bindable
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyPropertyChanged(BR.status);
    }

    @Bindable
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        notifyPropertyChanged(BR.createdDate);
    }

    @Bindable
    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
        notifyPropertyChanged(BR.dueDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        Log.i(TAG, "writeToParcel: task using the writeToParcel method: " + this.toString());

        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(status);
        dest.writeString(createdDate);
        dest.writeString(dueDate);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}