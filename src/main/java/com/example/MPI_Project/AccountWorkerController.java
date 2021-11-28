package com.example.MPI_Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.MPI_Project.domain.Finances;
import com.example.MPI_Project.domain.Report;
import com.example.MPI_Project.repos.FinancesRepo;
import com.example.MPI_Project.repos.ReportRepo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountWorkerController {
    @Autowired
    private FinancesRepo financesRepo;
    @Autowired
    private ReportRepo reportRepo;

    public void putVariables(Map<String, Object> model, String title, String text) {
        Iterable<Report> reports = reportRepo.findAll();
        model.put("reports", reports);
    }
    public void putFinance(Map<String, Object> model, String date, Double amount, String type) {
        Iterable<Finances> finances = financesRepo.findAll();
        model.put("finances", finances);
        model.put("finances_date", date);
        model.put("finances_amount", amount);
        model.put("finances_type", type);
    }

    public List<Finances> findFinances(String type) {
        return financesRepo.findByType(type);
    }

    @GetMapping
    public String start(Map<String, Object> model) {
        putVariables(model, "", "");
        return "account_temp";
    }

    @PostMapping("/create_finance")
    public String createNewFinance (
            @RequestParam String date,
            @RequestParam Double amount,
            @RequestParam String type,
            Map<String, Object> model) {
        if (date == null)
            date = "";
        if (amount == null)
            amount = 0.0;
        if (type == null)
            type = "";
        Finances finance = new Finances(date, amount, type);
        putFinance(model, "", 0.0, "");
        financesRepo.save(finance);

        putVariables(model, "", "");
        return "account_temp";
    }

    @PostMapping("/create_report_by_type")
    public String createReportByType (
            @RequestParam String type,
            Map<String, Object> model) {
        Report report = new Report();

        List<Finances> fin = findFinances(type);
        String date = new SimpleDateFormat("dd-M-yyyy hh:mm:ss").format(new Date());
        report.setTitle("Report: " + date + ". Type: " + type);
        ArrayList<String> text = new ArrayList<String>();
        Double sum = 0.0;

        for (Finances item : fin) {
            Double amount = item.getAmount();
            if (amount == null)
                amount = 0.0;
            text.add("Date: " + item.getDate() + ", Amount: " + amount + "\n");
            sum += amount;
        }
        if (text.size() == 0) {
            report.setText("Записей не найдено");
        } else {
            String reportsAsString = "";
            for (String line : text) {
                reportsAsString = reportsAsString + line + "\n";
            }
            report.setText(reportsAsString);
            report.setSum(sum);
        }
        reportRepo.save(report);
        model.put("report", report);
        model.put("report_title", report.getTitle());
        model.put("report_text", report.getText());
        Double r_sum = report.getSum();
        if (r_sum == null)
            r_sum = 0.0;
        model.put("report_sum", r_sum);
        putVariables(model, "", "");

        return "account_temp";
    }

    @PostMapping("/choose")
    public String chooseReport (
            @RequestParam String title,
            Map<String, Object> model) {

        List<Report> report = reportRepo.findByTitle(title);
        Report r = report.get(0);
        String r_title = r.getTitle();
        String r_text = r.getText();

        putVariables(model, r_title, r_text);

        return "account_temp";
    }

    @PostMapping("/delete")
    public String deleteTask (@RequestParam Integer deleteReport_id, Map<String, Object> model) {
        reportRepo.deleteById(deleteReport_id);
        putVariables(model, "",  "");
        return "account_temp";
    }

    @PostMapping("/exit")
    public String goToMain (Map<String, Object> model) {
        return "redirect:/main";
    }
}
