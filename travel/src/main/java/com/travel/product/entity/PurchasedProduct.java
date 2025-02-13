package com.travel.product.entity;

import com.travel.member.entity.Member;
import com.travel.order.dto.response.OrderAdminResponseDTO;
import com.travel.order.dto.response.OrderResponseDTO;
import com.travel.order.entity.Order;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "purchased_product")
@Entity
public class PurchasedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchased_product_id")
    private Long purchasedProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "purchased_product_name")
    private String purchasedProductName;

    @Column(name = "purchased_product_thumbnail")
    private String purchasedProductThumbnail;

    @Column(name = "purchased_product_price")
    private Integer purchasedProductPrice;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "period")
    private Integer period;

    @Column(name = "option_name")
    private String periodOptionName;

    @Setter
    @Column(name = "purchased_product_quantity")
    private Integer purchasedProductQuantity;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    public PurchasedProduct(Product product, PeriodOption periodOption, Integer quantity) {
        this.product = product;
        this.purchasedProductName = product.getProductName();
        this.purchasedProductThumbnail = product.getProductImages().get(0).getImagePath();
        this.purchasedProductPrice = product.getProductPrice() * quantity; //나중에 옵션이랑 더 해줄 예정
        this.startDate = periodOption.getStartDate();
        this.endDate = periodOption.getEndDate();
        this.period = periodOption.getPeriod();
        this.periodOptionName = periodOption.getOptionName();
        this.purchasedProductQuantity = quantity;
    }

    public OrderResponseDTO toOrderResponseDTO(boolean hasReview) {
        return OrderResponseDTO.builder()
                .orderId(this.order.getOrderId())
                .productId(this.product.getProductId())
                .purchasedProductId(this.purchasedProductId)
                .productName(this.purchasedProductName)
                .productThumbnail(this.purchasedProductThumbnail)
                .productPrice(this.purchasedProductPrice)
                .optionName(this.periodOptionName)
                .purchasedProductQuantity(this.purchasedProductQuantity)
                .orderDate(this.order.getCreatedDate())
                .orderStatus(this.order.getOrderStatus())
                .hasReview(hasReview)
                .build();
    }

    public OrderAdminResponseDTO toOrderAdminResponseDTO(Member member) {
        return OrderAdminResponseDTO.builder()
                .orderId(this.order.getOrderId())
                .productId(this.product.getProductId())
                .purchasedProductId(this.purchasedProductId)
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .memberEmail(member.getMemberEmail())
                .productName(this.purchasedProductName)
                .productThumbnail(this.purchasedProductThumbnail)
                .productPrice(this.purchasedProductPrice)
                .periodOptionName(this.periodOptionName)
                .purchasedProductQuantity(this.purchasedProductQuantity)
                .orderDate(this.order.getCreatedDate())
                .orderStatus(this.order.getOrderStatus())
                .paymentMethod(this.order.getPaymentMethod())
                .build();
    }
}
