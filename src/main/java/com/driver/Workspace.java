package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int size = calendar.size();
        LocalTime[][] arr = new LocalTime[2][size];
        int idx = 0;
        for(Meeting meeting : calendar) {
            arr[idx][0] = meeting.getStartTime();
            arr[idx][1] = meeting.getEndTime();
            idx++;
        }

        Arrays.sort(arr, Comparator.comparing(o -> o[1]));
        int maxMeetings = 1;
        idx = 1;
        LocalTime endTime = arr[0][1];
        while(idx < arr.length) {
            LocalTime startTime = arr[idx][0];
            if(startTime.isAfter(endTime)) {
                endTime = startTime;
                maxMeetings++;
            }
            idx++;
        }
        return maxMeetings;
    }
}
