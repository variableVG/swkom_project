package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.repository.GeoCoordinateRepository;
import at.fhtw.swen3.services.GeoCoordinateService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class GeoCoordinateImpl implements GeoCoordinateService {

    GeoCoordinateRepository repo;

    public GeoCoordinateImpl() {
        this.repo = new GeoCoordinateRepository() {
            @Override
            public void submitGeoCoordinate(Double lat, Double lon) {

            }

            @Override
            public GeoCoordinateEntity getGeoCoordinateEntityById(long id) {
                return null;
            }

            @Override
            public List<GeoCoordinateEntity> findAll() {
                return null;
            }

            @Override
            public List<GeoCoordinateEntity> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<GeoCoordinateEntity> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public <S extends GeoCoordinateEntity> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends GeoCoordinateEntity> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends GeoCoordinateEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<GeoCoordinateEntity> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public GeoCoordinateEntity getOne(Long aLong) {
                return null;
            }

            @Override
            public GeoCoordinateEntity getById(Long aLong) {
                return null;
            }

            @Override
            public GeoCoordinateEntity getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends GeoCoordinateEntity> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends GeoCoordinateEntity> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<GeoCoordinateEntity> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends GeoCoordinateEntity> S save(S entity) {
                return null;
            }

            @Override
            public Optional<GeoCoordinateEntity> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(GeoCoordinateEntity entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends GeoCoordinateEntity> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends GeoCoordinateEntity> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends GeoCoordinateEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends GeoCoordinateEntity> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends GeoCoordinateEntity> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends GeoCoordinateEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
    }



    @Override
    public void submitGeoCoordinate(Double lat, Double lon) {
         repo.submitGeoCoordinate(lat, lon);
    }
}
