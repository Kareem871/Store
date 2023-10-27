package com.store.tunnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 Author: Kareem M
 Date: 01/May/2023
 Description: Defines the product attributes and return error details

 Last Updated:
    01/May/2023 - Create the class
    08/Oct/2023 - Add Comments and refactor the code
 **/

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(columnDefinition = "serial")
    private Long id;

    // Title should be limited From 2 to 50 char
    @Size(min = 2, max= 50, message = "العنوان لا يجب أن يقل عن حرفين ولا يزيد على 50 حرف")
    @NotBlank(message = "برجاء ادخال العنوان")
    private String title;

    // Type should be limited From 2 to 50 char
    @Size(min = 2, max= 50, message = "الوع لا يجب أن يقل عن حرفين ولا يزيد على 50 حرف")
    @NotBlank(message = "برجاء اختيار النوع")
    private String type;

    // File Name should be limited From 2 to 50 char
    @Size(min = 2, max= 50, message = "اسم الملف لا يجب أن يقل عن حرفين ولا يزيد على 50 حرف")
    private String filename;

    // Price should be limited From 10 to 1000
    @Min(value = 10, message = " ₪ الحد الأدنى للسعر 10")
    @Max(value = 1000, message = "₪ الحد الأعلى للسعر 1000")
    private int price;

    // Trader Name should be limited From 2 to 50 char
    @Size(min = 2, max= 50, message = "اسم التاجر لا يجب أن يقل عن حرفين ولا يزيد على 50 حرف")
    private String traderName;

    private int traderShare;

    // Quantity - At least 1
    @Min(value = 1, message = "قطعة واحدة بحد أدنى")
    private int quantity;

    // Append the date to the object
    private Date createdAt;
    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
