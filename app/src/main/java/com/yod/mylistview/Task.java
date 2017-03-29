package com.yod.mylistview;

/**
 * 任务工单流程对象
 * 孙立鹏 on 2017/3/29 10:09
 */

public class Task {

    private String date;//日期
    private String step;//当前步骤
    private String initiator;//发起人
    private String exhibitor;//提交人

    public Task(String date, String step, String initiator, String exhibitor) {
        this.date = date;
        this.step = step;
        this.initiator = initiator;
        this.exhibitor = exhibitor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getExhibitor() {
        return exhibitor;
    }

    public void setExhibitor(String exhibitor) {
        this.exhibitor = exhibitor;
    }
}
