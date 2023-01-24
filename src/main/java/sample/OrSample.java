package sample;

import java.util.Optional;

public class OrSample {
    public String resolveValue(String path, String defaultValue) {
        return fromFilePath(path)
                .or(() -> fromResourcePath(path))
                .or(() -> fromUrlPath(path))
                .orElse(defaultValue);
    }
    public String resolveValue1(String path, String defaultValue) {
        return fromFilePath(path)
                .orElseGet(() -> fromResourcePath(path)
                        .orElseGet(() -> fromUrlPath(path)
                                .orElse(defaultValue))

                );
    }
    public String resolveValue2(String path, String defaultValue) {
        Optional<String> val = fromFilePath(path);
        if (val.isPresent()) {
            return val.get();
        }
        val = fromResourcePath(path);
        if (val.isPresent()) {
            return val.get();
        }
        val = fromUrlPath(path);
        if (val.isPresent()) {
            return val.get();
        }
        return defaultValue;
    }
    private Optional<String> fromFilePath(String path) {
        // ex) file:/foo/bar/...
        return path.startsWith("file:") ? Optional.of("file value") : Optional.empty();
    }
    private Optional<String> fromResourcePath(String path) {
        // ex) jar:file:/foo/bar/...
        return path.startsWith("jar:") ? Optional.of("jar value") : Optional.empty();
    }
    private Optional<String> fromUrlPath(String path) {
        // ex) http://foo.bar/...
        return path.startsWith("http:") ? Optional.of("url value") : Optional.empty();
    }
    public static void main(String[] args) {
        var val1 = new OrSample().resolveValue("jar:file:/foo/bar/...", "unknown value");
        var val2 = new OrSample().resolveValue("net:/foo/bar/...", "unknown value");
        System.out.println(val1);
        System.out.println(val2);
    }
}
