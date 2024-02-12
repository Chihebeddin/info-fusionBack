package com.example.infofusionback.service;


import com.example.infofusionback.entity.HistoUtilisationVfp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HistoUtilisationVfpService {


    List<HistoUtilisationVfp> allHistoUtilisationVfp();

    HistoUtilisationVfp getHistoUtilisationVfpById(long id);

    HistoUtilisationVfp addHistoUtilisationVfp(HistoUtilisationVfp histoUtilisationVfp);

    void deleteHistoUtilisationVfp(long id);
}
