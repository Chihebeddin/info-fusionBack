package com.example.infofusionback.repository;


import com.example.infofusionback.entity.Client;
import com.example.infofusionback.entity.HistoStatutVfp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoStatutVfpRepository  extends CrudRepository<HistoStatutVfp, Long> {

    HistoStatutVfp findById(long id);

    List<HistoStatutVfp> findAll();


}
