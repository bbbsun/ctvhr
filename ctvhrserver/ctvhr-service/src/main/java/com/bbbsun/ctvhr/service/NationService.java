package com.bbbsun.ctvhr.service;

import com.bbbsun.ctvhr.mapper.NationMapper;
import com.bbbsun.ctvhr.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationService {

    @Autowired
    private NationMapper nationMapper;

    public List<Nation> getAllNations() {
        return nationMapper.getAllNations();
    }
}
