CREATE TABLE IF NOT EXISTS task(
    task_id SERIAL PRIMARY KEY,
    task_name VARCHAR(255) NOT NULL UNIQUE,
    is_completed BOOLEAN NOT NULL DEFAULT false
);
--CREATE TABLE task(
--    task_id SERIAL,
--    task_name VARCHAR(255),
--    is_completed BOOLEAN
--);