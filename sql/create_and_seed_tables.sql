DROP TABLE IF EXISTS activities;
DROP TABLE IF EXISTS roles;

CREATE TABLE IF NOT EXISTS activities (
    id serial primary key,
    activity_type varchar,
    date date,
    title varchar,
    distance varchar,
    calories varchar,
    time varchar,
    avg_hr varchar,
    max_hr varchar,
    aerobic_te varchar,
    avg_run_cadence varchar,
    max_run_cadence varchar,
    avg_speed varchar,
    max_speed varchar,
    elev_gain varchar,
    elev_loss varchar,
    avg_stride_length varchar,
    avg_bike_cadence varchar,
    max_bike_cadence varchar,
    total_reps varchar,
    total_sets varchar,
    min_temp varchar,
    best_lap_time varchar,
    number_of_laps varchar,
    max_temp varchar,
    moving_time varchar,
    elapsed_time varchar,
    min_elevation varchar,
    max_elevation varchar
);

CREATE TABLE IF NOT EXISTS roles (
    id serial primary key not null,
    name varchar
);


INSERT INTO roles("name") VALUES('ROLE_USER');
INSERT INTO roles("name") VALUES('ROLE_MODERATOR');
INSERT INTO roles("name") VALUES('ROLE_ADMIN');

COPY activities(activity_type,date,title,distance,calories,time,avg_hr,max_hr,aerobic_te,avg_run_cadence,max_run_cadence,avg_speed,max_speed,elev_gain,elev_loss,avg_stride_length,avg_bike_cadence,max_bike_cadence,total_reps,total_sets,min_temp,best_lap_time,number_of_laps,max_temp,moving_time,elapsed_time ,min_elevation,max_elevation) FROM '/var/lib/postgresql/activity-data/GarminActivities.csv' DELIMITER ',' CSV HEADER;