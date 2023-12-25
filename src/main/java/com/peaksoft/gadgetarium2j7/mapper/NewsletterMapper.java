package com.peaksoft.gadgetarium2j7.mapper;

import com.peaksoft.gadgetarium2j7.model.Newsletter;
import com.peaksoft.gadgetarium2j7.model.dto.NewsletterRequest;
import com.peaksoft.gadgetarium2j7.model.dto.NewsletterResponse;
import org.springframework.stereotype.Component;

@Component
public class NewsletterMapper {
    public Newsletter mapToEntity(NewsletterRequest request) {
        Newsletter newsletter = new Newsletter();
        newsletter.setNameSender(request.getNameSender());
        newsletter.setDescriptionSender(request.getDescriptionSender());
        newsletter.setStartletter(request.getStartletter());
        newsletter.setEndletter(request.getEndletter());
        return newsletter;
    }

    public NewsletterResponse mapToResponse(Newsletter newsletter) {
        return NewsletterResponse
                .builder()
                .id(newsletter.getId())
                .nameSender(newsletter.getNameSender())
                .descriptionSender(newsletter.getDescriptionSender())
                .startletter(newsletter.getStartletter())
                .endletter(newsletter.getEndletter())
                .build();
    }
}
