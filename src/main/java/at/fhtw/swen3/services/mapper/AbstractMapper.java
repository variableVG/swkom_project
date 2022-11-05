package at.fhtw.swen3.services.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractMapper<S, T> implements Mapper<S, T> {

    public List<T> mapToTarget(Collection<S> objects) {
        List<T> targets = new ArrayList<>();
        objects.forEach(o -> targets.add(mapToTarget(o)));
        return targets;
    }

    public List<S> mapToSource(Collection<T> objects) {
        List<S> sources = new ArrayList<>();
        objects.forEach(o -> sources.add(mapToSource(o)));
        return sources;
    }
}
