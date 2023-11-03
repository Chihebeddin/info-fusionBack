package com.example.infofusionback.entity;

public abstract class AbstractBOEntityConverter<Entity, DTO> {
    public abstract Entity convertToEntity(DTO dto);

    public abstract DTO convertToBO(Entity entity);
}
