package com.travel.post.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class QnACreateDTO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String inquiryType;

    private Long purchasedProductId;
}
