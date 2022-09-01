package com.mth.provider.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: mth
 * @date: 2022/8/30
 **/
@Slf4j
@Data
@Builder
public class Book {
    public String id;
    public String bookName;
    public String count;
}
