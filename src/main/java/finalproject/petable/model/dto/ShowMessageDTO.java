package finalproject.petable.model.dto;

import finalproject.petable.model.entity.Message;

import java.time.LocalDate;

public class ShowMessageDTO {

    private String senderFullName;

    private String subject;

    private String body;

    private LocalDate date;

    public ShowMessageDTO() {
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public void setSenderFullName(String senderFullName) {
        this.senderFullName = senderFullName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
