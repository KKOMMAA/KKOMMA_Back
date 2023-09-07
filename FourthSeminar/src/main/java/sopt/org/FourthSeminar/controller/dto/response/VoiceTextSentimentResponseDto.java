package sopt.org.FourthSeminar.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VoiceTextSentimentResponseDto {
    private String voiceText;
    private String sentiment;

    public static VoiceTextSentimentResponseDto of(String voiceText,String sentiment) {
        return new VoiceTextSentimentResponseDto(voiceText,sentiment);
    }


}
