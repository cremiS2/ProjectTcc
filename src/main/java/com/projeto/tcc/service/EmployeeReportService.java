package com.projeto.tcc.service;

import com.projeto.tcc.entities.Employee;
import com.projeto.tcc.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeReportService {

    private final EmployeeRepository repository;

    public byte[] gerarRelatorioFuncionarios() {
        try {
            // 1. Carregar o jrxml (coloque o arquivo em src/main/resources/reports/)
            InputStream reportStream = new ClassPathResource("reports/Relatorio_Funcionarios.jrxml").getInputStream();

            // 2. Compilar
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // 3. Dados
            List<Employee> funcionarios = repository.findAll();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(funcionarios);

            // 4. Parâmetros
            Map<String, Object> params = new HashMap<>();
            params.put("logo", "src/main/resources/static/logo.png");
            params.put("titulo", "Relatório de Funcionários");

            // 5. Preencher
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            // 6. Exportar
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório de funcionários", e);
        }
    }
}
