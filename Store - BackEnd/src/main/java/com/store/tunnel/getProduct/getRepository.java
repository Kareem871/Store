package com.store.tunnel.getProduct;

import com.store.tunnel.product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 Author: Kareem M
 Date: 01/May/2023
 Description: List Products from DB

 Last Updated:
 01/May/2023 - Create the interface
 08/Oct/2023 - Add Comments and refactor the code
 **/

public interface getRepository extends JpaRepository<product, Long> {

    // Get All Rows in Product Table
    List<product> findAll();
}
