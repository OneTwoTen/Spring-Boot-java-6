package com.Java6.rest.controller;

import java.io.File;
import java.util.UUID;

import javax.websocket.server.PathParam;

import com.Java6.service.UploadService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadRestController {
    @Autowired
    UploadService uploadService;

    @PostMapping(value = "/rest/upload/{folder}")
    public JsonNode upload(@PathParam("file") MultipartFile file, @PathVariable("folder") String folder) {
        String uuid = UUID.randomUUID().toString();

        File saveFile = uploadService.save(file, folder);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("name", saveFile.getName());
        node.put("size", saveFile.length());
        return node;
    }
}
