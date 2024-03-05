package com.pjung.coreservice.dto;

import com.pjung.coreservice.model.Client;

public record ClientTokenDTO(String token, Client client) {
}
