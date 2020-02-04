package com.exam.demo;

public class Zipcode {
  private int lower_bound;
  private int upper_bound;

  public Zipcode(int lower_bound, int upper_bound) {
    this.lower_bound = lower_bound;
    this.upper_bound = upper_bound;
  }

  public int getLower_bound() {
    return lower_bound;
  }

  public void setLower_bound(int lower_bound) {
    this.lower_bound = lower_bound;
  }

  public int getUpper_bound() {
    return upper_bound;
  }

  public void setUpper_bound(int upper_bound) {
    this.upper_bound = upper_bound;
  }

}
