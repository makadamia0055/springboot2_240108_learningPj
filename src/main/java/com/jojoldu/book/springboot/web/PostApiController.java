package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.service.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Tag(name= "Posts API Controller", description = "Posts와 관련된 API 요청 컨트롤러")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostApiController {

    private final PostsService postsService;

    @Operation(summary = "Get All Posts", description = "게시판 전체 목록 조회 등을 위한 모든 Posts조회")
    @ApiResponse(responseCode = "200", description = "모든 게시글 조회",
    content = {@Content(schema = @Schema(implementation = PostsListResponseDto.class))})
    @GetMapping("/all")
    public List<PostsListResponseDto> getAllPosts(){
        List<PostsListResponseDto> postsList = postsService.findAllDesc();
        return postsList;
    }
    @Operation(summary = "Post a post", description = "post 게시글을 받아 Posts 엔티티로 저장")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공. 등록된 게시글 번호를 리턴.",
            content = {@Content(schema = @Schema(implementation = Long.class))}),
            @ApiResponse(responseCode = "404", description = "해당 유저의 ID가 존재하지 않음.")
    })
    @PostMapping(value = "",
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Long save(
            @Parameter(description = "게시글 Save Dto 클래스", content = @Content(schema = @Schema(implementation = PostsSaveRequestDto.class)))
            @RequestBody @Valid PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @Operation(summary = "게시글 조회", description = "게시글 ID로 게시글을 조회하는 메소드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공, 해당 조회글 dto를 리턴",
                    content = @Content(schema = @Schema(implementation = PostsResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "조회 실패, 없는 글 조회 시도")
    })
    @GetMapping("/{id}")
    public PostsResponseDto findById(
            @Parameter(description = "조회할 게시글 ID", required = true, example = "1") @PathVariable Long id){
        return postsService.findById(id);
    }

    @Operation(summary = "Posts 업데이트")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update 성공, id 값 리턴"),
            @ApiResponse(responseCode = "404", description = "해당 번호 없음")
    })
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }
}
