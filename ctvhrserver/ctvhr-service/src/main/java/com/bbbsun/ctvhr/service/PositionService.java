package com.bbbsun.ctvhr.service;

import com.bbbsun.ctvhr.mapper.PositionMapper;
import com.bbbsun.ctvhr.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PositionService {
    @Autowired
    private PositionMapper positionMapper;

    public List<Position> getAllPosition() {
        return positionMapper.getAllPosition();
    }

    public int addPosition(Position position) {
        return positionMapper.insertSelective(position);
    }

    public int updatePosition(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public int deleteById(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    public int deletePositionByIds(Integer[] ids) {
        return positionMapper.deletePositionByIds(ids);
    }
}
