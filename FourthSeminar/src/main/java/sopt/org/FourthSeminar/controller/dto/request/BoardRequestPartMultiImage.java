package sopt.org.FourthSeminar.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardRequestPartMultiImage {
    private List<MultipartFile> thumbnails;

}
