package com.store.tunnel.add;


import com.store.tunnel.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



/**
 Author: Kareem M
 Date: 01/May/2023
 Description: Get the list of file names in DB to make sure that the new product image name is unique

 Last Updated:
 01/May/2023 - Create the Interface
 08/Oct/2023 - Add Comments and refactor the code
 **/


public interface addRepository extends JpaRepository<product, Long> {

    // Get the images names from product table
    @Query(value = "SELECT filename FROM product")
    List<String> findFileNames();
}
