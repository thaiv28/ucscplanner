package com.thaiv.ucscplanner.repositories;

import org.springframework.data.repository.CrudRepository;

import com.thaiv.ucscplanner.models.Course;

public interface CourseRepository extends CrudRepository<Course, String> {

}