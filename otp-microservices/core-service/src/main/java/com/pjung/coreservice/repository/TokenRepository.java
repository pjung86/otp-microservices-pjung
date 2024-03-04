package com.pjung.coreservice.repository;

import com.pjung.coreservice.model.ClientToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<ClientToken, Long> {

    ClientToken getClientTokenByToken(String token);
}
