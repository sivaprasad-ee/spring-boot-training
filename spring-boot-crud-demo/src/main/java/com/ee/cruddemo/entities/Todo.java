package com.ee.cruddemo.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Todo {
    private Long id;
    private String content;
    private boolean completed;
    private LocalDateTime createdOn;
}
