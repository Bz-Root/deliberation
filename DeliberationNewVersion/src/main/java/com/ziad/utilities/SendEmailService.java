package com.ziad.utilities;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
	@Autowired
	private JavaMailSender mailer;

	private static final String FROM_EMAIL = "deliberation.sms@gmail.com";
	private static final String EMAIL_HTML = "\r\n" + "\r\n"
			+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n"
			+ "<title>Welcome to One Touch</title>\r\n" + "<style type=\"text/css\">\r\n"
			+ "#emailWrapperTable table {\r\n" + "	font: 13px Arial, Verdana, Helvetica, sans-serif;\r\n"
			+ "	color: #292929;\r\n" + "}\r\n" + "\r\n" + "#emailWrapperTable h1, #emailWrapperTable h2 {\r\n"
			+ "	font-family: Arial, Verdana, Helvetica, sans-serif;\r\n" + "	margin-bottom: 2px;\r\n"
			+ "	font-size: 15px;\r\n" + "}\r\n" + "\r\n" + "#emailWrapperTable h3 {\r\n" + "	font-size: 13px;\r\n"
			+ "}\r\n" + "\r\n" + "#emailWrapperTable h4 {\r\n" + "	font-size: 11px;\r\n" + "}\r\n" + "\r\n" + "a {\r\n"
			+ "	color: #084482;\r\n" + "	text-decoration: underline;\r\n" + "}\r\n" + "\r\n" + "a.actionLink {\r\n"
			+ "	color: #000;\r\n" + "	text-decoration: none;\r\n" + "}\r\n" + "\r\n" + "hr {\r\n"
			+ "	display: none;\r\n" + "}\r\n" + "\r\n" + ".small {\r\n" + "	font-size: 10px;\r\n" + "}\r\n" + "\r\n"
			+ ".ppid {\r\n" + "	color: #757575;\r\n" + "}\r\n" + "\r\n" + "p {\r\n" + "	margin: 11px 0;\r\n"
			+ "	padding: 0;\r\n" + "}\r\n" + "\r\n" + ".headline {\r\n"
			+ "	font-family: Helvetica Neue Light, Helvetica;\r\n" + "	font-weight: 300;\r\n"
			+ "	font-size: 28px;\r\n" + "	color: #0079C1;\r\n" + "}\r\n" + "\r\n" + "sup {\r\n"
			+ "	font-size: 7px !important;\r\n" + "}\r\n" + "\r\n" + "sup {\r\n" + "	font-size: 7px !important;\r\n"
			+ "}\r\n" + "\r\n" + "sup {\r\n" + "	font-size: 7px !important;\r\n" + "}\r\n" + "\r\n" + "sup {\r\n"
			+ "	font-size: 7px !important;\r\n" + "}\r\n" + "\r\n" + "sup {\r\n" + "	font-size: 7px !important;\r\n"
			+ "}\r\n" + "\r\n" + "sup {\r\n" + "	font-size: 7px !important;\r\n" + "}\r\n" + "\r\n" + "sup {\r\n"
			+ "	font-size: 7px !important;\r\n" + "}\r\n" + "\r\n" + "sup {\r\n" + "	font-size: 7px !important;\r\n"
			+ "}\r\n" + "\r\n" + "sup {\r\n" + "	font-size: 7px !important;\r\n" + "}\r\n" + "\r\n"
			+ ".footerlink {\r\n" + "	font: 13px Arial, Verdana, Helvetica, sans-serif !important;\r\n"
			+ "	color: #292929 !important;\r\n" + "	font-weight: bold;\r\n" + "	line-height: auto;\r\n"
			+ "	width: 530px;\r\n" + "}\r\n" + "\r\n" + "a.footerlink:link {\r\n" + "	color: #084482;\r\n"
			+ "	text-decoration: underline;\r\n" + "}\r\n" + "\r\n" + "a.footerlink:visited {\r\n"
			+ "	color: #820844;\r\n" + "	text-decoration: none;\r\n" + "}\r\n" + "div.button{\r\n"
			+ "	padding: 19px;\r\n" + "	border: 2px solid #007FA4;\r\n" + "	color:#007FA4;\r\n"
			+ "	background-color: white;\r\n" + "	font-size:18px;\r\n" + "	text-decoration: none;\r\n" + "}\r\n"
			+ "div.button a{\r\n" + "	margin-left: 200px;\r\n" + "}\r\n" + "</style>\r\n" + "\r\n"
			+ "<div style=\"display: none; color: #fff; font-size: 1pt;\"></div>\r\n"
			+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"emailWrapperTable\"\r\n"
			+ "	width=\"580\">\r\n" + "	<tbody>\r\n" + "		<tr valign=\"top\">\r\n"
			+ "			<td colspan=\"3\">\r\n"
			+ "				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n"
			+ "					<tbody>\r\n" + "						<tr valign=\"top\">\r\n"
			+ "							<td width=\"130px;\"><br></td>\r\n" + "						</tr>\r\n"
			+ "						<tr>\r\n"
			+ "							<td><img alt=\"\" border=\"0\" height=\"10\"\r\n"
			+ "								src=\"http://www.paypalobjects.com/en_US/i/scr/pixel.gif\"\r\n"
			+ "								width=\"1\"><img src=\"http://www.umi.ac.ma/wp-content/themes/umi/images/logo.png\"><br>\r\n"
			+ "							<br></td>\r\n" + "						</tr>\r\n"
			+ "					</tbody>\r\n" + "				</table>\r\n" + "			</td>\r\n"
			+ "		</tr>\r\n" + "		<tr>\r\n" + "			<td colspan=\"3\"><img height=\"13\"\r\n"
			+ "				src=\"https://www.paypalobjects.com/en_US/i/scr/scr_emailTopCorners_580wx13h.gif\"\r\n"
			+ "				border=\"0\" style=\"vertical-align: bottom\" alt=\"\"></td>\r\n" + "		</tr>\r\n"
			+ "		<tr>\r\n" + "			<td width=\"12\"\r\n"
			+ "				style=\"background: url(/i/scr/scr_emailLeftBorder_13wx1h.gif) left repeat-y; border-left: 1px solid #ddd;\">\r\n"
			+ "				<img src=\"https://www.paypalobjects.com/en_US/i/scr/pixel.gif\"\r\n"
			+ "				border=\"0\" alt=\"\">\r\n" + "			</td>\r\n" + "\r\n"
			+ "			<td class=\"contentArea\"\r\n"
			+ "				style=\"width: 530px; word-wrap: break-word; padding: 12px; margin: 0\"\r\n"
			+ "				width=\"530\">\r\n" + "				<table width=\"100%\">\r\n"
			+ "					<tbody>\r\n" + "						<tr>\r\n"
			+ "							<td><span class=\"headline\"\r\n"
			+ "								style=\"font-family: Helvetica Neue Light, Helvetica; font-weight: 300; font-size: 28px; color: #0079C1;\">\r\n"
			+ "									<p style=\"font-size: 80%; color: #007FA4;\">{{titre}}</p>\r\n"
			+ "							</span>\r\n" + "							<p>Cher {{nom}},</p>\r\n"
			+ "								<p>{{paragraphe}}</p>\r\n"
			+ "								<div class=\"button\">\r\n"
			+ "									<a href=\"{{link}}\">Continuer</a>\r\n"
			+ "								</div>\r\n" + "						</tr>\r\n"
			+ "					</tbody>\r\n" + "				</table>\r\n" + "			</td>\r\n"
			+ "			<td width=\"12\"\r\n"
			+ "				style=\"background: url(/i/scr/scr_emailRightBorder_13wx1h.gif) left repeat-y; border-right: 1px solid #ddd;\">\r\n"
			+ "				<img src=\"https://www.paypalobjects.com/en_US/i/scr/pixel.gif\"\r\n"
			+ "				border=\"0\" alt=\"\">\r\n" + "			</td>\r\n" + "\r\n" + "		</tr>\r\n"
			+ "		<tr>\r\n" + "			<td colspan=\"3\"><img height=\"13\"\r\n"
			+ "				src=\"https://www.paypalobjects.com/en_US/i/scr/scr_emailBottomCorners_580wx13h.gif\"\r\n"
			+ "				border=\"0\" alt=\"\"></td>\r\n" + "		</tr>\r\n" + "	</tbody>\r\n" + "</table>\r\n"
			+ "\r\n" + "\r\n" + "\r\n" + "";

	public void sendEmail(String name, String to, String body, String topic, String link) throws MessagingException {
		MimeMessage message = mailer.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		String message_html_body = makeHtmlMessage(name, body, topic, link);

		helper.setFrom(FROM_EMAIL);
		helper.setTo(to);
		helper.setSubject(topic);
		helper.setText(message_html_body, true);
		mailer.send(message);

	}

	public String makeHtmlMessage(String name, String body, String topic, String link) {
		String message = "";

		String attribut_titre = "{{titre}}";
		int index = EMAIL_HTML.indexOf(attribut_titre);
		message = EMAIL_HTML.substring(0, index);
		message = message + topic;
		index = index + attribut_titre.length();

		String attribut_name = "{{nom}}";
		int index_name = EMAIL_HTML.indexOf(attribut_name);
		message = message + EMAIL_HTML.substring(index, index_name);
		message = message + name;
		index_name = index_name + attribut_name.length();
		System.out.println(index_name);
		System.out.println(message);

		String attribut_body = "{{paragraphe}}";
		int index_body = EMAIL_HTML.indexOf(attribut_body);
		message = message + EMAIL_HTML.substring(index_name, index_body);
		message = message + body;
		index_body = index_body + attribut_body.length();
		System.out.println(index_body);

		String attribut_link = "{{link}}";
		int index_link = EMAIL_HTML.indexOf(attribut_link);
		message = message + EMAIL_HTML.substring(index_body, index_link);
		message = message + link;
		index_link = index_link + attribut_link.length();
		message = message + EMAIL_HTML.substring(index_link);
		return message;

	}

}
