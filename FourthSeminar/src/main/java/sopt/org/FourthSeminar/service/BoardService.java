package sopt.org.FourthSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.FourthSeminar.controller.dto.request.BoardImageListRequestDto;
import sopt.org.FourthSeminar.controller.dto.request.BoardRequestDto;
import sopt.org.FourthSeminar.controller.dto.request.BoardRequestPartImageDto;
import sopt.org.FourthSeminar.domain.Board;
import sopt.org.FourthSeminar.domain.Image;
import sopt.org.FourthSeminar.domain.User;
import sopt.org.FourthSeminar.exception.Error;
import sopt.org.FourthSeminar.exception.model.NotFoundException;
import sopt.org.FourthSeminar.infrastructure.BoardRepository;
import sopt.org.FourthSeminar.infrastructure.ImageRepository;
import sopt.org.FourthSeminar.infrastructure.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;


    @Transactional
    public void create(Long userId, List<String> boardImageUrlList, BoardRequestPartImageDto request) {
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

// 단일 이미지 게시글 생성
//        Board newBoard = Board.newInstance(
//                user,
//                boardThumbnailImageUrl,
//                request.getTitle(),
//                request.getContent(),
//                request.getIsPublic()
//        );

        Board newBoard = Board.newInstance(
                user,
                request.getTitle(),
                request.getContent(),
                request.getIsPublic()
        );
        boardRepository.save(newBoard);

        //이미지 생성
        for (String boardImageUrl: boardImageUrlList) {
            imageRepository.save(Image.newInstance(newBoard, boardImageUrl));
        }    }
}
