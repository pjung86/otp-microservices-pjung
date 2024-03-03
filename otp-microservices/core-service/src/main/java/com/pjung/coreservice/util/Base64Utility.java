package com.pjung.coreservice.util;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class Base64Utility {

    public String Base64Encoder(String data) {
        String encondedString = Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
        return encondedString;
    }

    public String Base64Decoder (String data) {
        byte[] byteArray = Base64.getDecoder().decode(data);
        String decodedString = new String(byteArray);
        return decodedString;
    }
}
