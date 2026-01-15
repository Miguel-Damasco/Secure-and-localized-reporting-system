package com.example.proyect.mapper;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.example.proyect.dto.report.ReportResponseDTO;
import com.example.proyect.model.ReportModel;

@Component
public class ReportMapper {
    
    public ReportResponseDTO toResponse(ReportModel pReport, Locale pLocale) {

        var rb = ResourceBundle.getBundle("i18n.messages", pLocale);

       String resourceTitle = rb.getString("report.title");

       String resourceDate = rb.getString("report.createdAt");

       String resourcePrice = rb.getString("report.price");

        LocalDateTime date = LocalDateTime.ofInstant(pReport.getCreationDate(), ZoneId.systemDefault());

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

       String dateFormatted = MessageFormat.format(resourceDate, dateFormatter.format(date));

       NumberFormat currenyFormat = NumberFormat.getCurrencyInstance(pLocale);

       String priceFormatter = currenyFormat.format(pReport.getPrice().doubleValue());

       String priceFormatted = MessageFormat.format(resourcePrice, priceFormatter);

       String message = dateFormatted + " " + priceFormatted;

        return new ReportResponseDTO(pReport.getId(), 
                                        resourceTitle, 
                                        message, 
                                        pReport.getPrice().doubleValue(), 
                                        pReport.getLocale());
    }
}
