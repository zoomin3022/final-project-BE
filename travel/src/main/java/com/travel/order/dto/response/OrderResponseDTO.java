package com.travel.order.dto.response;

import com.travel.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class OrderResponseDTO {

    private Long orderId;

    private Long productId;

    private Long purchasedProductId;

    private String productName;

    private String productThumbnail;

    private Integer productPrice;

    private LocalDateTime orderDate;

    private String optionName;

    private Integer purchasedProductQuantity;

    private String orderStatus;

    private boolean hasReview;

    @Builder
    public OrderResponseDTO(
            Long orderId, Long productId, Long purchasedProductId, String productName,
            String productThumbnail, Integer productPrice, LocalDateTime orderDate, String optionName,
            Integer purchasedProductQuantity, OrderStatus orderStatus, boolean hasReview
    ) {
        this.orderId = orderId;
        this.productId = productId;
        this.purchasedProductId = purchasedProductId;
        this.productName = productName;
        this.productThumbnail = productThumbnail;
        this.productPrice = productPrice;
        this.orderDate = orderDate;
        this.optionName = optionName;
        this.purchasedProductQuantity = purchasedProductQuantity;
        this.orderStatus = orderStatus.getKorean();
        this.hasReview = hasReview;
    }
}
