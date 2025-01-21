package com.raysi.onetoonewithspringboot.dto;

import com.raysi.onetoonewithspringboot.entity.Engine;

import java.util.function.Function;

public class EngineDTOMapper implements Function<Engine, EngineDTO> {

    @Override
    public EngineDTO apply(Engine engine) {
        return new EngineDTO(
                engine.getCompanyName(),
                engine.getHp(),
                engine.getNoOfCylinders(),
                engine.getEngineType()
        );
    }
}
