package com.kevin.activitytracker.Data;

import com.kevin.activitytracker.Model.Activity;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private static final String[] FIELD_NAMES = new String[] { "id", "activity_type", "activity_date", "favorite", "title",
            "distance", "calories", "activity_time", "avg_hr", "max_hr", "aerobic_teE", "avg_run_cadence",
            "max_run_cadence", "avg_speed", "max_speed", "elev_gain", "elev_loss", "avg_stride_length",
            "avg_vertical_ratio", "avg_vertical_oscillation", "avg_bike_cadence", "max_bike_cadence",
            "training_stress_score", "grit", "flow", "total_strokes", "total_reps", "total_sets", "dive_time",
            "min_temp", "surface_interval", "decompression", "best_lap_time", "number_of_laps", "max_temp",
            "moving_time", "elapsed_time", "min_elevation", "max_elevation" };

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<InputActivity> reader() {
        return new FlatFileItemReaderBuilder<InputActivity>().name("activityItemReader")
                .resource(new ClassPathResource("GarminActivities.csv")).delimited().names(FIELD_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<InputActivity>() {
                    {
                        setTargetType(InputActivity.class);
                    }
                }).build();
    }

    @Bean
    public ActivityItemProcessor processor() {
        return new ActivityItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Activity> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Activity>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO activity (id, activity_type, activity_name, time, distance ) VALUES (:id, :activityType, :activityName, :time, :distance)")
                .dataSource(dataSource).build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1)
                .end().build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Activity> writer) {
        return stepBuilderFactory.get("step1")
            .<InputActivity, Activity>chunk(10)
            .reader(reader())
            .processor(processor())
            .writer(writer)
            .build();
    }

}
