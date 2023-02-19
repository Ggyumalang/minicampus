package com.minicampus.minicampus.course.repository;

import com.minicampus.minicampus.course.entity.Course;
import com.minicampus.minicampus.course.model.CourseParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCategoryId(Long categoryId);
}
