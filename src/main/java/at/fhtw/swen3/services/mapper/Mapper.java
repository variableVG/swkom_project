package at.fhtw.swen3.services.mapper;

public interface Mapper<S, T> {

    T mapToTarget(S object);
    S mapToSource(T object);

}
