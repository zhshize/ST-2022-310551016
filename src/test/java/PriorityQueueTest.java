import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {

    static Stream<Arguments> streamProvider()
    {
        return Stream.of(
                Arguments.of(new int[] {1, 6, 8}, new int[] {1, 6, 8}),
                Arguments.of(new int[] {99, 98, 97, 96, 95}, new int[] {95, 96, 97, 98, 99}),
                Arguments.of(new int[] {-401, -999, 0, 752}, new int[] {-999, -401, 0, 752}),
                Arguments.of(new int[] {0, 0, -7, 3}, new int[] {-7, 0, 0, 3}),
                Arguments.of(new int[] {9}, new int[] {9})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array) {
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int index = 0;
        Integer s;
        int[] result = new int[random_array.length];

        for (int n : random_array) {
            test.add(n);
        }

        for (int i = 0; i < random_array.length; i++) {
            result[i] = test.poll();
        }

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void whenExceptionThrown_thenToArrayBadType()
    {
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        test.add(5);
        assertThrows(ArrayStoreException.class, () -> {
            test.toArray(new String[0]);
        });
    }

    @Test
    public void whenExceptionThrown_thenAddNull()
    {
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        test.add(5);
        assertThrows(NullPointerException.class, () -> {
            test.add(null);
        });
    }

    @Test
    public void whenExceptionThrown_thenAddBadTypeObject()
    {
        PriorityQueue<Object> test = new PriorityQueue<Object>();
        test.add(5);
        assertThrows(ClassCastException.class, () -> {
            test.add("999");
        });
    }
}
