package com.example.infofusionback.service;


import com.example.infofusionback.entity.HistoUtilisationVfp;
import com.example.infofusionback.repository.HistoUtilisationVfpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoUtilisationVfpImpl  implements HistoUtilisationVfpService {

    @Autowired
    protected HistoUtilisationVfpRepository repo;

    @Override
    public List<HistoUtilisationVfp> allHistoUtilisationVfp() {
        return repo.findAll();
    }

    @Override
    public HistoUtilisationVfp getHistoUtilisationVfpById(long id) {
        return repo.findById(id);
    }

    @Override
    public HistoUtilisationVfp addHistoUtilisationVfp(HistoUtilisationVfp histoUtilisationVfp) {
        return repo.save(histoUtilisationVfp);
    }

    @Override
    public void deleteHistoUtilisationVfp(long id) {
        repo.deleteById(id);
    }
}
