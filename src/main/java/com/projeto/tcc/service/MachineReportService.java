package com.projeto.tcc.service;

import com.projeto.tcc.entities.Machine;
import com.projeto.tcc.repository.MachineRepository;
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
public class MachineReportService {

    private final MachineRepository machineRepository;

    public byte[] gerarRelatorioMaquinas() {
        try {
            // 1. Carregar o jrxml do classpath (ex: src/main/resources/reports/Relatorio_Maquinas.jrxml)
            InputStream reportStream = new ClassPathResource("reports/Relatorio_Maquinas.jrxml").getInputStream();

            // 2. Compilar o relatório
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // 3. Buscar dados
            List<Machine> maquinas = machineRepository.findAll();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(maquinas);

            // 4. Parâmetros
            Map<String, Object> params = new HashMap<>();
            params.put("logo", "src/main/resources/static/logo.png"); // ou URL
            params.put("titulo", "Relatório de Máquinas");

            // 5. Preencher
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            // 6. Exportar para PDF (byte[])
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório de máquinas", e);
        }
    }
}
