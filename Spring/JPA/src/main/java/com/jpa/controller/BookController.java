package com.jpa.controller;

import com.jpa.models.Request.FilterRequest;
import com.jpa.models.Request.SortRequest;
import com.jpa.models.response.CommonResponse;
import com.jpa.serivce.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/getAll")
    CommonResponse getAllBook(@RequestBody FilterRequest filterRequest){
        return CommonResponse.getCommonResponseSuccess(bookService.getAllBook(filterRequest));
    }

    @PostMapping("/get-all-spec")
    CommonResponse getAllBookSpec(@RequestBody FilterRequest filterRequest){
        return CommonResponse.getCommonResponseSuccess(bookService.getAllBookWithSpec(filterRequest));
    }

    @PostMapping("/addAll")
    CommonResponse add(){
        bookService.addAll();
        return CommonResponse.getCommonResponseSuccess("");
    }


    @PostMapping(value = "/filter")
    public ResponseEntity<String> handleFilterRequest(@RequestBody SortRequest sortRequest) {
        // Process the filterRequest here
        System.out.println("Received filter request:");
        System.out.println("Key: " + sortRequest);

        // Return a response
        return ResponseEntity.ok("Filter request processed successfully");
    }
}
