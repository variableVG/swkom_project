package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.RecipientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RecipientImpl implements RecipientService {
    private final RecipientRepository repo;

    public RecipientImpl(RecipientRepository repo) {

        this.repo = repo;

    }
}
