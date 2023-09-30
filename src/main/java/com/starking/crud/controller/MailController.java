package com.starking.crud.controller;

import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starking.crud.domain.model.Usuario;
import com.starking.crud.repositories.UsuarioRepository;

/**
 * @author pedroRhamon
 */

@RestController
public class MailController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(path = "/email-send", method = RequestMethod.GET)
	public String sendMail(@RequestParam String email) {
		Optional<Usuario> optionalEmail = this.usuarioRepository.findByEmail(email);
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText("Bem vindo a Empresa Starking");
		message.setTo("pedrorhamon167@gmail.com");
		message.setFrom(optionalEmail.toString());

		try {
			this.mailSender.send(message);
			return "Email enviado com sucesso!";
		} catch (Exception e) {
			return "Erro ao enviar email";
		}
	}

	@RequestMapping(path = "/email-send", method = RequestMethod.GET)
	public String sendMailHtml() {
		try {
			MimeMessage mail = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setTo("pedro@gmail.com");
			helper.setSubject("Teste Envio de e-mail");
			helper.setText("<p>Bem vindo a Empresa Starking</p>", true);
			mailSender.send(mail);

			return "OK";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao enviar e-mail";
		}
	}

}
