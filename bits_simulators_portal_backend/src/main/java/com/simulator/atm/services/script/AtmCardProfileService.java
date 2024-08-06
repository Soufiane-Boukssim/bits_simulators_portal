package com.simulator.atm.services.script;

import com.simulator.atm.model.AtmCardProfil;
import com.simulator.atm.model.AtmCardProfilId;
import com.simulator.atm.repositories.reposcript.AtmCardProfilRepository;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AtmCardProfileService {

    private final Logger logger = LogManager.getLogger(AtmCardProfileService.class);
    @Autowired
    AtmCardProfilRepository cardRepository;

    public ResponseApiJson<List<AtmCardProfil>> getAllCardProfiles(AtmCardProfilId cardProfileId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<AtmCardProfil> cardProfiles = cardRepository.findByBankCodeAndCardProtocol(cardProfileId.getBankCode());

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all cardProfilesuccesfully  !", cardProfiles);

        } catch (Exception e) {
            logger.info("Exception of this::getAllCardProfiles " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCardProfiles please check with your provider !");

        }

    }

    public ResponseApiJson<List<AtmCardProfil>> getOneCardProfile(AtmCardProfilId id) {
        String idRequest = "CPSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<AtmCardProfil> cardProfile = cardRepository.findById(id);
            List<AtmCardProfil> cardProfiles = new ArrayList<>();
            cardProfile.ifPresent(cardProfiles::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one cardProfile succesfully  !" , cardProfiles);
        } catch (Exception e)   {
            logger.info("Exception of  getOneCardProfile " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneCardProfile please check with your provider !");

        }
    }

    public ResponseApiJson<?> addCardProfile(AtmCardProfil cardProfile){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(cardProfile.toString());
        try {
            Optional<AtmCardProfil> cardProfiletoCheck = cardRepository.findById(cardProfile.getId());
            if ( cardProfiletoCheck.isEmpty()) {
                cardRepository.save(cardProfile);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde cardProfile sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this AtmCardProfile already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneCardProfile "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addCardProfile please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateCardProfile(AtmCardProfil cardProfile) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<AtmCardProfil> cardProfileto = cardRepository.findById(cardProfile.getId());
            GlobalVars globalVars = new GlobalVars();
            if (cardProfileto.isPresent()){
                AtmCardProfil cardProfile1 = (AtmCardProfil)  globalVars.construct(AtmCardProfil.class, cardProfile , cardProfileto.get());
                logger.info("cardProfileto " + cardProfile1.toString());
                cardRepository.save(cardProfile1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  cardProfile  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this AtmProfile  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneCardProfile "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateCardService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteCardProfile(AtmCardProfilId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<AtmCardProfil> cardProfile = cardRepository.findById(id);
            cardProfile.ifPresent(cardRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete cardProfile successfully !");
        }catch (Exception e){
            logger.info("Delete cardProfile Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCardProfile terminated with issue");
        }
    }
}
