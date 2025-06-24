package com.ignite.hairhub.dto;

import com.ignite.hairhub.entity.Establishment;

import java.util.UUID;

public class EstablishmentDTO {

    private UUID id;

    private String name;

    private String description;

    private String cnpj;

    private String imgUrl;

    public EstablishmentDTO() {
    }

    public EstablishmentDTO(UUID id, String name, String description, String cnpj, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cnpj = cnpj;
        this.imgUrl = imgUrl;
    }

    public EstablishmentDTO(Establishment entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        cnpj = entity.getCnpj();
        imgUrl = entity.getImgUrl();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
