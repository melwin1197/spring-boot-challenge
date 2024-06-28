package com.melwin.springbootmaven.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.melwin.springbootmaven.dto.DepartmentDTO;
import com.melwin.springbootmaven.dto.EmployeeDTO;
import com.melwin.springbootmaven.entity.Department;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JasperReportService {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ResourceLoader resourceLoader;

    public byte[] generateEmployeeReport() throws JRException, IOException {
        List<EmployeeDTO> employeeDTOs = employeeService.getAllEmployees();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeeDTOs);

        InputStream inputStream = resourceLoader.getResource("classpath:employees_report.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        Map<String, Object> parameters = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
