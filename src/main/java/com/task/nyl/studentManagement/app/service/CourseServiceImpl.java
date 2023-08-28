package com.task.nyl.studentManagement.app.service;


import com.task.nyl.studentManagement.app.dto.CourseDTO;
import com.task.nyl.studentManagement.app.entity.CourseEntity;
import com.task.nyl.studentManagement.app.entity.StudentEntity;
import com.task.nyl.studentManagement.app.exceptions.UserServiceException;
import com.task.nyl.studentManagement.app.repo.CourseRepo;
import com.task.nyl.studentManagement.app.repo.StudentRepo;
import com.task.nyl.studentManagement.app.responce.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    private StudentRepo studentRepo;
    private CourseRepo courseRepo;

    public CourseServiceImpl(StudentRepo studentRepo, CourseRepo courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public List<CourseDTO> getCourseByStudent(String id) {
        ModelMapper modelMapper = new ModelMapper();
        List<CourseDTO> returnValue = new ArrayList<>();
        StudentEntity studentEntity = studentRepo.findStudentByStudentId(id);
        if(studentEntity ==null)throw new UserServiceException(ErrorMessages.UNABLE_TO_FIND_COURSES.getErrorMessage());
        Iterable<CourseEntity> courses = courseRepo.findAllByStudentEntity(studentEntity);
        for(CourseEntity entity:courses){
            returnValue.add(modelMapper.map(entity, CourseDTO.class));
        }
        return returnValue;
    }
}
