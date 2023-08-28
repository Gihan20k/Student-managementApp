package com.task.nyl.studentManagement.app.repo;

import com.task.nyl.studentManagement.app.entity.AddressEntity;
import com.task.nyl.studentManagement.app.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends CrudRepository<AddressEntity, Long> {
    List<AddressEntity> findAllByStudentEntity(StudentEntity studentEntity);
    AddressEntity findByAddressId(String addressId);
}
