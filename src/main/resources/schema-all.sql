DROP TABLE activity IF EXISTS;

CREATE TABLE activity  (
    Activity_Date VARCHAR(100) PRIMARY KEY,
    Activity_Type VARCHAR(150),
    Title VARCHAR(150),
    Activity_Time VARCHAR(150)
);