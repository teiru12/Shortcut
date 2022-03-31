package sc.mail;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import sc.util.MailUtils;

@Service("mailService")
public class MailService {
	@Resource(name = "mailSender")
    private JavaMailSenderImpl mailSender;

    //인증메일 보내기
    public void sendAuthMail(String email) {
        //인증메일 보내기
        try {
            MailUtils sendMail = new MailUtils(mailSender);
            sendMail.setSubject("ShortCut 이메일인증");
            sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
            .append("<p>아래 비밀번호로 로그인후 비밀번호 변경하시길 권장드립니다.</p>")
            .append("<a href='http://localhost:9003/SC/testjoin.cut?EMAIL=")
            .append(email)
            .append("'>링크</a>")
            .toString());
            sendMail.setFrom("ShortCut0328@gmail.com", "관리자");
            sendMail.setTo(email);
            sendMail.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public void sendAuthMail2(String email, String password) {
        //인증메일 보내기
        try {
            MailUtils sendMail = new MailUtils(mailSender);
            sendMail.setSubject("ShortCut 이메일인증");
            sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
            .append("<p>아래 비밀번호로 로그인후 비밀번호 변경하시길 권장드립니다.</p>")
            .append(password)
            .append("<p><a href='http://localhost:9003/SC/loginForm.cut?EMAIL=")
            .append(email)
            .append("'>링크</a></p>")
            .toString());
            sendMail.setFrom("ShortCut0328@gmail.com", "관리자");
            sendMail.setTo(email);
            sendMail.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
