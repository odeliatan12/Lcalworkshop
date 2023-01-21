package calculator.practicing_exercise.controller;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import calculator.practicing_exercise.model.LoveResult;
import calculator.practicing_exercise.service.LoveCalculator;

@Controller
@RequestMapping(path = "/list")
public class LoveController {
    
    @Autowired
    private LoveCalculator loveCalculator;

    @GetMapping
    public String getLove(@RequestParam(required = true) String fname, @RequestParam(required = true) String sname, Model model ) throws IOException, InterruptedException{
        LoveResult lv = loveCalculator.getDescription(fname, sname);

        System.out.println(lv);
        model.addAttribute("Compatibility", lv);
        return "love";
    }
}
