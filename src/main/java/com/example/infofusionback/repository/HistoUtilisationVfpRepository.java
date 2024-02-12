package com.example.infofusionback.repository;


import com.example.infofusionback.entity.HistoStatutVfp;
import com.example.infofusionback.entity.HistoUtilisationVfp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoUtilisationVfpRepository extends CrudRepository<HistoUtilisationVfp, Long> {


    HistoUtilisationVfp findById(long id);

    List<HistoUtilisationVfp> findAll();
}
