package ro.fasttrackit.curs13.course.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.curs13.course.model.CourseStudentEntity;

import java.util.List;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudentEntity, Integer> {

    List<CourseStudentEntity> deleteAllByStudentId(Integer studentId);
}
