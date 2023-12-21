package edu.hw6.Task5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask5 {
    @Test
    void testHackerNewsTopStories_shouldReturnResult() {
        long[] res = HackerNews.hackerNewsTopStories();
        assertThat(res).isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(
        longs = {37570037, 38294543, 38318436}
    )
    void testNews_shouldReturnResultForValidNewsId(long id) {
        String res = HackerNews.news(id);
        assertThat(res).isNotEqualTo("UNDEFINED");
    }

    @ParameterizedTest
    @ValueSource(
        longs = {-1, 0, 920138124498L}
    )
    void testNews_shouldReturnUndefinedStringForInvalidNewsId(long id) {
        String res = HackerNews.news(id);
        assertThat(res).isEqualTo("UNDEFINED");
    }
}
