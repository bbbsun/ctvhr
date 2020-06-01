package com.bbbsun.ctvhr.service;

import com.bbbsun.ctvhr.mapper.PoliticsstatusMapper;
import com.bbbsun.ctvhr.model.Politicsstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticsstatusService {

    @Autowired
    private PoliticsstatusMapper politicsstatusMapper;

    public List<Politicsstatus> getAllpoliticsstatus() {
        return politicsstatusMapper.getAllpoliticsstatus();

    }
}
