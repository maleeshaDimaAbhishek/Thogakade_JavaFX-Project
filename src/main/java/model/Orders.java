package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orders {
    private String orderId;
    private String orderDate;
    private String customerId;
}
