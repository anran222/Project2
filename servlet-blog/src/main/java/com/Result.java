package com;

import com.fasterxml.jackson.core.SerializableString;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author:xiang
 */
@Setter
@Getter
@ToString
public class Result {

    private boolean success;

    private String message;

    private String stackTrace;

    private Object data;
}
