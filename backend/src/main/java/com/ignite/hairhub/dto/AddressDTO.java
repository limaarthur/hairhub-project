package com.ignite.hairhub.dto;

import com.ignite.hairhub.entity.Address;

import java.util.UUID;

public class AddressDTO {

    private UUID id;

    private String street;

    private String city;

    private String uf;

    private String zipCode;

    public AddressDTO() {
    }

    public AddressDTO(UUID id, String street, String city, String uf, String zipCode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.uf = uf;
        this.zipCode = zipCode;
    }

    public AddressDTO(Address entity) {
        id = entity.getId();
        street = entity.getStreet();
        city = entity.getCity();
        uf = entity.getUf();
        zipCode = entity.getZipCode();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
