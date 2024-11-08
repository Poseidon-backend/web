package com.proger4.webproger4.repository;

import com.proger4.webproger4.model.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface DataRepository extends JpaRepository<DataEntity, Long> {
    }

