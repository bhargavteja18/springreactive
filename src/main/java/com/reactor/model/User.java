package com.reactor.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {

    private Integer id;
    private String name;
    private String city;
}
