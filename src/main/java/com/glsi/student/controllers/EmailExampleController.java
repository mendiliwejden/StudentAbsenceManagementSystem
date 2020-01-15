package com.glsi.student.controllers;

import com.glsi.student.config.Scheduled;
import com.glsi.student.entities.Absence;
import com.glsi.student.entities.Etudiant;
import com.glsi.student.repositories.EtudiantRepository;
import com.glsi.student.service.AbsenceService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
//@Scheduled(Cron="0 0 12 ? ? SUN", zone="Africa/Tunis")
public class EmailExampleController {

	@Autowired
    public JavaMailSender emailSender;
	
	@Autowired
	EtudiantRepository studentRepo;
	
	@Autowired
	private AbsenceService service;
 
    @ResponseBody
    @RequestMapping("/sendEmail")
    public String sendSimpleEmail() {
    	
    	Iterable<Etudiant> students = studentRepo.findAll();
    	
    	for (Etudiant student:students) {
    		@NonNull
			String StudentMail = student.getEmail();
    		String studentName="Bonjour  "+student.getNom() + student.getPrenom();
    		List<Absence> absences = service.findAllAbsences();
    		
    		List<String> abs =absences.stream().filter(x->x.getEtudiant().equals(student))
    				.map(x->x.getMatiere().getLabel()+"  "+x.getNbrAbs()).collect(Collectors.toList());
    		
    		String t = abs.toString();
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
         
        message.setTo(StudentMail);
        message.setSubject("La liste D'absence");
        message.setText(studentName+t);
 
        // Send Message!
        this.emailSender.send(message);
    	}
        return "Email Sent!";
    }
}
