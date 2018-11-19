package com.edu.cloud.downloader.clouddownloader.controller;

import com.edu.cloud.downloader.clouddownloader.ftp.FTPDownloader;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "ftp-controller")
public class FTPController {


    @Autowired
    FTPDownloader ftpDownloader;

    @PostMapping
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> download(@RequestBody DownloadParams downloadParams){
        boolean downloaded = ftpDownloader.download(downloadParams.getFromFtpLocation(), downloadParams.getToLocalLocation());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(downloaded);
    }

    @Data
    public static class DownloadParams {
        String fromFtpLocation;
        String toLocalLocation;
    }
}
