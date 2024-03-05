package com.pjung.coreservice.service.builder;

import com.pjung.coreservice.dto.ClientDTO;
import com.pjung.coreservice.dto.ClientDeviceDTO;
import com.pjung.coreservice.model.Client;
import com.pjung.coreservice.model.ClientDevice;
import com.pjung.coreservice.repository.ClientDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientDeviceBuilder {

    private final ClientDeviceRepository clientDeviceRepository;

    @Autowired
    public ClientDeviceBuilder(ClientDeviceRepository clientDeviceRepository) {
        this.clientDeviceRepository = clientDeviceRepository;
    }

    public ClientDevice clientDeviceBuilder (ClientDeviceDTO clientDeviceDTO){
        ClientDevice clientDevice =  ClientDevice.builder()
                .deviceHash(clientDeviceDTO.deviceHash())
                .client(clientDeviceDTO.client())
                .build();
        clientDeviceRepository.save(clientDevice);
        return clientDevice;
    }
}
