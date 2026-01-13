package com.example.proyect.service;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Service;

import com.example.proyect.dto.report.ReportCreateDTO;
import com.example.proyect.dto.report.ReportResponseDTO;
import com.example.proyect.model.ReportModel;
import com.example.proyect.repository.ReportRepository;

@Service
public class ReportService {
    
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository pReportRepository) {
        this.reportRepository = pReportRepository;
    }

    public ReportResponseDTO create(ReportCreateDTO pRequest, Locale pLocale) {
        
        ReportModel myReport = new ReportModel();

        myReport.setCreationDate(Instant.now());
        myReport.setLocale(pLocale.toString());
        myReport.setPrice(pRequest.price());

       ReportModel mySavedReport = this.reportRepository.save(myReport);

       var rb = ResourceBundle.getBundle("i18n.messages", pLocale);

       String resourceTitle = rb.getString("report.title");

       String resourceDate = rb.getString("report.createdAt");

       String resourcePrice = rb.getString("report.price");

        LocalDateTime date = LocalDateTime.ofInstant(mySavedReport.getCreationDate(), ZoneId.systemDefault());

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

       String dateFormatted = MessageFormat.format(resourceDate, dateFormatter.format(date));

       NumberFormat currenyFormat = NumberFormat.getCurrencyInstance(pLocale);

       String priceFormatter = currenyFormat.format(mySavedReport.getPrice().doubleValue());

       String priceFormatted = MessageFormat.format(resourcePrice, priceFormatter);

       String message = dateFormatted + " " + priceFormatted;

        return new ReportResponseDTO(mySavedReport.getId(), resourceTitle, message, mySavedReport.getPrice().doubleValue(), mySavedReport.getLocale());

    }
    
}
