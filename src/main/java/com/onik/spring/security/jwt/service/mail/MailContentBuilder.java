package ai_tech.service.mail;

import ai_tech.service.mail.dto.RegContentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class MailContentBuilder {

    private final TemplateEngine templateEngine;

    public String build(RegContentDTO contentDto, String template) {
        Context context = new Context();
        context.setVariable("mailContent", contentDto);
        return templateEngine.process(template, context);
    }
}
