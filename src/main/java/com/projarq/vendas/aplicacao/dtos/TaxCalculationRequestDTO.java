package com.projarq.vendas.aplicacao.dtos;

public class TaxCalculationRequestDTO {
    private String state;
    private double value;

    // Default constructor
    public TaxCalculationRequestDTO() {}

    // Constructor with parameters
    public TaxCalculationRequestDTO(String state, double value) {
        this.state = state;
        this.value = value;
    }

    // Getters and setters
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
} 