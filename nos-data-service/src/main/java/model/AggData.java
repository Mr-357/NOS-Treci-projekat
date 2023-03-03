package model;

import java.util.Date;

public class AggData {
    private int data;
    private Date start;
    private Date endTime;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
