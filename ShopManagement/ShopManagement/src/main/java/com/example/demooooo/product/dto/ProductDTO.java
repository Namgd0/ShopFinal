package com.example.demooooo.product.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ProductDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "productid")
    private int id;

    private int idshop;

    @NotBlank(message = "Not null!!!")
    private String productname;

    @Min(value = 0, message = "Not Null!!!")
    private double unitprice;

    @Min(value = 1, message = "Tối thiểu một sản phẩm!!!")
    private int quantity;

    private int selledquantity;
}
