package com.example.proyect.service;

import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.proyect.dto.report.ReportCreateDTO;
import com.example.proyect.dto.report.ReportListResponseDTO;
import com.example.proyect.dto.report.ReportPageDTO;
import com.example.proyect.dto.report.ReportResponseDTO;
import com.example.proyect.exception.domain.UserNotFoundException;
import com.example.proyect.mapper.ReportMapper;
import com.example.proyect.model.ReportModel;
import com.example.proyect.repository.ReportRepository;

@Service
public class ReportService {
    
    private final ReportRepository reportRepository;

    private final ReportMapper reportMapper;

    public ReportService(ReportRepository pReportRepository, ReportMapper pReportMapper) {
        this.reportRepository = pReportRepository;
        this.reportMapper = pReportMapper;
    }

    public ReportResponseDTO create(ReportCreateDTO pRequest, Locale pLocale) {
        
        ReportModel myReport = new ReportModel();

        myReport.setCreationDate(Instant.now());
        myReport.setLocale(pLocale.toString());
        myReport.setPrice(pRequest.price());

       ReportModel mySavedReport = this.reportRepository.save(myReport);

       return this.reportMapper.toResponse(mySavedReport, pLocale);
    }

    public void delete(long id) {

        Optional<ReportModel> myReportOptional = this.reportRepository.findById(id);

        if(!myReportOptional.isPresent()) {
            throw new UserNotFoundException(id);
        }

        this.reportRepository.delete(myReportOptional.get());
    }

    public ReportListResponseDTO getReports(int pPage, int pPageSize, Locale pLocale) {

        int safePage = Math.max(pPage, 1);
        int safePageSize = Math.min(pPageSize, 50);

        Pageable pageable = PageRequest.of(safePage - 1, safePageSize);

        Page<ReportModel> myItems = this.reportRepository.findAll(pageable);

        List<ReportResponseDTO> mylist = myItems.stream()
                                                    .map(report -> reportMapper.toResponse(report, pLocale))
                                                    .toList();

        return new ReportListResponseDTO(new ReportPageDTO(mylist, 
                                                            myItems.getNumberOfElements(),
                                                            safePage, 
                                                            safePageSize, 
                                                            myItems.getTotalPages()));
    }

    
}
