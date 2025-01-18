-- TODO: ALTER TABLE command below is not idempotent

-- SELECT query
SELECT BIN_TO_UUID(id) AS id, first_name FROM students WHERE id = UUID_TO_BIN('0fab1a6b-da72-47eb-a90c-38c936ab2e24');

-- Create the 'students' table for storing basic student information
CREATE TABLE IF NOT EXISTS students (
	id binary(16) default (uuid_to_bin(uuid())) NOT NULL PRIMARY KEY,
    -- student_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    -- middle_name VARCHAR(100) NOT NULL,
    -- last_name VARCHAR(100) NOT NULL,
    -- email VARCHAR(150) UNIQUE NOT NULL,
    phone_number VARCHAR(15),
    date_of_birth DATE,
    -- enrollment_date DATE NOT NULL,
    -- is_alumni BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create the 'alumni_details' table for additional information specific to alumni
CREATE TABLE IF NOT EXISTS alumni_details (
    id binary(16) NOT NULL PRIMARY KEY,
    graduation_year YEAR NOT NULL,
    current_job_title VARCHAR(150),
    current_company VARCHAR(150),
    linkedin_profile VARCHAR(255),
    FOREIGN KEY (id) REFERENCES students(id) ON DELETE CASCADE
);

-- Create the 'courses' table for storing course information
CREATE TABLE IF NOT EXISTS courses (
    id binary(16) default (uuid_to_bin(uuid())) NOT NULL PRIMARY KEY,
    course_name VARCHAR(150) NOT NULL,
    course_code VARCHAR(20) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create the 'student_courses' table for mapping students to courses
CREATE TABLE IF NOT EXISTS student_courses (
	id binary(16) default (uuid_to_bin(uuid())) NOT NULL PRIMARY KEY,
    student_id binary(16) NOT NULL,
    course_id binary(16) NOT NULL,
    enrollment_date DATE NOT NULL,
    grade VARCHAR(5),
    UNIQUE KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- Create the 'departments' table for storing department information
CREATE TABLE IF NOT EXISTS departments (
    id binary(16) default (uuid_to_bin(uuid())) NOT NULL PRIMARY KEY,
    department_name VARCHAR(150) NOT NULL,
    head_of_department VARCHAR(150),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Add a foreign key column to the 'courses' table for department association
ALTER TABLE courses
ADD COLUMN department_id binary(16),
ADD FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE SET NULL;
