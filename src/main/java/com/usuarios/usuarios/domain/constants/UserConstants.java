/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.domain.constants;

/**
 *
 * @author usuario
 */
public class UserConstants {
    public static final String ROLE_OWNER = "Propietario";
    public static final String ROLE_ADMIN = "Administrador";
    public static final String ROLE_EMPLOYEE = "Empleado";
    public static final String ROLE_CLIENT = "Cliente";
    
    public static final int MINIMUM_AGE = 18;
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String PHONE_REGEX = "\\+?\\d{1,13}";
    public static final String DOCUMENT_REGEX = "\\d+";
}