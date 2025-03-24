package fitnesstracker.util.mapper;

public interface Mapper<X,Y,Z> {
    X toEntity(Y dtoRequest);
    Z toDtoResponse(X entity);
}
