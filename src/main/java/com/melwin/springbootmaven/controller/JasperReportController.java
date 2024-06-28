package com.melwin.springbootmaven.controller;

import com.melwin.springbootmaven.service.JasperReportService;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class JasperReportController {
	@Autowired
	private JasperReportService jasperReportService;

	@GetMapping("/employees")
	public ResponseEntity<byte[]> getEmployeeReport() throws IOException {
		try {
			byte[] data = jasperReportService.generateEmployeeReport();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("attachment", "EmployeeReport.pdf");
			return ResponseEntity.ok().headers(headers).body(data);
		} catch (JRException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}
}
