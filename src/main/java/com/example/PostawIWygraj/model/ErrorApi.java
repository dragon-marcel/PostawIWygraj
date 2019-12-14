package com.example.PostawIWygraj.model;

import lombok.Data;

@Data
public class ErrorApi {

    private String message;
    private String pole;

    public ErrorApi(String message, String pole) {
	super();
	this.message = message;
	this.pole = pole;
    }

}
