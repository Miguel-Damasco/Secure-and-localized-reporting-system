package com.example.proyect.dto.report;

import java.util.List;

public record ReportPageDTO(List<ReportResponseDTO> items, 
                                                        long totalRecords, 
                                                        int page, 
                                                        int pageSize,
                                                        int totalPages) {}
