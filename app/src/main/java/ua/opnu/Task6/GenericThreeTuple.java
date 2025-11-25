package ua.opnu.Task6;

public class GenericThreeTuple<T, V, S> extends GenericTwoTuple<T, V> {
    public final S third;

    public GenericThreeTuple(T first, V second, S third) {
        super(first, second);
        this.third = third;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }
}
