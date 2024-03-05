package com.pjung.coreservice.dto;

import com.pjung.coreservice.model.Client;

public record ClientDeviceDTO(String deviceHash, Client client) {
}
