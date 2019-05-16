package com.myproj.target.ue.rest;

import com.myproj.target.ue.entity.ShortUrl;
import com.myproj.target.ue.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;

@RestController
public class UrlController {

  @Autowired
  private UrlService urlService;

  @GetMapping(path = "/{hash}")
  public RedirectView getOriginalUrl(@PathVariable("hash") String hash) {
    System.out.println("Received hash : " + hash);
    RedirectView redirectView = new RedirectView();
    ShortUrl url = urlService.getOriginalUrl(hash);
    try {
      System.out.println("Fetched value" + url.toString());
    } catch(EntityNotFoundException e) {
      System.out.println("Entity could not be found");
      redirectView.setUrl("http://www.404errorpages.com/");
      redirectView.setStatusCode(HttpStatus.NO_CONTENT);
      return redirectView;
    }
    redirectView.setUrl(url.getOriginalUrl());
    redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
    return redirectView;
  }

  @PostMapping(path = "/url", consumes = "application/json", produces = "application/json")
  public ShortUrl generateShortUrl(@RequestBody ShortUrl newUrl) {
    String originalUrl = newUrl.getOriginalUrl();
    newUrl = urlService.addUrl(originalUrl);
    System.out.println("Received url : " + originalUrl);
    return newUrl;
  }

}
