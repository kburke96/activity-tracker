package com.kevin.activitytracker.Data;

import org.springframework.batch.item.ItemProcessor;

// import java.time.Duration;

import com.kevin.activitytracker.Model.Activity;

public class ActivityItemProcessor implements ItemProcessor<InputActivity, Activity> {

    @Override
    public Activity process(final InputActivity inputActivity) throws Exception {
        // TODO Auto-generated method stub
        Activity activity = new Activity();

        // activity.setId(inputActivity.getId());
        activity.setActivityType(inputActivity.getActivity_type());
        activity.setActivityName(inputActivity.getTitle());
        activity.setTime(inputActivity.getActivity_time());

        System.out.println("Converting (" + inputActivity + ") into (" + activity + ")");
        return activity;
    }
    
}
