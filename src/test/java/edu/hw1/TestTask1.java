package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask1 {
    @Test
    @DisplayName("Преобразование длины видео в секунды из корректной строки формата mm:ss")
    void testCorrectMinutesToSeconds() {
        // given
        String formattedVideoLength = "01:00";
        // when
        int videoLengthInSeconds = Task1.minutesToSeconds(formattedVideoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(60);

        // given
        formattedVideoLength = "13:56";
        // when
        videoLengthInSeconds = Task1.minutesToSeconds(formattedVideoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(836);

        // given
        formattedVideoLength = "999:59";
        // when
        videoLengthInSeconds = Task1.minutesToSeconds(formattedVideoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(59999);

        // given
        formattedVideoLength = "312:59";
        // when
        videoLengthInSeconds = Task1.minutesToSeconds(formattedVideoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(18779);

        // given
        formattedVideoLength = "00:00";
        // when
        videoLengthInSeconds = Task1.minutesToSeconds(formattedVideoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(0);

        // given
        formattedVideoLength = "01:12";
        // when
        videoLengthInSeconds = Task1.minutesToSeconds(formattedVideoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(72);
    }

    @Test
    @DisplayName("Преобразование длины видео в секунды из некорректной строки формата mm:ss")
    void testIncorrectMinutesToSeconds() {
        // given
        String formattedVideoLength = "0:00";
        // when
        int videoLengthInSeconds = Task1.minutesToSeconds(formattedVideoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(-1);

        // given
        formattedVideoLength = "10:60";
        // when
        videoLengthInSeconds = Task1.minutesToSeconds(formattedVideoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(-1);

        // given
        formattedVideoLength = ":";
        // when
        videoLengthInSeconds = Task1.minutesToSeconds(formattedVideoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(-1);

        // given
        formattedVideoLength = "";
        // when
        videoLengthInSeconds = Task1.minutesToSeconds(formattedVideoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(-1);

        // given
        formattedVideoLength = "52:000";
        // when
        videoLengthInSeconds = Task1.minutesToSeconds(formattedVideoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(-1);
    }
}
