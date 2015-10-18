package br.com.economy.email;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

//clase que retorna uma autenticacao para ser enviada e verificada pelo servidor smtp  
class SimpleAuth extends Authenticator 
{  
    public String username = null;  
    public String password = null;  
  
  
    public SimpleAuth(String user, String pwd) 
    {  
        username = user;  
        password = pwd;  
    }  
  
    protected PasswordAuthentication getPasswordAuthentication() 
    {  
        return new PasswordAuthentication (username,password);  
    } 

}