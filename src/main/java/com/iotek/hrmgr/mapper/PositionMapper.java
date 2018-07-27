package com.iotek.hrmgr.mapper;

import com.iotek.hrmgr.entity.Dept;
import com.iotek.hrmgr.entity.Position;

import java.util.List;

public interface PositionMapper {
    public Position selectPositionById(int id);

    public List<Position> selectAllPositions();

    public void insertPosition(Position position);

    //需要id
    public void updatePosition(Position position);

    public void deletePositionById(int id);
} 