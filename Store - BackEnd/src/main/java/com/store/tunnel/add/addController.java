package com.store.tunnel.add;

import com.store.tunnel.product;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 Author: Kareem M
 Date: 01/May/2023
 Description: Manage adding new product object

 Last Updated:
 01/May/2023 - Create GET Controller
 08/Oct/2023 - Add Comments and refactor the code
 **/

@Slf4j
@RestController
@RequestMapping("/add")
public class addController {

    @Autowired
    private addRepository ar;

    // POST uploaded image as Multipart request
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<String> addProduct(@Valid @RequestPart("file") MultipartFile multipartFile,
                                             @Valid @RequestPart("form") product p,
                                             HttpServletResponse response,
                                             Errors e) throws IOException {
         log.info("Add POST");
//         log.info(e.toString());

        log.info(p.toString());

        // Get image file name from the uploaded file
        String fileName = multipartFile.getOriginalFilename();


        log.info(fileName + " received");

        // Get list of the uploaded file names in DB
        List<String> filenames = ar.findFileNames();

        // Convert the file name to path like
        String file = StringUtils.cleanPath(fileName);

        // Iterate over the file names as the file name must be unique
        String filename = filenames.stream()
                .filter(name -> file.equals(name.toString()))
                .findAny()
                .orElse(null);

        log.info(filename);

        // If the image is new and no previous one has similar name
        if (filename == null) {
            // The value of the Trader Share should not be greater than the product price
            if (p.getTraderShare() > p.getPrice()){
                response.sendError(HttpStatus.BAD_REQUEST.value(), "حصة التاجر لا يمكن أن تكون أعلى من سعر القطعة");
            // Save the image using the service
            } else {
                FileUploadUtil.saveFile("./src/main/resources/static/images", StringUtils.cleanPath(fileName), multipartFile);
                ar.save(p);
            }

        }
        // Return Error that there is an image with similar name already exists
        else {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "يوجد صورة بنفس الاسم، برجاء اختيار صورة أخرى");
        }
     return new ResponseEntity<>(HttpStatus.OK);
    }

    // Generate Exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex, Errors e) {
        Map<String, String> response = new HashMap<>();
        ArrayList<Map<String,String>> errorsArray = new ArrayList<>();
        for (int i = 0; i < e.getErrorCount(); i++) {

            // Build the error response message as the below:
            // [
            //  {
            //      "field": <Get the field that has error>,
            //      "defaultMessage": <Get the error message>
            //  },
            // ....
            // ]
            response.put("field", e.getFieldErrors().get(i).getField());
            response.put("defaultMessage", e.getFieldErrors().get(i).getDefaultMessage());
//            log.info(response.toString());

            // Add each hashed error message to $errorsArray ArrayList
            errorsArray.add(new HashMap<>(response));
            response.clear();
        }
//        log.info(r.toString());
        return new ResponseEntity<Object>(errorsArray, HttpStatus.BAD_REQUEST);
    }
}