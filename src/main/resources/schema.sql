CREATE TABLE users (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    age INT,
    gender VARCHAR(50),
    weight DOUBLE,
    height DOUBLE
);

CREATE TABLE activity (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    userId LONG,
    activityType VARCHAR(255),
    duration INT,
    caloriesBurned DOUBLE,
    activityDate TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES users(id)
);