package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.impl.HopArrivalImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HopArrivalRepositoryTest {

    @Autowired
    HopArrivalRepository repo;

    HopArrivalEntity hopArrivalEntity;

    @Test
    void setUp() {
        hopArrivalEntity = HopArrivalEntity.builder().build();
    }

    @Test
    void saveTest() {

    }


}