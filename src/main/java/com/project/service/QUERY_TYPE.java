package com.project.service;


/*
 * 
 * Author : Emin.Aydogar
 * 
 */

public enum QUERY_TYPE {
    SELECT("SELECT * FROM "),
    UPDATE("UPDATE"),
    DELETE("DELETE FROM ");
	
    public final String type;

    private QUERY_TYPE(String type) {
        this.type = type;
    }
}
