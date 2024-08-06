package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.FunctDef;
import com.simulator.entities.FunctDefId;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.FunctRepository;
import com.simulator.repository.UserActivityTrackingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class FunctService {
    private final Logger logger = LogManager.getLogger(FunctService.class);
    @Autowired
    FunctRepository functRepository;

    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;




    public ResponseApiJson<List<FunctDef>> getAllFunctDefs() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<FunctDef> functDefs = functRepository.findAll();

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all functDefs succesfully  !", functDefs);


        } catch (Exception e) {
            logger.info("Exception of this::getAllfunctDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllfunctDefs please check with your provider !");

        }
    }


    public ResponseApiJson<List<FunctDef>> getFunctDefsByProtocol(FunctDefId id) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<FunctDef> functDefs = functRepository.findByBankCodeAndFctProtocol(id.getBankCode(), id.getFctProtocol());

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all functDefs succesfully  !", functDefs);


        } catch (Exception e) {
            logger.info("Exception of this::getAllfunctDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllfunctDefs please check with your provider !");

        }
    }



    public ResponseApiJson<List<FunctDef>> getOneFunctDef(FunctDefId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<FunctDef> functDef = functRepository.findById(id);
            List<FunctDef> functDefs = new ArrayList<>();
            functDef.ifPresent(functDefs::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one functDef succesfully  !" , functDefs);
        } catch (Exception e)   {
            logger.info("Exception of  getOneFunctDef " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneFunctDef please check with your provider !");

        }
    }

    public ResponseApiJson<?> addFunctDef(FunctDef functDef){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info(functDef.toString());
        try {
            Optional<FunctDef> functDeftoCheck = functRepository.findById(functDef.getId());
            if ( functDeftoCheck.isEmpty()) {
                functRepository.save(functDef);
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "func_Def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Add Function","Add Function with id : "+functDef.getId().getFctCode()+" successfull",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde functDef sucessfully  !");
            }else {
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "func_Def","000.000.00.00", "Failed", GlobalVars.ALREADYEXIST,"Add Function","Function with id : "+functDef.getId().getFctCode()+" already exist !",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this FunctDef already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneFunctDef "  + e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "func_Def","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Add Function","Some issues in addFunctDef please check provider !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addFunctDef please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateFunctDef(FunctDef functDef) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try{
            Optional<FunctDef> functDefto = functRepository.findById(functDef.getId());
            GlobalVars globalVars = new GlobalVars();
            if (functDefto.isPresent()){
                FunctDef functDef1 = (FunctDef)  globalVars.construct(FunctDef.class, functDef , functDefto.get());
                logger.info("functDefto " + functDef1.toString());
                functRepository.save(functDef1);
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "func_Def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Update Function","Update Function with id : "+functDef.getId().getFctCode()+" successfull",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  functDef  sucessfuly  !");

            } else {
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "func_Def","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Update Function","Function with id : "+functDef.getId().getFctCode()+" does not exist",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this functDef  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneFunctDef "  + e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "func_Def","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Update Function","Some issues in updateFuncDEfService please check with your provider !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateFunctService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteFunctDef(FunctDefId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<FunctDef> functDef = functRepository.findById(id);
            functDef.ifPresent(functRepository ::delete);
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "func_Def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Delete Function","Delete Function with id : "+id+" successfull",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete functDef succesfully !");
        }catch (Exception e){
            logger.info("Delete functDef Exception: " +e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "func_Def","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Delete Function","Delete funcDef terminated with issue",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteFunctDef terminated with issue");
        }
    }
}




