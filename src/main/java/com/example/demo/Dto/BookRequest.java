package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class BookRequest {

    private String title;
    private String isbn;
    private String edition;
    private String language;
    private Integer publicationYear;
    private String summary;
    private String coverImageUrl;
    private Long publisherId;
    private Set<Long> authorIds;
    private Set<Long> categoryIds;



}
