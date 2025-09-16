package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private String id;
    private String title;
    private String name;
    private String dob;
    private double salary;
    private String address;
    private String city;
    private String province;
    private String postalCode;

}
