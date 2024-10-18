CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE Sensor(
                       id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
                       name varchar not null,
                       created_at timestamp

)

CREATE TABLE Measurement(
                            id  INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                            value float not null,
                            isRaining boolean not null,
                            sensor_id UUID REFERENCES Sensor(id),
                            reated_at timestamp
)


SELECT * FROM Sensor
SELECT * FROM Measurement

