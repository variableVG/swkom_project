package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.repository.ParcelRepository;
import at.fhtw.swen3.persistence.repository.RecipientRepository;
import at.fhtw.swen3.services.RecipientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RecipientImpl extends RecipientService {
    private final RecipientRepository repo;

    public RecipientImpl(RecipientRepository repo) {

        this.repo = repo;
    }
}
