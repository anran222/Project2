package com;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author:xiang
 */
@Getter
@Setter
@ToString
public class User {
    private Integer id;

    private String name;

    private Date createTime;
}
