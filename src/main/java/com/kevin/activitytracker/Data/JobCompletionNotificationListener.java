package com.kevin.activitytracker.Data;

// import java.time.Duration;

import com.kevin.activitytracker.Model.Activity;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

//   private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
    //   log.info("!!! JOB FINISHED! Time to verify the results");

      jdbcTemplate.query("SELECT Activity_Type, Title, Activity_Time  FROM activity",
        (rs, row) -> new Activity(
          rs.getString(1),
          rs.getString(2),
          rs.getString(3))
      ).forEach(activity -> System.out.println("Found <" + activity + "> in the database."));
    }
  }
}