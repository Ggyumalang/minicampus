package com.minicampus.minicampus.course.repository;

import com.minicampus.minicampus.course.entity.TakeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TakeCourseRepository extends JpaRepository<TakeCourse, Long> {

    Long countByCourseIdAndUserIdAndStatusIn(Long courseId, String userId, Collection<String> statusList);
}
