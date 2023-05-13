package com.driver;

import java.util.ArrayList;
import java.util.Date;

class Mail {
    private Date date;
    private String sender;
    private String message;
    Mail(Date date, String sender, String message) {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    ArrayList<Mail> inbox;
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    ArrayList<Mail> trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        inbox = new ArrayList<>();
        trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inbox.size() == inboxCapacity) {
            trash.add(inbox.get(0));
            inbox.remove(0);
        }

        Mail mail = new Mail(date, sender, message);
        inbox.add(mail);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        int reqMailIdx = -1, curIdx = 0;
        for(Mail mail : inbox) {
            if(mail.getMessage().equals(message)){
                reqMailIdx = curIdx;
                break;
            }
            curIdx++;
        }

        if(reqMailIdx != -1) {
            trash.add(inbox.get(reqMailIdx));
            inbox.remove(reqMailIdx);
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.size() == 0) return null;
        return inbox.get(inbox.size()-1).getMessage();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.size() == 0) return null;
        return inbox.get(0).getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int totalMails = 0;
        for(Mail mail : inbox) {
            Date date = mail.getDate();
            if(date.compareTo(start) >= 0 && date.compareTo(end) <= 0) {
                totalMails++;
            }
        }
        return totalMails;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash = new ArrayList<>();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
