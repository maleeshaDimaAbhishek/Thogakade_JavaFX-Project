package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetails {
    private String orderId;
    private String item_Code;
    private int quantity;
    private int discount;
   // private Double total;

}
