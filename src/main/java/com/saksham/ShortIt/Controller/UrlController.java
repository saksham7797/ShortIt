package com.saksham.ShortIt.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saksham.ShortIt.DTO.CreateUrlRequest;
import com.saksham.ShortIt.DTO.UpdateUrlRequest;
import com.saksham.ShortIt.DTO.UrlResponse;
import com.saksham.ShortIt.Service.UrlService;
import com.saksham.ShortIt.exception.ControllerException;





@RestController
@RequestMapping("/ShortIt")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }
    
    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> createNewUrl(@RequestBody CreateUrlRequest request) {
        UrlResponse response = urlService.shorten(request);
        if(response == null) {
            throw new ControllerException("400, Bad Request");
        }

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/shorten/{shortCode}")
    public ResponseEntity<UrlResponse> getOrignialUrl(@PathVariable String shortCode) {
        UrlResponse response = urlService.getOriginalUrl(shortCode);
        if(response == null) {
            throw new ControllerException("404, Not Found");
        }

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/shorten/{shortCode}")
    public ResponseEntity<UrlResponse> putUpdateUrl(@PathVariable String shortCode, @RequestBody UpdateUrlRequest request) {
        UrlResponse response = urlService.updateUrl(request, shortCode);
        if(response == null) {
            throw new ControllerException("400, Bad Request");
        }

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/shorten/{shortCode}")
    public ResponseEntity<UrlResponse> deleteEntity(@PathVariable String shortCode) {
        UrlResponse response = urlService.deleteUrl(shortCode);
        if(response == null) {
            throw new ControllerException("404, Not Found");
        }

        return ResponseEntity.status(204).build();
    }
    
    @GetMapping("/shorten/{shortCode}/stats")
    public ResponseEntity<UrlResponse> getAccessCount(@PathVariable String shortCode) {
        UrlResponse response = urlService.getStats(shortCode);
        if(response == null) {
            throw new ControllerException("404, Not Found");
        }

        return ResponseEntity.status(200).body(response);
    }
    
}
