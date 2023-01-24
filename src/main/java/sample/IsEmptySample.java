package sample;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class IsEmptySample {
    public static void main(String[] args) {

        List<Optional<String>> optList = List.of(Optional.of("1"), Optional.empty(), Optional.of("3"));

        // ラムダで判定
        long nullCount = optList.stream()
                .filter(v -> !v.isPresent())
                .count();
        System.out.println(nullCount);

        // メソッド参照で判定
        nullCount = optList.stream()
            .filter(Optional::isEmpty)
            .count();
        System.out.println(nullCount);


        // Predicate.notで判定
        nullCount = optList.stream()
                .filter(Predicate.not(Optional::isPresent))
                .count();
        System.out.println(nullCount);
    }
}
