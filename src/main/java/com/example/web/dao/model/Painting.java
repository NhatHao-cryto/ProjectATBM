package com.example.web.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Painting implements Serializable{
        private int id;
        private String title;
        private double price;
        private String description;
        private String imageUrl;
        private String artistName;
        private String themeName;
        private List<PaintingSize> sizes = new ArrayList<>();
        private String discountName;
        private double discountPercentage;
        private boolean available;
        private Date crateDate;
        private boolean isFeatured;
        private Date createDate;
        private double averageRating;

        public Painting(int id, String title, double price, String description, String imageUrl, String artistName, String themeName, boolean isFeatured, Date createDate, double averageRating) {
            this.id = id;
            this.title = title;
            this.price = price;
            this.description = description;
            this.imageUrl = imageUrl;
            this.artistName = artistName;
            this.themeName = themeName;
            this.isFeatured = isFeatured;
            this.createDate = createDate;
            this.averageRating = averageRating;
        }
    public Painting(int id, String title, double price, String description, String imageUrl, String artistName, String themeName, boolean isFeatured, Date createDate, double averageRating, boolean isSold) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.artistName = artistName;
        this.themeName = themeName;
        this.isFeatured = isFeatured;
        this.createDate = createDate;
        this.averageRating = averageRating;
        this.available = isSold;
    }

    public Painting() {

    }


    public Painting(int id, String title, String imageUrl, String artistName, Double price) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.artistName = artistName;
        this.price = price;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void addSize(int idSize, String sizeDescription, int quantity) {
            this.sizes.add(new PaintingSize(idSize, sizeDescription, quantity));
        }
        public void setDiscount(String discountName, double discountPercentage) {
            this.discountName = discountName;
            this.discountPercentage = discountPercentage;
        }

    public boolean isAvailable(){
        return this.available;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Date getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(Date crateDate) {
        this.crateDate = crateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public List<PaintingSize> getSizes() {
        return sizes;
    }

    public void setSizes(List<PaintingSize> sizes) {
        this.sizes = sizes;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }



    @Override
    public String toString() {
        return "Painting{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", artistName='" + artistName + '\'' +
                ", themeName='" + themeName + '\'' +
                ", sizes=" + sizes +
                ", createDate=" + createDate +
                ", discountName='" + discountName + '\'' +
                ", isFu='" + isFeatured + '\'' +
                ", raiting='" + averageRating + '\'' +
                ", isSold='" + available + '\'' +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
    public boolean getAvailable() {
            return available;
    }


    public void setAverageRating(double averageRating) {
            this.averageRating = averageRating;
    }
}
