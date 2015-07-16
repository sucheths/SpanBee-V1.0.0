package com.spanbee.model;

import com.spanbee.entities.Customer;

public class CustomerPersonnelInfoModel {

  private int id;

  private String address;

  private int community;

  private int cuisine;

  private int customerId;

  private int drinking;

  private int height;

  private String hobbies;

  private int motherTongue;

  private int music;

  private int religion;

  private int smoking;

  private byte[] uploadPics;

  private int weekendActivity;

  private int weight;

  private Customer customer;

  public CustomerPersonnelInfoModel() {
  }

  public int getId() {
      return this.id;
  }

  public void setId(int id) {
      this.id = id;
  }

  public String getAddress() {
      return this.address;
  }

  public void setAddress(String address) {
      this.address = address;
  }

  public int getCommunity() {
      return this.community;
  }

  public void setCommunity(int community) {
      this.community = community;
  }

  public int getCuisine() {
      return this.cuisine;
  }

  public void setCuisine(int cuisine) {
      this.cuisine = cuisine;
  }

  public int getCustomerId() {
      return this.customerId;
  }

  public void setCustomerId(int customerId) {
      this.customerId = customerId;
  }

  public int getDrinking() {
      return this.drinking;
  }

  public void setDrinking(int drinking) {
      this.drinking = drinking;
  }

  public int getHeight() {
      return this.height;
  }

  public void setHeight(int height) {
      this.height = height;
  }

  public String getHobbies() {
      return this.hobbies;
  }

  public void setHobbies(String hobbies) {
      this.hobbies = hobbies;
  }

  public int getMotherTongue() {
      return this.motherTongue;
  }

  public void setMotherTongue(int motherTongue) {
      this.motherTongue = motherTongue;
  }

  public int getMusic() {
      return this.music;
  }

  public void setMusic(int music) {
      this.music = music;
  }

  public int getReligion() {
      return this.religion;
  }

  public void setReligion(int religion) {
      this.religion = religion;
  }

  public int getSmoking() {
      return this.smoking;
  }

  public void setSmoking(int smoking) {
      this.smoking = smoking;
  }

  public byte[] getUploadPics() {
      return this.uploadPics;
  }

  public void setUploadPics(byte[] uploadPics) {
      this.uploadPics = uploadPics;
  }

  public int getWeekendActivity() {
      return this.weekendActivity;
  }

  public void setWeekendActivity(int weekendActivity) {
      this.weekendActivity = weekendActivity;
  }

  public int getWeight() {
      return this.weight;
  }

  public void setWeight(int weight) {
      this.weight = weight;
  }

  public Customer getCustomer() {
      return this.customer;
  }

  public void setCustomer(Customer customer) {
      this.customer = customer;
  }

}
