package com.simulator.EMVTester.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.EMVTester.dto.Emv;
import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Service

public class ProcessingRestrictionService {
    private final Logger logger = LogManager.getLogger(LogNDCController.class);


    public ResponseApiJson<List<Emv>> processingRestriction(Object body) {
        String idRequest = "Initiate_Application_" + new Date().getTime() + (Math.random() * 9999);
        List<Emv> emvList = new ArrayList<>();
//        logger.info("---->body");
        try {
            emvList.add(createEmvLog("Offline Authentification Processing in Progress ..."));
            // Conversion du corps en objet JsonNode
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.convertValue(body, JsonNode.class);

            // Fonction pour trouver la valeur correspondante à "Name": "82"
            String tag82Value = findTagValue(rootNode, "82");
            String binaryValue = Hexa_To_Bin(tag82Value);

            String tag8FValue = findTagValue(rootNode, "8F");
            String tag90Value = findTagValue(rootNode, "90");
            String tag92Value = findTagValue(rootNode, "92");
            String tag9F32Value = findTagValue(rootNode, "9F32");
            String tag93Value = findTagValue(rootNode, "93");


//            if(tag82Value!=null){
//                analyzeAIPBinary(binaryValue,emvList,tag8FValue,tag90Value,tag92Value,tag9F32Value,tag93Value);
//            }

            if (binaryValue != null && binaryValue.charAt(2) == '1') {
                emvList.add(createEmvLog("DDA Processing ..."));

            }
//            String  certificatPKI =   get dans file certifica
//            if (binaryValue != null && binaryValue.charAt(1) == '1') {
//                emvList.add(createEmvLog("Static Data Authetication (SDA) Processing in Progress ..."));
//
//                if (certificatPKI == null) {
//                    emvList.add(createEmvLog("Certificate not found for [" +nameApplication+ "] Index [" + tag8FValue + "]"));
//
//
//                }
//                emvList.add(createEmvLog("Certificate is [" + certificatPKI + "]"));
//
//            }




            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "processingRestriction profile successful", emvList);
        } catch (Exception ex) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in processingRestriction, please check with your provider !", null);
        }
    }



    //-------------------------------------------------------


    private Emv createEmvLog(String description) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return new Emv(sdf.format(new Date()), description);
    }


    // Fonction récursive pour parcourir la structure JSON et trouver la valeur de l'élément correspondant à "Name"
    private String findTagValue(JsonNode node, String tagName) {
        if (node.isArray()) {
            for (JsonNode arrayElement : node) {
                String value = findTagValue(arrayElement, tagName);
                if (value != null) {
                    return value;
                }
            }
        } else if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                if (field.getKey().equals("Name") && field.getValue().asText().equals(tagName)) {
                    JsonNode valueNode = node.get("Value");
                    return valueNode != null ? valueNode.asText() : null;
                } else {
                    String value = findTagValue(field.getValue(), tagName);
                    if (value != null) {
                        return value;
                    }
                }
            }
        }
        return null;
    }

    //-------------------------------


    private void analyzeAIPBinary(String binaryValue, List<Emv> emvList,String tag8FValue,String tag90Value,String tag92Value, String tag9F32Value,String tag93Value) {
        try {

            if (binaryValue.charAt(2) == '1') {
                emvList.add(createEmvLog("DDA Processing ..."));
            };
            if (binaryValue.charAt(1) == '1') {
                emvList.add(createEmvLog("Static Data Authetication (SDA) Processing in Progress ..."));


            };





        } catch (Exception ex) {
            // Gérer les erreurs éventuelles
        }
    }


    // Fonction pour convertir une valeur hexadécimale en binaire
    private String Hexa_To_Bin(String pHexa) {
        String text = pHexa.toUpperCase();
        if (Is_Hexa(pHexa)) {
            text = text.replace("0", "0000");
            text = text.replace("1", "0001");
            text = text.replace("2", "0010");
            text = text.replace("3", "0011");
            text = text.replace("4", "0100");
            text = text.replace("5", "0101");
            text = text.replace("6", "0110");
            text = text.replace("7", "0111");
            text = text.replace("8", "1000");
            text = text.replace("9", "1001");
            text = text.replace("A", "1010");
            text = text.replace("B", "1011");
            text = text.replace("C", "1100");
            text = text.replace("D", "1101");
            text = text.replace("E", "1110");
            return text.replace("F", "1111");
        }

        return null;
    }

    // Fonction pour vérifier si une chaîne est au format hexadécimal
    private boolean Is_Hexa(String input) {
        // Utiliser une expression régulière pour vérifier si la chaîne est au format hexadécimal
        String hexPattern = "^[0-9A-Fa-f]+$";
        return Pattern.matches(hexPattern, input);
    }


}
