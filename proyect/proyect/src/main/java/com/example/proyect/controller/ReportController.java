package com.example.proyect.controller;

import java.net.URI;
import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.proyect.dto.report.ReportCreateDTO;
import com.example.proyect.dto.report.ReportListResponseDTO;
import com.example.proyect.dto.report.ReportResponseDTO;
import com.example.proyect.dto.response.ApiResponse;
import com.example.proyect.dto.response.ApiResponses;
import com.example.proyect.service.ReportService;

@RestController
@RequestMapping(path = "/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService pReportService) {
        this.reportService = pReportService;
    }

    
    @GetMapping("/test")
    public String getLocale(Locale pLocale) {

        return pLocale.toString();
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ReportResponseDTO>> create(@RequestBody ReportCreateDTO pRequest, Locale pLocale) {
     
        ReportResponseDTO response = this.reportService.create(pRequest, pLocale);

        URI location = ServletUriComponentsBuilder
                                                .fromCurrentRequest()
                                                .path("/{id}")
                                                .buildAndExpand(response.id())
                                                .toUri();

        return ResponseEntity.created(location).body(ApiResponses.success(
                                                                        response, 
                                                                        201,
                                                                        "Report successfully created!",
                                                                        location.getPath()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable(name = "id") long pId) {

        this.reportService.delete(pId);

         String path = ServletUriComponentsBuilder
                                                .fromCurrentRequest()
                                                .build()
                                                .getPath();

        return ResponseEntity.ok().body(ApiResponses.success(null, 
                                                        200, 
                                                        "Report successfull deleted!",
                                                        path));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<ReportListResponseDTO>> list(@RequestParam(defaultValue = "1", name = "page") int pPageRequest,
                                                                    @RequestParam(defaultValue = "10", name = "pageSize") int pPageSize,
                                                                    Locale pLocale) {

        ReportListResponseDTO response = this.reportService.getReports(pPageRequest, pPageSize, pLocale);

        String path = ServletUriComponentsBuilder
                                                .fromCurrentRequest()
                                                .build()
                                                .getPath();

        return ResponseEntity.ok(ApiResponses.success(response, 200, "Report list successgully retrived!", path));

    }
}
