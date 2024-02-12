package com.example.infofusionback.controller;


import com.example.infofusionback.entity.HistoUtilisationVfp;
import com.example.infofusionback.service.HistoUtilisationVfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HistoUtilisationVfp")
public class HistoUtilisationVfpController {


    @Autowired
    protected HistoUtilisationVfpService histoUtilisationVfpService;

    @GetMapping("/{id}")
    public HistoUtilisationVfp histoUtilisationVfpById(@PathVariable long id) {
        return histoUtilisationVfpService.getHistoUtilisationVfpById(id);
    }

    @GetMapping()
    public List<HistoUtilisationVfp> allHistoUtilisationVfp() {
        return histoUtilisationVfpService.allHistoUtilisationVfp();
    }

    @PostMapping("/create")
    public HistoUtilisationVfp createHistoUtilisationVfp(@RequestBody HistoUtilisationVfp histoUtilisationVfp) {
        return histoUtilisationVfpService.addHistoUtilisationVfp(histoUtilisationVfp);
    }

    @DeleteMapping("/{id}")
    public void deleteHistoUtilisationVfp(@PathVariable long id) {
        histoUtilisationVfpService.deleteHistoUtilisationVfp(id);
    }
}
