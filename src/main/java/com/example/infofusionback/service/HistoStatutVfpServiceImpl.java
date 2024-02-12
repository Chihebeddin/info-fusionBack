package com.example.infofusionback.service;


import com.example.infofusionback.entity.HistoStatutVfp;
import com.example.infofusionback.repository.HistoStatutVfpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoStatutVfpServiceImpl implements HistoStatutVfpService {

    @Autowired
    protected HistoStatutVfpRepository repo;


    @Override
    public List<HistoStatutVfp> allHistoStatutVfp() {
        return repo.findAll();
    }

    @Override
    public HistoStatutVfp getHistoStatutVfpById(long id) {


        return repo.findById(id);
    }

    @Override
    public HistoStatutVfp addHistoStatutVfp(HistoStatutVfp histoStatutVfp) {
        return repo.save(histoStatutVfp);

    }

    @Override
    public void deleteHistoStatutVfp(long id) {
        repo.deleteById(id);

    }
}
