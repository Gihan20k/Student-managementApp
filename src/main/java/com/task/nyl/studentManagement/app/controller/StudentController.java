package com.task.nyl.studentManagement.app.controller;

import com.task.nyl.studentManagement.app.dto.AddressDTO;
import com.task.nyl.studentManagement.app.dto.CourseDTO;
import com.task.nyl.studentManagement.app.dto.StudentDTO;
import com.task.nyl.studentManagement.app.exceptions.UserServiceException;
import com.task.nyl.studentManagement.app.model.StudentDetailModel;
import com.task.nyl.studentManagement.app.service.AddressServiceImpl;
import com.task.nyl.studentManagement.app.service.CourseServiceImpl;
import com.task.nyl.studentManagement.app.service.StudentImpl;
import com.task.nyl.studentManagement.app.responce.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("student")
public class StudentController {


    Logger log = LoggerFactory.getLogger(StudentController.class);

    private StudentImpl studentImpl;
    private AddressServiceImpl addressService;
    private CourseServiceImpl courseService;

    public StudentController(StudentImpl studentImpl, StudentImpl userImpl, AddressServiceImpl addressService, CourseServiceImpl courseService) {
        this.studentImpl = studentImpl;
        this.studentImpl = userImpl;
        this.addressService = addressService;
        this.courseService = courseService;
    }


    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public StudentResponse createStudent(@RequestBody StudentDetailModel userDetail) throws Exception {
        if (userDetail.getEmail().isEmpty() || userDetail.getFirstName().isEmpty() || userDetail.getLastName().isEmpty())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        ModelMapper modelMapper = new ModelMapper();
        StudentDTO studentDTO = modelMapper.map(userDetail, StudentDTO.class);
        StudentDTO createUser = studentImpl.createStudent(studentDTO);
        StudentResponse returnValue = modelMapper.map(createUser, StudentResponse.class);
        //BeanUtils.copyProperties(createUser, returnValue);
        return returnValue;
    }


    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public StudentResponse getStudent(@PathVariable String id) {
        StudentResponse returnValue = new StudentResponse();
        StudentDTO getUser = studentImpl.getStudentByStudentId(id);
        BeanUtils.copyProperties(getUser, returnValue);
        return returnValue;
    }


    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public StudentResponse updateStudent(@PathVariable String id, @RequestBody StudentDetailModel userDetail) {
        StudentResponse returnValue = new StudentResponse();
        StudentDTO studentDTO = new StudentDTO();
        BeanUtils.copyProperties(userDetail, studentDTO);
        StudentDTO updateUser = studentImpl.updateStudent(id, studentDTO);
        BeanUtils.copyProperties(updateUser, returnValue);
        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatus deleteStudent(@PathVariable String id) {
        OperationStatus returnValue = new OperationStatus();
        returnValue.setOperationName(RequestOperationName.DELETE.name());
        studentImpl.deleteStudent(id);
        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<StudentResponse> getStudent(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<StudentResponse> returnValue = new ArrayList<>();
        List<StudentDTO> getUsers = studentImpl.getStudents(page, limit);
        for (StudentDTO studentDTO : getUsers) {
            StudentResponse response = new StudentResponse();
            BeanUtils.copyProperties(studentDTO, response);
            returnValue.add(response);
        }
        return returnValue;

    }

    @GetMapping(path = "/{id}/addresses")
    public List<AddressResponse> getStudentAddresses(@PathVariable String id) {

        List<AddressResponse> returnValue = new ArrayList<>();
        List<AddressDTO> addressDTO = addressService.getAddressesByUSer(id);
        if (addressDTO != null && !addressDTO.isEmpty()) {
            java.lang.reflect.Type listType = new TypeToken<List<AddressResponse>>() {
            }.getType();
            returnValue = new ModelMapper().map(addressDTO, listType);
        }
        return returnValue;
    }

    @GetMapping(path = "/{id}/courses")
    public List<CourseResponse> getStudentCourses(@PathVariable String id) {
        List<CourseResponse> returnValue = new ArrayList<>();
        List<CourseDTO> courseDTO = courseService.getCourseByStudent(id);
        if (courseDTO != null && !courseDTO.isEmpty()) {
            java.lang.reflect.Type listType = new TypeToken<List<AddressResponse>>() {
            }.getType();
            returnValue = new ModelMapper().map(courseDTO, listType);
        }
        return returnValue;
    }

    @GetMapping(path = "/addresses/{addressID}/{StudentID}")
    public AddressResponse getStudentAddress(@PathVariable String addressID, @PathVariable String StudentID) {
        AddressResponse returnValue = new AddressResponse();
        ModelMapper modelMapper = new ModelMapper();
        AddressDTO addressDTO = addressService.getUserAddress(addressID);
        returnValue = modelMapper.map(addressDTO, AddressResponse.class);
        Link StudentLink = WebMvcLinkBuilder.linkTo(StudentController.class).slash(StudentID).withRel("student");
        Link userAddress = WebMvcLinkBuilder.linkTo(StudentController.class)
                .slash(StudentID)
                .slash("addresses")
                .withRel("userAddresses");
        returnValue.add(StudentLink);
        returnValue.add(userAddress);
        return returnValue;
    }

}
