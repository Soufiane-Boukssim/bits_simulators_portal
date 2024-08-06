package com.simulator.calculatorsCrypto.controller;

import com.simulator.calculatorsCrypto.service.LisParserMBService;
import com.simulator.globalModels.ResponseApiJson;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/su/tools/lisparser")
@RequiredArgsConstructor
public class LisParserMBController {


    private final Logger logger = LogManager.getLogger(DESDE055Controller.class);

    @Autowired
    private LisParserMBService lisParserMBService;

    @PostMapping("/upload")
    public ResponseApiJson<?> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        logger.info("#################### Start uploadFile ####################");
        ResponseApiJson<?> responseApiJson = lisParserMBService.uploadFile(file);
        return responseApiJson;

    }




    @PostMapping("/show_details")
    public ResponseApiJson<?> showDetails(@RequestBody Map<String, String> requestData) throws Exception {
        logger.info("#################### Start showDetails ####################");
        String data = requestData.get("data");
        ResponseApiJson<?> responseApiJson = lisParserMBService.showDetails(data);
        return responseApiJson;

    }
}
