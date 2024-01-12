package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    @NotBlank(message = "게시글 제목을 입력하세요.")
    @Length(max = 30, message = "게시글 제목은 30자 이하로 입력해야 합니다.")
    @Schema(description = "게시글 제목", example = "안녕하세요.")
    private String title;

    @NotBlank(message = "게시글 내용을 입력하세요.")
    @Schema(description = "게시글 내용", example = "게시글 내용입니다.")
    private String content;
    @NotBlank(message = "작성자 이름을 입력하세요.")
    @Schema(description = "작성자 이름", example = "GSB")
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

    }
}
