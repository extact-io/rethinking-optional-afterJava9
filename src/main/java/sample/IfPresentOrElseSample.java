package sample;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class IfPresentOrElseSample {
    public void outputValue(String path) {
        fromFilePath(path).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("unknown value"));
    }
    public void outputValue1(String path) {
        Optional<String> value = fromFilePath(path);
        if (value.isPresent()) {
            System.out.println(value.get());
        } else {
            System.out.println("unknown value");
        }
    }
    private Optional<String> fromFilePath(String path) {
        // ex) file:/foo/bar/...
        return path.startsWith("file:") ? Optional.of("file value") : Optional.empty();
    }
    public static void main(String[] args) {
        new IfPresentOrElseSample().outputValue("file:/foo/bar/value");
        new IfPresentOrElseSample().outputValue("net:/foo/bar/value");
    }
}
