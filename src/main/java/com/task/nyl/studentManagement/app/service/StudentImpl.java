package com.task.nyl.studentManagement.app.service;

import com.task.nyl.studentManagement.app.Utils.Utils;
import com.task.nyl.studentManagement.app.dto.AddressDTO;
import com.task.nyl.studentManagement.app.dto.CourseDTO;
import com.task.nyl.studentManagement.app.dto.StudentDTO;
import com.task.nyl.studentManagement.app.entity.StudentEntity;
import com.task.nyl.studentManagement.app.exceptions.UserServiceException;
import com.task.nyl.studentManagement.app.repo.StudentRepo;
import com.task.nyl.studentManagement.app.responce.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentImpl implements StudentService {

    private StudentRepo studentRepo;
    private Utils utils;

    public StudentImpl(StudentRepo studentRepo, Utils utils) {
        this.studentRepo = studentRepo;
        this.utils = utils;
    }


    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {

        for (int i = 0; i < studentDTO.getAddresses().size(); i++) {
            AddressDTO address = studentDTO.getAddresses().get(i);
            address.setStudentEntity(studentDTO);
            address.setAddressId(utils.generateAddressId(30));
            studentDTO.getAddresses().set(i, address);
        }
        for (int i = 0; i < studentDTO.getCourses().size(); i++) {
            CourseDTO courses = studentDTO.getCourses().get(i);
            courses.setStudentEntity(studentDTO);
            courses.setCourseId(utils.generateCourseId(30));
            studentDTO.getCourses().set(i, courses);
        }
        ModelMapper modelMapper = new ModelMapper();
        StudentEntity studentEntity = modelMapper.map(studentDTO, StudentEntity.class);
        String publicStudentId = utils.generateStudentId(30);
        studentEntity.setStudentId(publicStudentId);
        StudentEntity storeUser = studentRepo.save(studentEntity);
        StudentDTO returnValue = modelMapper.map(storeUser, StudentDTO.class);
        return returnValue;
    }

    @Override
    public StudentDTO getStudentByEmail(String email) {
        StudentEntity studentEntity = studentRepo.findStudentByEmail(email);
        if (studentEntity == null) throw new UserServiceException(ErrorMessages.UNABLE_TO_FIND_STUDENT_BY_EMAIL.getErrorMessage());
        StudentDTO returnValue = new StudentDTO();
        BeanUtils.copyProperties(studentEntity, returnValue);
        return returnValue;
    }

    @Override
    public StudentDTO getStudentByStudentId(String StudentId) {
        StudentEntity studentEntity = studentRepo.findStudentByStudentId(StudentId);
       if (studentEntity == null) throw new UserServiceException(ErrorMessages.UNABLE_TO_FIND_STUDENT_BY_EMAIL.getErrorMessage());
        StudentDTO returnValue = new StudentDTO();
        BeanUtils.copyProperties(studentEntity, returnValue);
        return returnValue;
    }

    @Override
    public StudentDTO updateStudent(String StudentId, StudentDTO studentDTO) {
        StudentDTO returnValue = new StudentDTO();
        StudentEntity studentEntity = studentRepo.findStudentByStudentId(StudentId);
       if (studentEntity == null) throw new UserServiceException(ErrorMessages.UNABLE_TO_FIND_USER.getErrorMessage());
        studentEntity.setFirstName(studentDTO.getFirstName());
        studentEntity.setLastName(studentDTO.getLastName());
        StudentEntity updatedValue = studentRepo.save(studentEntity);
        BeanUtils.copyProperties(updatedValue, returnValue);
        return returnValue;
    }

    @Override
    public void deleteStudent(String studentId) {
        StudentEntity studentEntity = studentRepo.findStudentByStudentId(studentId);
        if (studentEntity == null) throw new UserServiceException(ErrorMessages.UNABLE_TO_FIND_USER.getErrorMessage());
        studentRepo.delete(studentEntity);
    }

    @Override
    public List<StudentDTO> getStudents(int page, int limit) {
        List<StudentDTO> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<StudentEntity> userPage = studentRepo.findAll(pageableRequest);
        List<StudentEntity> users = userPage.getContent();

        for (StudentEntity studentEntity : users) {
            StudentDTO studentDTO = new StudentDTO();
            BeanUtils.copyProperties(studentEntity, studentDTO);
            returnValue.add(studentDTO);
        }
        return returnValue;
    }

}
