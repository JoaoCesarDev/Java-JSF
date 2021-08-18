/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj1_maven_jsf.utils;
  
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
  
public class GenerateMD5 {
  
    public static String generate(String toMD5) {
  
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            md.update(toMD5.getBytes());
            
            byte[] bytes = md.digest();
            
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < bytes.length; i++) {
                
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        
                        .substring(1));
                
            }
            generatedPassword = sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
            
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
