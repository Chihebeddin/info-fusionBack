package com.example.infofusionback.controller;


import com.example.infofusionback.entity.Category;
import com.example.infofusionback.entity.HistoStatutVfp;
import com.example.infofusionback.service.HistoStatutVfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HistoStatutVfp")
public class HistoStatutVfpController {


    @Autowired
    protected HistoStatutVfpService histoStatutVfpService;

    @GetMapping("/{id}")
    public HistoStatutVfp histoStatutVfpById(@PathVariable long id) {
        return histoStatutVfpService.getHistoStatutVfpById(id);
    }

    @GetMapping()
    public List<HistoStatutVfp> allHistoStatutVfp() {
        return histoStatutVfpService.allHistoStatutVfp();
    }

    @PostMapping("/create")
    public HistoStatutVfp createHistoStatutVfp(@RequestBody HistoStatutVfp histoStatutVfp) {
        return histoStatutVfpService.addHistoStatutVfp(histoStatutVfp);
    }

    @DeleteMapping("/{id}")
    public void deleteHistoStatutVfp(@PathVariable long id) {
        this.histoStatutVfpService.deleteHistoStatutVfp(id);
    }
}
