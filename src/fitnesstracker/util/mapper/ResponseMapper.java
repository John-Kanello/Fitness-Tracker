package fitnesstracker.util.mapper;

public interface ResponseMapper<X,Y> {
    Y toResponseDto(X entity);
}
