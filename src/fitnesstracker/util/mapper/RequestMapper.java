package fitnesstracker.util.mapper;

public interface RequestMapper<X,Y> {
    X toEntity(Y requestDto);
}
