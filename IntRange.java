import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class represents a range of Integers from a given start value until a given end value excluded.
 *
 * @author 0xflotus
 * @since 18.10.2017
 */
public class IntRange {

    private static final UnaryOperator<Integer> PLUS_ONE = i -> i + 1;

    private List<Integer> list;

    public IntRange(Integer startInclusive, Integer endExclusive) {
        if (!(endExclusive > startInclusive)) throw new IllegalArgumentException("end must be greater than start");
        list = Stream.iterate(startInclusive, integer -> integer < endExclusive, PLUS_ONE).collect(Collectors.toList());
    }

    private IntRange(List<Integer> list) {
        this.list = list;
    }

    public Integer count() {
        return list.size();
    }

    public void forEach(Consumer<Integer> consumer) {
        list.forEach(consumer);
    }

    public IntRange butOnly(Predicate<Integer> predicate) {
        return new IntRange(list.stream().filter(predicate).collect(Collectors.toList()));
    }

    public List<Integer> toList() {
        return list;
    }

    public Integer[] toArray() {
        return list.toArray(new Integer[0]);
    }

    public boolean contains(IntRange subRange) {
        return list.containsAll(subRange.toList());
    }

    public boolean contains(Integer integer) {
        return list.contains(integer);
    }

    public static IntRange between(Integer startInclusive, Integer endExclusive) {
        return new IntRange(startInclusive, endExclusive);
    }
}
