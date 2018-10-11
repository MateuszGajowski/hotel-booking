package pl.gajowski.mateusz.hotelbooking.config.mapper;

@FunctionalInterface
public interface BaseMapper<T, F> {
    F convert(T from);
}
