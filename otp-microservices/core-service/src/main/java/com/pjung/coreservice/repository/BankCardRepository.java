package com.pjung.coreservice.repository;

import com.pjung.coreservice.model.ClientBankCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCardRepository extends JpaRepository<ClientBankCard, Long> {

    ClientBankCard getClientBankCardById (Long id);
}
