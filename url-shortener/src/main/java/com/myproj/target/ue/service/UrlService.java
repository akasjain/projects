package com.myproj.target.ue.service;

import com.myproj.target.ue.dao.UrlDao;
import com.myproj.target.ue.entity.ShortUrl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UrlService {

  @Autowired
  private UrlDao urlDao;

  public ShortUrl getOriginalUrl(String hash) {
    return urlDao.getOne(hash);
  }

  @Transactional
  public ShortUrl addUrl(String originalUrl) {
    String urlHash = getUrlHash(originalUrl);
    ShortUrl url = new ShortUrl(originalUrl, urlHash);
    urlDao.save(url);
    return url;
  }

  public String getUrlHash(String url) {
    return DigestUtils.md5Hex(url).toLowerCase().substring(0,7);
  }

}
