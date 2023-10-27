package com.store.tunnel.getProduct;

import com.store.tunnel.product;
import jdk.jfr.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 Author: Kareem M
 Date: 01/May/2023
 Description: Return the available products from getRepository Interface

 Last Updated:
 01/May/2023 - Create GET Controller
 08/Oct/2023 - Add Comments and refactor the code
 **/

@Slf4j
@RestController
@RequestMapping("/view")
public class getProductController {

    @Autowired
    private getRepository gr;

    // List of products when submitting GET request
    @ResponseBody
    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public List<product> getAll(){
        List<product> data = gr.findAll();
        log.info(data.toString());
        return data;
    }
}
