package org.jusecase.jte.output;

import org.junit.jupiter.api.Test;
import org.jusecase.jte.internal.IoUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class Utf8FileOutputTest {

    @Test
    void test() {
        assertEncoding("Hello world");
        assertEncoding("你好，世界");
        assertEncoding("你好，世界 \uD83D\uDE02\uD83D\uDE02\uD83D\uDE02");
    }

    private void assertEncoding(String expected) {
        Path path = Path.of("utf-8-file-output.tmp");
        try (Utf8FileOutput output = new Utf8FileOutput(path)) {
            output.writeSafeContent(expected);

            try (InputStream is = Files.newInputStream(path)) {
                String result = IoUtils.toString(is);
                assertThat(result).isEqualTo(expected);
            }

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            try {
                Files.delete(path);
            } catch (IOException e) {
                // Ignored
            }
        }
    }
}