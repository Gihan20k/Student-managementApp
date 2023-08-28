package com.task.nyl.studentManagement.app.service;

import com.task.nyl.studentManagement.app.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> getAddressesByUSer(String id);
    AddressDTO getUserAddress(String id);
}
