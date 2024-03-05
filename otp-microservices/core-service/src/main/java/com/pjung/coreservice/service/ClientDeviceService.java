package com.pjung.coreservice.service;

import com.pjung.coreservice.dto.ClientDTO;
import com.pjung.coreservice.dto.ClientDeviceDTO;
import com.pjung.coreservice.model.Client;
import com.pjung.coreservice.model.ClientDevice;
import com.pjung.coreservice.repository.ClientDeviceRepository;
import com.pjung.coreservice.service.builder.ClientDeviceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientDeviceService {

    private final ClientDeviceBuilder clientDeviceBuilder;

    @Autowired
    public ClientDeviceService(ClientDeviceBuilder clientDeviceBuilder) {
        this.clientDeviceBuilder = clientDeviceBuilder;
    }

}
