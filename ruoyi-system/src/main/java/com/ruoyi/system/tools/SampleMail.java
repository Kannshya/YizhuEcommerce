package com.ruoyi.system.tools;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
//import java.net.MalformedURLException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

public class SampleMail {

    // 配置常量
    private static final String SMTP_HOST = "smtpdm.aliyun.com";
    private static final int SMTP_PORT = 80;
    private static final String USER_NAME = "service@smtp.yizhu.me";
    private static final String PASSWORD = "serviceYIZHU48";

    protected static String genMessageID(String mailFrom) {
        // 生成Message-ID:
        if (!mailFrom.contains("@")) {
            throw new IllegalArgumentException("Invalid email format: " + mailFrom);
        }
        String domain = mailFrom.split("@")[1];
        UUID uuid = UUID.randomUUID();
        return "<" + uuid.toString() + "@" + domain + ">";
    }

    private static void setRecipients(MimeMessage message, Message.RecipientType type, String[] recipients)
            throws MessagingException {
        // 设置收件人地址
        if (recipients == null || recipients.length == 0) {
            return; // 空列表不设置
        }
        InternetAddress[] addresses = new InternetAddress[recipients.length];
        for (int h = 0; h < recipients.length; h++) {
            addresses[h] = new InternetAddress(recipients[h]);
        }
        message.setRecipients(type, addresses);
    }

    public static void sendEmail(String toEmail, String subject, String content) throws MessagingException, UnsupportedEncodingException {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();

        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.from", USER_NAME);
        props.put("mail.user", USER_NAME);
        props.put("mail.password", PASSWORD);
        System.setProperty("mail.mime.splitlongparameters", "false");

        // 构建授权信息
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        };

        Session mailSession = Session.getInstance(props, authenticator);
        String messageIDValue = genMessageID(USER_NAME);
        MimeMessage message = new MimeMessage(mailSession) {
            @Override
            protected void updateMessageID() throws MessagingException {
                setHeader("Message-ID", messageIDValue);
            }
        };

        // 设置发件人和收件人
        InternetAddress from = new InternetAddress(USER_NAME, "YizhuEcommerce");
        message.setFrom(from);
        setRecipients(message, Message.RecipientType.TO, new String[]{toEmail});

        // 设置邮件主题和内容
        message.setSentDate(new Date());
        message.setSubject(subject);

        Multipart multipart = new MimeMultipart();
        BodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(content, "text/html;charset=UTF-8");
        multipart.addBodyPart(htmlPart);
        message.setContent(multipart);

        Transport.send(message);
    }

    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();

        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        //设置端口：
        props.put("mail.smtp.port", SMTP_PORT);//或"25", 如果使用ssl，则去掉使用80或25端口的配置，进行如下配置：

        props.put("mail.smtp.from", USER_NAME);    //mailfrom 参数
        props.put("mail.user", USER_NAME);// 发件人的账号（在控制台创建的发信地址）
        props.put("mail.password", PASSWORD);// 发信地址的smtp密码（在控制台选择发信地址进行设置）
        //props.put("mail.smtp.connectiontimeout", 1000);
        System.setProperty("mail.mime.splitlongparameters", "false");//用于解决附件名过长导致的显示异常
        //props.setProperty("mail.smtp.ssl.enable", "true");  //请配合465端口使用

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        };

        //使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);

        String messageIDValue = genMessageID(USER_NAME);
        MimeMessage message = new MimeMessage(mailSession) {
            @Override
            protected void updateMessageID() throws MessagingException {
                setHeader("Message-ID", messageIDValue);
            }
        };

        try {
            // 设置发件人邮件地址和名称。填写控制台配置的发信地址。和上面的mail.user保持一致。名称用户可以自定义填写。
            InternetAddress from = new InternetAddress(USER_NAME, "YizhuEcommerce");//from 参数,可实现代发，注意：代发容易被收信方拒信或进入垃圾箱。
            message.setFrom(from);

            setRecipients(message, Message.RecipientType.TO, new String[]{"364195403@qq.com"});
//            setRecipients(message, Message.RecipientType.CC, new String[]{"收信地址3", "收信地址4"});
//            setRecipients(message, Message.RecipientType.BCC, new String[]{"收信地址5", "收信地址6"});

//            InternetAddress replyToAddress = new InternetAddress("回信地址");
//            message.setReplyTo(new Address[]{replyToAddress});//可选。设置回信地址
            message.setSentDate(new Date());
            message.setSubject("测试主题");

            //发送附件和内容：
            // 创建多重消息
            Multipart multipart = new MimeMultipart();


            // 创建一个BodyPart用于HTML内容
            BodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("测试<br> html内容4", "text/html;charset=UTF-8");//设置邮件的内容，会覆盖前面的message.setContent
            multipart.addBodyPart(htmlPart);

            // 添加完整消息
            message.setContent(multipart);
            // 发送附件代码，结束

            //mailSession.setDebug(true);//开启debug模式
            Transport.send(message);
            System.out.println("发送完成！");
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.err.println("邮件发送失败: " + e.getMessage());
            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
        }

    }
}
