package com.example.android.autovia.CarCare;

/**
 * Created by Abdulrhman on 16/08/2017.
 */

public class CarCareModel {
    private String nameService;
    private int priceService;
    private String imageService;

    public CarCareModel(String nameService, int priceService, String imageService) {
        this.nameService = nameService;
        this.priceService = priceService;
        this.imageService = imageService;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public int getPriceService() {
        return priceService;
    }

    public void setPriceService(int priceService) {
        this.priceService = priceService;
    }

    public String getImageService() {
        return imageService;
    }

    public void setImageService(String imageService) {
        this.imageService = imageService;
    }
}
