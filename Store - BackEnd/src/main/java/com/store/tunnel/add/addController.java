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

@Slf4j
@RestController
@RequestMapping("/add")
public class addController {
    @Autowired
    private addRepository ar;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<String> addProduct(@Valid @RequestPart("file") MultipartFile multipartFile,
                                             @Valid @RequestPart("form") product p,
                                             HttpServletResponse response,
                                             Errors e) throws IOException {
         log.info("Add POST");
//         log.info(e.toString());

        log.info(p.toString());

        String fileName = multipartFile.getOriginalFilename();
        log.info(fileName + " received");

        List<String> filenames = ar.findFileNames();
        String file = StringUtils.cleanPath(fileName);
        String filename = filenames.stream()
                .filter(name -> file.equals(name.toString()))
                .findAny()
                .orElse(null);

        log.info(filename);
        if (filename == null) {
            if (p.getTraderShare() > p.getPrice()){
                response.sendError(HttpStatus.BAD_REQUEST.value(), "حصة التاجر لا يمكن أن تكون أعلى من سعر القطعة");
            } else {
                FileUploadUtil.saveFile("./src/main/resources/static/images", StringUtils.cleanPath(multipartFile.getOriginalFilename()), multipartFile);
                ar.save(p);
            }
        } else {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "يوجد صورة بنفس الاسم، برجاء اختيار صورة أخرى");
        }
     return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex, Errors e) {
        Map<String, String> response = new HashMap<>();
        ArrayList<Map<String,String>> r = new ArrayList<>();
        for (int i = 0; i < e.getErrorCount(); i++) {
            response.put("field", e.getFieldErrors().get(i).getField().toString());
            response.put("defaultMessage", e.getFieldErrors().get(i).getDefaultMessage().toString());
//            log.info(response.toString());
            r.add(new HashMap<>(response));
            response.clear();
        }
//        log.info(r.toString());
        return new ResponseEntity<Object>(r, HttpStatus.BAD_REQUEST);
    }
}