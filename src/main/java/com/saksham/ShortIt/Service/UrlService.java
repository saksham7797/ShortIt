package com.saksham.ShortIt.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.saksham.ShortIt.DTO.CreateUrlRequest;
import com.saksham.ShortIt.DTO.UpdateUrlRequest;
import com.saksham.ShortIt.DTO.UrlResponse;
import com.saksham.ShortIt.Entity.UrlMapping;
import com.saksham.ShortIt.repository.UrlRepository;

@org.springframework.stereotype.Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;


    public UrlResponse shorten(CreateUrlRequest request) {
        String shortCode = UUID.randomUUID().toString().substring(0,6);
        while(urlRepository.existsByShortCode(shortCode)) {
            shortCode = UUID.randomUUID().toString().substring(0,6);
        }
        UrlMapping obj = new UrlMapping();
        obj.setOriginalUrl(request.getOriginalUrl());
        obj.setShortCode(shortCode);
        obj.setCreatedAt(LocalDateTime.now());
        obj.setUpdatedAt(LocalDateTime.now());
        urlRepository.save(obj);
        UrlResponse response = new UrlResponse(obj.getId(), obj.getOriginalUrl(), obj.getShortCode(), obj.getCreatedAt(), obj.getUpdatedAt(), obj.getAccessCount()); 
        return response;   
    }

    public UrlResponse getOriginalUrl(String shortCode) {
        Optional<UrlMapping> obj = urlRepository.findByShortCode(shortCode);
        if(obj.isEmpty()) {
            return null;
        }
        UrlMapping url = obj.get();
        url.setAccessCount(url.getAccessCount()+1);
        urlRepository.save(url);

        UrlResponse response = new UrlResponse(url.getId(), url.getOriginalUrl(), url.getShortCode(), url.getCreatedAt(), url.getUpdatedAt(), url.getAccessCount()); 
        return response;
    } 

    public UrlResponse updateUrl(UpdateUrlRequest request, String shortCode) {
        Optional<UrlMapping> obj = urlRepository.findByShortCode(shortCode);
        if(obj.isEmpty()) {
            return null;
        }
        UrlMapping url = obj.get();
        url.setOriginalUrl(request.getOriginalUrl());
        url.setUpdatedAt(LocalDateTime.now());
        urlRepository.save(url);
        
        UrlResponse response = new UrlResponse(url.getId(), url.getOriginalUrl(), url.getShortCode(), url.getCreatedAt(), url.getUpdatedAt(), url.getAccessCount()); 
        return response;
    }

    public UrlResponse deleteUrl(String shortCode) {
        Optional<UrlMapping> obj = urlRepository.findByShortCode(shortCode);
        if(obj.isEmpty()) {
            return null;
        }
        UrlMapping url = obj.get();
        
        UrlResponse response = new UrlResponse(url.getId(), url.getOriginalUrl(), url.getShortCode(), url.getCreatedAt(), url.getUpdatedAt(), url.getAccessCount()); 


        urlRepository.deleteById(url.getId());
        return response;
    }

    public UrlResponse getStats(String shortCode) {
        Optional<UrlMapping> obj = urlRepository.findByShortCode(shortCode);
        if(obj.isEmpty()) {
            return null;
        }
        UrlMapping url = obj.get();
        
        UrlResponse response = new UrlResponse(url.getId(), url.getOriginalUrl(), url.getShortCode(), url.getCreatedAt(), url.getUpdatedAt(), url.getAccessCount()); 
        return response;
    }
    
}
