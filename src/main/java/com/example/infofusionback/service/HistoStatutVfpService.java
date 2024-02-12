package com.example.infofusionback.service;


import com.example.infofusionback.entity.HistoStatutVfp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HistoStatutVfpService {


    List<HistoStatutVfp> allHistoStatutVfp();

    HistoStatutVfp getHistoStatutVfpById(long id);

    HistoStatutVfp addHistoStatutVfp(HistoStatutVfp histoStatutVfp);

    void deleteHistoStatutVfp(long id);
}
