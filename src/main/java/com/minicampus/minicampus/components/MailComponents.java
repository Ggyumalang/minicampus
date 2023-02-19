package com.minicampus.minicampus.components;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
@Slf4j
public class MailComponents {

    private final JavaMailSender javaMailSender;

    public void sendMailTest(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("khg50877@gamil.com");
        message.setSubject("안녕하세요. 테스트입니다.");
        message.setText("안녕하세요 테스트에용 반가워요");

        javaMailSender.send(message);
    }

    public boolean sendMail(String email , String subject , String text){

        boolean result = false;

        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true , "UTF-8");
                mimeMessageHelper.setTo(email);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text, true); //HTML 사용 가능!
            }
        };

        try {
            javaMailSender.send(msg);
            result = true;
        } catch (MailException e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
