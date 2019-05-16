package com.myproj.target.ue.entity;

import javax.persistence.*;

@Entity
@Table(name = "short_url")
public class ShortUrl {
  @Id
  private String hash;

  private String originalUrl;

  public ShortUrl() {
  }

  public ShortUrl(String originalUrl, String hash) {
    this.originalUrl = originalUrl;
    this.hash = hash;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public void setOriginalUrl(String originalUrl) {
    this.originalUrl = originalUrl;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  @Override
  public String toString() {
    return "ShortUrl{" +
      ", originalUrl='" + originalUrl + '\'' +
      ", hash='" + hash + '\'' +
      '}';
  }
}
