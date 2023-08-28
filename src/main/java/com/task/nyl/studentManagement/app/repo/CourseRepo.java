package com.task.nyl.studentManagement.app.repo;

import com.task.nyl.studentManagement.app.entity.AddressEntity;
import com.task.nyl.studentManagement.app.entity.CourseEntity;
import com.task.nyl.studentManagement.app.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepo extends CrudRepository<CourseEntity, Long> {
    List<CourseEntity> findAllByStudentEntity(StudentEntity studentEntity);
    AddressEntity findByCourseId(String addressId);
}
