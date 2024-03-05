package com.pjung.coreservice.repository;

import com.pjung.coreservice.model.ClientDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDeviceRepository extends JpaRepository<ClientDevice, Long> {

    ClientDevice getClientDeviceById(Long Id);
}
