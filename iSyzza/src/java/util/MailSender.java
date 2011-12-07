/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import oracle.jdbc.driver.Message;
//import sun.rmi.transport.Transport;

/**
 *
 * @author karenborges
 */
public class MailSender {

    /**
     * Send an Email via Gmail SMTP server using SSL connection.
     * @param assunto
     * @param mensagem
     * @throws MessagingException 
     */
    public static void sendMail(String senha, String emailTo) throws MessagingException, IOException {
        PropertiesManager pm = new PropertiesManager("email.properties");
        HashMap dados = pm.readPropertiesFile();
        
        final String username = (String) dados.get("username");
        final String password = (String) dados.get("password");
        final String host = (String) dados.get("host");
        final String porta = (String) dados.get("porta");

        Properties props = new Properties();
        props.put("mail.smtp.host", host );
        props.put("mail.smtp.socketFactory.port", porta);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", porta);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

            @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("isyzza@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(emailTo));
        message.setSubject("iSyzza - Nova Senha");
        message.setText("Olá, a sua nova senha é: " + senha + " Não responda a este email.");

        Transport.send(message);
        System.out.println("foiee");

    }

//    public static void main(String[] args) {
//        try {
//                MailSender.sendMail("mensagem de teste2", "o assunto é teste de novo");
//            
//            System.out.println("email enviado com sucesso !");
//        } catch (MessagingException ex) {
//            System.out.println(ex);
//        } catch (IOException ex) {
//                Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    }
    public static void main(String[] args) throws MessagingException, IOException {
        MailSender ms = new MailSender();
        MailSender.sendMail("Cornoooooomanco", "macielmorais90@gmail.com");
    }
}
