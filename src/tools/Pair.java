package tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("ClassCanBeRecord")
public class Pair<T, U> {

    private static final Map<Object, Map<Object, Pair<Object, Object>>> pairs = new HashMap<>();

    private final T item1;
    private final U item2;

    private Pair(T item1, U item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public T getItem1() { return item1; }
    public U getItem2() { return item2; }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Pair<?, ?> pair))
            return false;
        return item1.equals(pair.item1) && item2.equals(pair.item2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item1, item2);
    }

    @SuppressWarnings("unchecked")
    public static <T, U> Pair<T, U> of(T item1, U item2) {
        Pair<Object, Object> pair;
        Map<Object, Pair<Object, Object>> pairsWithItem1 = pairs.computeIfAbsent(item1, k -> new HashMap<>());
        pair = pairsWithItem1.get(item2);
        if (pair == null) {
            pair = new Pair<>(item1, item2);
            pairsWithItem1.put(item2, pair);
        }
        return (Pair<T, U>) pair;
    }
}
