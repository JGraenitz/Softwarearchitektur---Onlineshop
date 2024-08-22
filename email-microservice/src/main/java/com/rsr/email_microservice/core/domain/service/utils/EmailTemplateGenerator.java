package com.rsr.email_microservice.core.domain.service.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class EmailTemplateGenerator {

    private Configuration cfg;

    public EmailTemplateGenerator() {
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(this.getClass(), "/");
        cfg.setDefaultEncoding("UTF-8");
    }

    public String generateEmail(String templateName, Map<String, Object> dataModel) throws IOException, TemplateException {
        Template template = cfg.getTemplate(templateName);

        StringWriter out = new StringWriter();
        template.process(dataModel, out);
        return out.toString();
    }
}
