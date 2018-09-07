package com.mpri.aio.system.utils;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class MailUtil
{
	@Autowired
	private JavaMailSender jms;

	/**
	 * 管理员邮箱
	 */
	@Value("${spring.mail.adminAddress}")
	String adminAddress;

	/**
	 * 使用freemarker模板发送邮件
	 * @param toEmail 目标地址
	 * @param template 模板对象
	 * @param model 模板参数
	 * @throws IOException
	 * @throws TemplateException
	 * @throws MessagingException
	 */
	@Async
	public void send(String toEmail, Template template,Map<String, Object> map)
			throws IOException, TemplateException, MessagingException
	{
		String html = FreeMarkerTemplateUtils
				.processTemplateIntoString(template, map);
		send(toEmail, html);
	}
	
	/**
	 * 发送邮件
	 * @param toEmail 目标地址
	 * @param html 发送内容
	 * @throws IOException
	 * @throws TemplateException
	 * @throws MessagingException
	 */
	@Async
	public void send(String toEmail, String html)
			throws IOException, MessagingException
	{
		MimeMessage message = jms.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(adminAddress);
		helper.setTo(toEmail);

		helper.setText(html, true);
		jms.send(message);
	}
}
