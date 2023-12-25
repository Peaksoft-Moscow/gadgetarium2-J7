package com.peaksoft.gadgetarium2j7.service;

import com.peaksoft.gadgetarium2j7.mapper.NewsletterMapper;
import com.peaksoft.gadgetarium2j7.model.Newsletter;
import com.peaksoft.gadgetarium2j7.model.dto.NewsletterRequest;
import com.peaksoft.gadgetarium2j7.model.dto.NewsletterResponse;
import com.peaksoft.gadgetarium2j7.repository.NewsletterRepository;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsletterService {
    private final NewsletterMapper newsletterMapper;
    private final NewsletterRepository repository;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    public void save(NewsletterRequest request) {
        Newsletter newsletter = newsletterMapper.mapToEntity(request);
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            if (user.isNewsletter()) {
                sendMassage(newsletter,user.getEmail());
            }
        }
        repository.save(newsletter);
    }
    private void sendMassage(Newsletter newsletter, String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("temuchi500@gmail.com");
        message.setTo(email);
        message.setSubject(newsletter.getNameSender());
        message.setText(newsletter.getDescriptionSender());
        javaMailSender.send(message);
    }

    public NewsletterResponse update(Long newsId, NewsletterRequest request) {
        Newsletter oldApp = repository.findById(newsId)
                .orElseThrow(() -> new RuntimeException(" Newsletter not found by id: " + newsId));
        oldApp.setNameSender(request.getNameSender());
        oldApp.setDescriptionSender(request.getDescriptionSender());
        oldApp.setStartletter(request.getStartletter());
        oldApp.setEndletter(request.getEndletter());
        repository.save(oldApp);
        return newsletterMapper.mapToResponse(oldApp);
    }
    public void delete(Long appId) {
        repository.deleteById(appId);
    }
}
