package com.travel.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewListProductDTO {

    private String memberNickname;

    private String postContent;

    private int scope;
}
