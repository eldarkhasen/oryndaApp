package com.example.android.orynda.DB;

/**
 * Created by админ on 22.07.2017.
 */

public class Tasks {
    public Long id;
    public String title;
    public String description;
    public String deadline;
    public String reward;
    public String punishment;
    public boolean reminder;
    public boolean completed;
    public boolean success;

    public Tasks() {
    }

    public Tasks(Long id, String title, String description, String deadline, String reward, String punishment, boolean reminder, boolean completed, boolean success) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.reward = reward;
        this.punishment = punishment;
        this.reminder = reminder;
        this.completed = completed;
        this.success = success;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public boolean isReminder() {
        return reminder;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
