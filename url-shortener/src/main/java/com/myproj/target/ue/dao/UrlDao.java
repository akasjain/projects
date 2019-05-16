package com.myproj.target.ue.dao;

import com.myproj.target.ue.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlDao extends JpaRepository<ShortUrl, String> {

}
