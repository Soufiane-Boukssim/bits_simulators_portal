package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.entities.pos.PosExchangeRateParam;
import com.simulator.entities.pos.PosExchangeRateParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.ExchangeRateService;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.repository.pos.PosExchangeRateRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PosExchangeRateService {

    private final Logger logger = LogManager.getLogger(ExchangeRateService.class);
    @Autowired
    PosExchangeRateRepository exchangeRateRepository;


    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;




    public ResponseApiJson<?> getAllExchangeRateParams(String protocol , String bankCode) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            logger.info("protocol " + protocol + "");
            logger.info("bankCode " + bankCode + "");
            List<PosExchangeRateParam> exchangeRateParams = exchangeRateRepository.findByBankCodeAndRateProtocol(bankCode.trim(), protocol.trim());

            logger.info("exchangeRateParams [{}]", exchangeRateParams);
            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "ExchangeRateParams", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve ExchangeRateParams", "ExchangeRateParams retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all exchangeRate succesfully  !", exchangeRateParams);


        } catch (Exception e) {
            logger.info("Exception of this::getAllAccountDefs " + e.getMessage());

            // Track user activity for exception
            //  UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "ExchangeRateParams", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve ExchangeRateParams", "An error occurred while retrieving ExchangeRateParams", new Date());
            // userActivityTrackingRepository.save(userTrace);


            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllAccountDefs please check with your provider !");

        }


    }

    public ResponseApiJson<List<PosExchangeRateParam>> getOneExchangeRateParam(PosExchangeRateParamId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<PosExchangeRateParam> exchangeRateParam = exchangeRateRepository.findById(id);
            List<PosExchangeRateParam> exchangeRateParams = new ArrayList<>();
            exchangeRateParam.ifPresent(exchangeRateParams::add);

            // Track user activity
            // UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "ExchangeRateParams", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve PosExchangeRateParam", "PosExchangeRateParam retrieved successfully", new Date());
            //userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one ExchangeRate succesfully  !" , exchangeRateParams);
        } catch (Exception e)   {
            logger.info("Exception of  getOneExchangeRateParam" + e.getMessage());
            // Track user activity for exception
            //UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "PosExchangeRateParam", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve PosExchangeRateParam", "An error occurred while retrieving PosExchangeRateParam", new Date());
            // userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneExchangeRateParam please check with your provider !");

        }
    }

    public ResponseApiJson<?> addExchangeRateParam(PosExchangeRateParam exchangeRateParam){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(exchangeRateParam.toString());
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<PosExchangeRateParam> exchangeRateParamtoCheck = exchangeRateRepository.findById(exchangeRateParam.getId());
            if ( exchangeRateParamtoCheck.isEmpty()) {
                exchangeRateRepository.save(exchangeRateParam);

                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "ExchangeRateParams", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert PosExchangeRateParam", "PosExchangeRateParam inserted successfully", new Date());
                userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde exchangeRateParam sucessfully  !");
            }else {
                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "ExchangeRateParams", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Insert PosExchangeRateParam", "PosExchangeRateParam inserted Failed", new Date());
                userActivityTrackingRepository.save(userTrace);

                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this PosExchangeRateParam already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneExchangeRateParam "  + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "PosExchangeRateParam", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert PosExchangeRateParam", "Some issues in addExchangeRateParam please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addExchangeRateParam please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateExchangeRateParam(PosExchangeRateParam exchangeRateParam) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try{
            Optional<PosExchangeRateParam> exchangeRateParamto = exchangeRateRepository.findById(exchangeRateParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (exchangeRateParamto.isPresent()){
                PosExchangeRateParam exchangeRateParam1 = (PosExchangeRateParam)  globalVars.construct(PosExchangeRateParam.class, exchangeRateParam , exchangeRateParamto.get());
                logger.info("ExchangeRateParamto " + exchangeRateParam1.toString());
                exchangeRateRepository.save(exchangeRateParam1);

                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "PosExchangeRateParam", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Update PosExchangeRateParam", "PosExchangeRateParam updated  successfully", new Date());
                userActivityTrackingRepository.save(userTrace);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  exchangeRateParam  sucessfuly  !");

            } else {

                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "PosExchangeRateParam", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Update PosExchangeRateParam", "PosExchangeRateParam updated Failed", new Date());
                userActivityTrackingRepository.save(userTrace);
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this exchangeRateParam  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneExchangeRateParam "  + e.getMessage());
            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Update", "PosExchangeRateParam", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Update PosExchangeRateParam", "Some issues in updateExchangeRateService please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateExchangeRateService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteExchangeRateParam(PosExchangeRateParamId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<PosExchangeRateParam> exchangeRateParam = exchangeRateRepository.findById(id);
            exchangeRateParam.ifPresent(exchangeRateRepository ::delete);

            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Delete", "PosExchangeRateParam", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Delete PosExchangeRateParam", "PosExchangeRateParam deleted  successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete exchangeRateParam succesfully !");
        }catch (Exception e){
            logger.info("Delete exchangeRateParam Exception: " +e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "PosExchangeRateParam", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete PosExchangeRateParam", "Some issues in deleteExchangeRateParam please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteExchangeRateParam terminated with issue");
        }
    }

}



