package com.task.nyl.studentManagement.app.service;

import com.task.nyl.studentManagement.app.dto.AddressDTO;
import com.task.nyl.studentManagement.app.entity.AddressEntity;
import com.task.nyl.studentManagement.app.entity.StudentEntity;
import com.task.nyl.studentManagement.app.exceptions.UserServiceException;
import com.task.nyl.studentManagement.app.repo.AddressRepo;
import com.task.nyl.studentManagement.app.repo.StudentRepo;
import com.task.nyl.studentManagement.app.responce.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    AddressRepo addressRepo;

    @Override
    public List<AddressDTO> getAddressesByUSer(String id) {
        ModelMapper modelMapper = new ModelMapper();
        List<AddressDTO> returnValue = new ArrayList<>();
        StudentEntity studentEntity = studentRepo.findStudentByStudentId(id);
        if(studentEntity ==null) throw new UserServiceException(ErrorMessages.UNABLE_TO_FIND_ADDRESS.getErrorMessage());
        Iterable<AddressEntity> addresses = addressRepo.findAllByStudentEntity(studentEntity);
        for(AddressEntity entity:addresses){
            returnValue.add(modelMapper.map(entity, AddressDTO.class));
        }
         return returnValue;
    }

    @Override
    public AddressDTO getUserAddress(String id) {
        ModelMapper modelMapper = new ModelMapper();
        AddressDTO returnValue =  new AddressDTO();
        AddressEntity userAddress = addressRepo.findByAddressId(id);
        if(userAddress ==null) throw new UserServiceException(ErrorMessages.UNABLE_TO_FIND_ADDRESS.getErrorMessage());
        returnValue=modelMapper.map(userAddress,AddressDTO.class);
        return returnValue;

    }

}
