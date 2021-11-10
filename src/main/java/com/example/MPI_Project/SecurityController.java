package com.example.MPI_Project;

import com.example.MPI_Project.triffid_containment_simulation.Cell;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.awt.*;
import java.util.Map;

@Controller
@RequestMapping("/security")
public class SecurityController {
    private Cell cell_1 = new Cell();
    private Cell cell_2 = new Cell();
    private Cell cell_3 = new Cell();
    private Cell cell_4 = new Cell();
    private Cell cell_5 = new Cell();

    private String condition_1;
    private String condition_2;
    private String condition_3;
    private String condition_4;
    private String condition_5;

    private String ImageName = "/camera/no_signal.jpg";

    private int camera_number = 1;

    String updateCondition(Cell cell){
        cell.update();
        String condition = "Everything is OK.";
        if (!cell.isOpen()) {
            condition = "Everything is OK.";
        }
        else if (cell.isOpen() && !cell.isEmpty()) {
            condition = "Attention! Cell is open!";
        } else if (cell.isEmpty()) {
            condition = "ALERT! Triffid escaped!";
        }
        return condition;
    }

    String buildImageName(Cell cell){
        String imageName = "/camera/no_signal.jpg";
        String xCoord = Integer.toString(cell.triffidPosition()[0]);
        String yCoord = Integer.toString(cell.triffidPosition()[1]);

        if (cell.isEmpty()) {
            imageName = "/camera/empty.jpg";
            return imageName;
        } else if(cell.isOpen()) {
            imageName = "/camera/opened/";
        } else {
            imageName = "/camera/closed/";
        }
        imageName = imageName+xCoord+yCoord+".jpg";

        return imageName;
    }

    @GetMapping
    public String start(Map<String, Object> model) {

        condition_1 = updateCondition(cell_1);
        condition_2 = updateCondition(cell_2);
        condition_3 = updateCondition(cell_3);
        condition_4 = updateCondition(cell_4);
        condition_5 = updateCondition(cell_5);

        switch (camera_number){
            case 1:
                ImageName = buildImageName(cell_1);
                break;
            case 2:
                ImageName = buildImageName(cell_2);
                break;
            case 3:
                ImageName = buildImageName(cell_3);
                break;
            case 4:
                ImageName = buildImageName(cell_4);
                break;
            case 5:
                ImageName = buildImageName(cell_5);
                break;
        }
        System.out.println(ImageName);
        model.put("ImageName", ImageName);
        model.put("condition_1", condition_1);
        model.put("condition_2", condition_2);
        model.put("condition_3", condition_3);
        model.put("condition_4", condition_4);
        model.put("condition_5", condition_5);
        model.put("camera_number", camera_number);
        return "security_temp";
    }

    @PostMapping("/next")
    public String nextCamera (Map<String, Object> model) {
        camera_number += 1;
        if (camera_number > 5) {
            camera_number = 1;
        }
        model.put("ImageName", ImageName);
        model.put("condition_1", condition_1);
        model.put("condition_2", condition_2);
        model.put("condition_3", condition_3);
        model.put("condition_4", condition_4);
        model.put("condition_5", condition_5);
        model.put("camera_number", camera_number);
        return "security_temp";
    }

    @PostMapping("/prev")
    public String prevCamera (Map<String, Object> model) {
        camera_number -= 1;
        if (camera_number < 1) {
            camera_number = 5;
        }
        model.put("ImageName", ImageName);
        model.put("condition_1", condition_1);
        model.put("condition_2", condition_2);
        model.put("condition_3", condition_3);
        model.put("condition_4", condition_4);
        model.put("condition_5", condition_5);
        model.put("camera_number", camera_number);
        return "security_temp";
    }
}
