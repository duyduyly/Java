package lambdas_streams.streams;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

public class PrimitiveStreamDemo {
    public static void main(String[] args) {
        var intStream = IntStream.of(7, 2, -4, 11, 27);
        IntSummaryStatistics stats = intStream.summaryStatistics();
        System.out.println(stats.getCount());
        System.out.println(stats.getAverage());
        System.out.println(stats.getSum());
        System.out.println(stats.getMin());
        System.out.println(stats.getMax());
    }
}
