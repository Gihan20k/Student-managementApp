package com.task.nyl.studentManagement.app.repo;

import com.task.nyl.studentManagement.app.entity.StudentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends PagingAndSortingRepository<StudentEntity, Long> {
    StudentEntity findStudentByEmail(String email);
    StudentEntity findStudentByStudentId(String studentId);
}
