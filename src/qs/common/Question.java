package qs.common;
import java.sql.*;
public class Question {

    private Long id;
    private Long userId;
    private Long frequency;
    private Long type;
    private String content;
    private Timestamp time;
    private String label;
    private String title;

    public Long getId(){ return id; }
    public Long getUserID() { return userId; }
    public Long getFrequency() { return frequency; }
    public Long getType() { return type; }
    public String getContent() { return content; }
    public Timestamp getTime() { return time; }
    public String getLabel() { return label; }
    public String getTitle() { return title; }

    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setFrequency(Long frequency) { this.frequency = frequency; }
    public void setType(Long type) { this.type = type; }
    public void setContent(String content) {this.content = content; }
    public void setTime(Timestamp time) { this.time = time; }
    public void setLabel(String label) { this.label = label; }
    public void setTitle(String title) { this.title = title; }

}
