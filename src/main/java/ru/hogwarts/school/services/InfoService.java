package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class InfoService {

    public void testParallelStream() {
        long start = System.currentTimeMillis();
        Stream.iterate(1L, a -> a + 1)
                .limit(10_000L)
                .reduce(0L, (a, b) -> {
                    long result = 0;
                    for (int i = 0; i < a + b; i++) {
                        result += 1;
                    }
                    return result;
                });
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        Stream.iterate(1L, a -> a + 1)
                .parallel()
                .limit(10_000L)
                .reduce(0L, (a, b) -> {
                    long result = 0;
                    for (int i = 0; i < a + b; i++) {
                        result += 1;
                    }
                    return result;
                });
        System.out.println(System.currentTimeMillis() - start);
    }
}