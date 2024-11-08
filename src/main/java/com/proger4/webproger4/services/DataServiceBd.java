package com.proger4.webproger4.services;

import com.proger4.webproger4.model.DataEntity;
import com.proger4.webproger4.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DataServiceBd {

    private final DataRepository dataRepository;

    @Autowired
    public DataServiceBd(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

   public DataEntity saveDates(Date date1, Date date2) {
            DataEntity dataEntity = new DataEntity();
            dataEntity.setDate1(date1);
            dataEntity.setDate2(date2);
            return dataRepository.save(dataEntity);
        }
    }

