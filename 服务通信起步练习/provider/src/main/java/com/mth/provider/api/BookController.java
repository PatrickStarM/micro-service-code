package com.mth.provider.api;

import com.mth.provider.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: mth
 * @date: 2022/8/30
 **/
@RestController
public class BookController {
    @GetMapping("/api/books")
    public String sayHello() {
        List<Book> books = new ArrayList<>();
        books.add(Book.builder().id("1").bookName("《SpringCloud从入门到入职》").count("99").build());
        books.add(Book.builder().id("2").bookName("《Flutter从入门到入职》").count("99").build()) ;
        books.add(Book.builder().id("3").bookName("《SpringBoot从入门到入职》").count("99").build());

        return books.toString();
    }
}
