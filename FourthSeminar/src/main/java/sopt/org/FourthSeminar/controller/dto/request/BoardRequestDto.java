package sopt.org.FourthSeminar.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardRequestDto {

    //@Email(message = "이메일 형식에 맞지 않습니다")
    //private String email;
    @NotNull
    private MultipartFile thumbnail;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private  Boolean isPublic;
}