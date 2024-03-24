package br.com.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    fun notificar(subject: String, text: String, targetEmail: String) {
        val message = SimpleMailMessage()
            .also {
                it.subject = subject
                it.text = text
                it.setTo(targetEmail)
            }

        javaMailSender.send(message)
    }

}