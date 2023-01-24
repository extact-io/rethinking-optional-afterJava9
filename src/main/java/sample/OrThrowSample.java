package sample;

import java.util.Optional;

public class OrThrowSample {
    public String getValue(String path) {
        return fromFilePath(path).orElseThrow();
    }
    public String getValue2(String path) {
        return fromFilePath(path)
                .orElseThrow(() -> new IllegalArgumentException("path=" + path));
    }
    private Optional<String> fromFilePath(String path) {
        return path.startsWith("file:") ? Optional.of("file value") : Optional.empty();
    }
    public static void main(String[] args) {
        System.out.println(new OrThrowSample().getValue2("file:/foo/bar/.."));
        new OrThrowSample().getValue("net:/foo/bar/..");
    }
}
