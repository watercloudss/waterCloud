package com.watercloud.webmagic.controller;


import com.watercloud.webmagic.common.kafka.KafkaProducer;
import com.watercloud.webmagic.service.impl.RedissonLockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 系统日志表 前端控制器
 * </p>
 *
 * @author lly
 * @since 2021-09-21
 */
@RestController
@RequestMapping("/sys-log")
public class SysLogController {
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private RedissonLockService redissonLockService;
    @Resource
    private RedissonClient redissonClient;

    private final static String TEST_LOCK = "TEST_LOCK";
    @GetMapping("/kafka")
    public String kafka(){
        kafkaProducer.sendMessage("1","{\\\"POLST\\\":{\\\"POLICY\\\":{\\\"ZNEXTSYS\\\":\\\"2\\\",\\\"PRT1OPT\\\":\\\"Y\\\",\\\"PRT2OPT\\\":\\\"Y\\\",\\\"ZNGPCNO\\\":\\\"\\\",\\\"STATCODE\\\":\\\"PS\\\",\\\"CNTBRANCH\\\":\\\"SH\\\",\\\"TTMPRCNO\\\":\\\"013001204832\\\",\\\"Chdrnum\\\":\\\"\\\",\\\"OCCDATE\\\":\\\"20210908\\\",\\\"HPROPDTE\\\":\\\"20210908\\\",\\\"HPRRCVDT\\\":\\\"20211216\\\",\\\"PolicyOwner\\\":{\\\"OWNERSEL\\\":null,\\\"OWNERCRS\\\":{\\\"Custmer_Id\\\":\\\"\\\",\\\"Prtno\\\":\\\"\\\",\\\"Sur_Name\\\":\\\"\\\",\\\"Tax_Residency\\\":\\\"\\\",\\\"First_Name\\\":\\\"\\\",\\\"Middle_Name\\\":\\\"\\\",\\\"Date_Of_Birth\\\":\\\"\\\",\\\"Country\\\":\\\"\\\",\\\"Province_CH\\\":\\\"\\\",\\\"City_CH\\\":\\\"\\\",\\\"Area_CH\\\":\\\"\\\",\\\"Country_CH\\\":\\\"\\\",\\\"Address_CH\\\":\\\"\\\",\\\"Province_EN\\\":\\\"\\\",\\\"City_EN\\\":\\\"\\\",\\\"Address_EN\\\":\\\"\\\",\\\"Country_of_Birth\\\":\\\"\\\",\\\"Province_of_Birth_CH\\\":\\\"\\\",\\\"City_of_Birth_CH\\\":\\\"\\\",\\\"Area_of_Birth_CH\\\":\\\"\\\",\\\"Country_of_Birth_CH\\\":\\\"\\\",\\\"Address_of_Birth_CH\\\":\\\"\\\",\\\"Province_of_Birth_EN\\\":\\\"\\\",\\\"City_of_Birth_EN\\\":\\\"\\\",\\\"Address_of_Birth_EN\\\":\\\"\\\",\\\"Country_of_Tax1\\\":\\\"\\\",\\\"NINumber1\\\":\\\"\\\",\\\"Country_of_Tax2\\\":\\\"\\\",\\\"NINumber2\\\":\\\"\\\",\\\"Country_of_Tax3\\\":\\\"\\\",\\\"NINumber3\\\":\\\"\\\",\\\"Reason_Code\\\":\\\"\\\",\\\"Reason\\\":\\\"\\\",\\\"TransDate\\\":\\\"\\\"},\\\"SALUTL\\\":\\\"MR / MRS\\\",\\\"LSURNAME\\\":\\\"无实点点\\\",\\\"DOB\\\":\\\"19810819\\\",\\\"CLTSEX\\\":\\\"M\\\",\\\"MARRYD\\\":\\\"S\\\",\\\"ZACLTADDR\\\":\\\"上海市长宁区地方的大街1000201锦江路33号33室\\\",\\\"CLTPCODE\\\":\\\"200000\\\",\\\"ADDRTYPE\\\":\\\"1\\\",\\\"ZNBUSUT\\\":\\\"1\\\",\\\"CTRYCODE\\\":\\\"01\\\",\\\"NATLTY\\\":\\\"01\\\",\\\"CLRSKIND\\\":\\\"1\\\",\\\"RSKFLG\\\":\\\"1\\\",\\\"IDNPRF\\\":\\\"04\\\",\\\"SECUITYNO\\\":\\\"HGUE827608\\\",\\\"EXPDTE\\\":\\\"20250615\\\",\\\"INCPRF\\\":\\\"\\\",\\\"INCDESC\\\":\\\"\\\",\\\"DECGRSAL\\\":\\\"200000.00000000\\\",\\\"RMBLPHONE\\\":\\\"13916263346\\\",\\\"CLTPHONE\\\":\\\"021-83795970\\\",\\\"RINTERNET\\\":\\\"22345679@qq.com\\\",\\\"OCCPCODE\\\":\\\"T001\\\",\\\"ZNPIDCPYID\\\":\\\"N\\\",\\\"PRSKINDOWN\\\":\\\"\\\",\\\"ANNPRMTEND\\\":\\\"\\\",\\\"FMLINCOME\\\":\\\"\\\",\\\"RSDTKIND\\\":\\\"\\\",\\\"DIGNITARY\\\":\\\"\\\",\\\"NotifyAPP\\\":{\\\"appIsSmoke\\\":\\\"\\\",\\\"appSmokeFreq\\\":\\\"\\\",\\\"appSmokeYears\\\":\\\"\\\",\\\"appIsDrink\\\":\\\"\\\",\\\"appDrinkType\\\":\\\"\\\",\\\"appDrinkAmount\\\":\\\"\\\",\\\"appDrinkFreq\\\":\\\"\\\"}},\\\"BILLFREQ\\\":\\\"01\\\",\\\"ZNFPMOP\\\":\\\"D\\\",\\\"MOP\\\":\\\"E\\\",\\\"ZDIVOPT\\\":null,\\\"ZNFOPT\\\":\\\"03\\\",\\\"CNTCURR\\\":\\\"CNY\\\",\\\"BILLCURR\\\":\\\"CNY\\\",\\\"REGISTER\\\":\\\"01\\\",\\\"SRCEBUS\\\":\\\"2\\\",\\\"DLVRMODE\\\":\\\"04\\\",\\\"ZNPSTADD\\\":\\\"上海市长宁区地方的大街新苑小区23楼909\\\",\\\"CLTPCODE03\\\":\\\"111111\\\",\\\"ZNAGNTSEL\\\":\\\"8608812116\\\",\\\"AGCOMRT\\\":\\\"100\\\",\\\"SubAgnetList\\\":[{\\\"ZNAGNTSEL01\\\":\\\"\\\",\\\"SPLITC\\\":\\\"\\\"}],\\\"ZNCSMNAME\\\":\\\"\\\",\\\"INSLST\\\":{\\\"Insured\\\":{\\\"Life\\\":\\\"01\\\",\\\"InsuredInfo\\\":{\\\"LIFCNUM\\\":null,\\\"SALUTL02\\\":\\\"MR / MRS\\\",\\\"LSURNAME02\\\":\\\"无实点点\\\",\\\"DOB02\\\":\\\"19810819\\\",\\\"CLTSEX02\\\":\\\"M\\\",\\\"MARRYD02\\\":\\\"S\\\",\\\"ZACLTADDR02\\\":\\\"上海市长宁区地方的大街1000201锦江路33号33室\\\",\\\"CLTPCODE02\\\":\\\"200000\\\",\\\"ADDRTYPE02\\\":\\\"1\\\",\\\"ZNBUSUT02\\\":\\\"1\\\",\\\"CTRYCODE02\\\":\\\"01\\\",\\\"NATLTY02\\\":\\\"01\\\",\\\"CLRSKIND02\\\":\\\"1\\\",\\\"RSKFLG02\\\":\\\"1\\\",\\\"IDNPRF02\\\":\\\"04\\\",\\\"SECUITYNO02\\\":\\\"HGUE827608\\\",\\\"EXPDTE02\\\":\\\"20250615\\\",\\\"INCPRF02\\\":\\\"\\\",\\\"INCDESC02\\\":\\\"\\\",\\\"DECGRSAL02\\\":\\\"0\\\",\\\"RMBLPHONE02\\\":\\\"13916263346\\\",\\\"CLTPHONE02\\\":\\\"021-83795970\\\",\\\"RINTERNET02\\\":\\\"22345679@qq.com\\\",\\\"OCCPCODE02\\\":\\\"T001\\\",\\\"ZNPIDCPYID02\\\":\\\"N\\\",\\\"PRSKINDINS\\\":\\\"\\\",\\\"DIGNITARY02\\\":\\\"\\\",\\\"NotifyINS\\\":{\\\"insSmokeFreq\\\":\\\"\\\",\\\"insSmokeYears\\\":\\\"\\\",\\\"insIsDrink\\\":\\\"\\\",\\\"insDrinkType\\\":\\\"\\\",\\\"insDrinkAmount\\\":\\\"\\\",\\\"insDrinkFreq\\\":\\\"\\\"}},\\\"RELATION\\\":\\\"00\\\",\\\"SELECTION\\\":\\\"NIL\\\",\\\"HEIGHT\\\":\\\"\\\",\\\"WEIGHT\\\":\\\"\\\",\\\"SMOKING\\\":\\\"\\\",\\\"OCCUP\\\":\\\"T001\\\",\\\"Questionare\\\":{\\\"QUESTSET\\\":\\\"\\\",\\\"QUESTIONLIST\\\":{\\\"QUESTION\\\":[{\\\"QUESTIDF\\\":\\\"T02\\\",\\\"ANSWER\\\":\\\"N\\\"}]}},\\\"HUWDCDTE\\\":\\\"99999999\\\",\\\"COVLST\\\":{\\\"COVINFO\\\":[{\\\"Coverage\\\":\\\"01\\\",\\\"RIDER\\\":\\\"00\\\",\\\"ZNPRDCODE\\\":\\\"DD16TA\\\",\\\"SUMIN\\\":\\\"100000\\\",\\\"RCESSAGE\\\":\\\"106\\\",\\\"RCESSTRM\\\":\\\"0\\\",\\\"PCESSAGE\\\":\\\"0\\\",\\\"PCESSTRM\\\":\\\"5\\\",\\\"BCESSAGE\\\":\\\"106\\\",\\\"BCESSTRM\\\":\\\"0\\\",\\\"MORTCLS\\\":\\\"1\\\",\\\"INSTPRM\\\":\\\"12456.00000000\\\",\\\"LMPCNT\\\":\\\"\\\",\\\"ZPRFLG\\\":\\\"\\\",\\\"ZNINVAMT\\\":\\\"\\\",\\\"ZNTRM\\\":\\\"\\\",\\\"ZUNIT\\\":\\\"1\\\",\\\"LIVESNO\\\":\\\"1\\\",\\\"PAYCLT\\\":\\\"\\\",\\\"PayBankInfor\\\":{\\\"BANKCODE\\\":\\\"\\\",\\\"BANKKEY\\\":\\\"\\\",\\\"BANKACCKEY\\\":\\\"\\\",\\\"BANKDESC\\\":\\\"\\\",\\\"BNKACTYP\\\":\\\"\\\",\\\"CURRCODE\\\":\\\"\\\",\\\"DATEFROM\\\":\\\"0\\\",\\\"TIMESUSE\\\":\\\"\\\"},\\\"FREQANN\\\":null,\\\"PMTANN\\\":null,\\\"FREQCPN\\\":\\\"\\\",\\\"PMTCPN\\\":\\\"\\\",\\\"COMMSFLAG\\\":\\\"\\\",\\\"CLMPMTFRQ\\\":\\\"\\\",\\\"ADVANCE\\\":\\\"\\\",\\\"ARREARS\\\":\\\"\\\",\\\"GUARPERD\\\":\\\"0\\\",\\\"WITHPROP\\\":\\\"\\\",\\\"WITHOPROP\\\":\\\"\\\",\\\"NOMLIFE\\\":\\\"\\\",\\\"DTHPERCN\\\":\\\"0\\\",\\\"DTHPERCO\\\":\\\"0\\\",\\\"Payment\\\":{\\\"PAYCLT02\\\":\\\"\\\",\\\"CLTYPE\\\":\\\"\\\",\\\"RGPYMOP\\\":\\\"\\\",\\\"REGPAYFREQ\\\":\\\"\\\",\\\"PRCNT\\\":\\\"100\\\",\\\"CLAIMCUR\\\":\\\"CNY\\\"},\\\"RSUNIN\\\":\\\"\\\",\\\"PRCAMTIND\\\":\\\"\\\",\\\"PRMFLST\\\":{\\\"VRTFND01\\\":\\\"0\\\",\\\"UALPRC01\\\":\\\"0\\\",\\\"VRTFND02\\\":\\\"0\\\",\\\"UALPRC02\\\":\\\"0\\\",\\\"VRTFND03\\\":\\\"0\\\",\\\"UALPRC03\\\":\\\"0\\\",\\\"VRTFND04\\\":\\\"0\\\",\\\"UALPRC04\\\":\\\"0\\\",\\\"VRTFND05\\\":\\\"0\\\",\\\"UALPRC05\\\":\\\"0\\\",\\\"VRTFND06\\\":\\\"0\\\",\\\"UALPRC06\\\":\\\"0\\\",\\\"VRTFND07\\\":\\\"0\\\",\\\"UALPRC07\\\":\\\"0\\\",\\\"VRTFND08\\\":\\\"0\\\",\\\"UALPRC08\\\":\\\"0\\\",\\\"VRTFND09\\\":\\\"0\\\",\\\"UALPRC09\\\":\\\"0\\\",\\\"VRTFND10\\\":\\\"0\\\",\\\"UALPRC10\\\":\\\"0\\\"},\\\"RGUFLST\\\":{\\\"ZAVRTFND01\\\":\\\"0\\\",\\\"ZNSPLAMT01\\\":\\\"0\\\",\\\"ZAVRTFND02\\\":\\\"0\\\",\\\"ZNSPLAMT02\\\":\\\"0\\\",\\\"ZAVRTFND03\\\":\\\"0\\\",\\\"ZNSPLAMT03\\\":\\\"0\\\",\\\"ZAVRTFND04\\\":\\\"0\\\",\\\"ZNSPLAMT04\\\":\\\"0\\\",\\\"ZAVRTFND05\\\":\\\"0\\\",\\\"ZNSPLAMT05\\\":\\\"0\\\",\\\"ZAVRTFND06\\\":\\\"0\\\",\\\"ZNSPLAMT06\\\":\\\"0\\\",\\\"ZAVRTFND07\\\":\\\"0\\\",\\\"ZNSPLAMT07\\\":\\\"0\\\",\\\"ZAVRTFND08\\\":\\\"0\\\",\\\"ZNSPLAMT08\\\":\\\"0\\\",\\\"ZAVRTFND09\\\":\\\"0\\\",\\\"ZNSPLAMT09\\\":\\\"0\\\",\\\"ZAVRTFND10\\\":\\\"0\\\",\\\"ZNSPLAMT10\\\":\\\"0\\\"},\\\"CovField1\\\":\\\"\\\",\\\"CovField2\\\":\\\"\\\",\\\"CovField3\\\":\\\"\\\",\\\"CovField4\\\":\\\"\\\",\\\"CovField5\\\":\\\"\\\"},{\\\"Coverage\\\":\\\"01\\\",\\\"RIDER\\\":\\\"01\\\",\\\"ZNPRDCODE\\\":\\\"MD01TA\\\",\\\"SUMIN\\\":\\\"100000\\\",\\\"RCESSAGE\\\":\\\"106\\\",\\\"RCESSTRM\\\":\\\"0\\\",\\\"PCESSAGE\\\":\\\"0\\\",\\\"PCESSTRM\\\":\\\"5\\\",\\\"BCESSAGE\\\":\\\"106\\\",\\\"BCESSTRM\\\":\\\"0\\\",\\\"MORTCLS\\\":\\\"1\\\",\\\"INSTPRM\\\":\\\"1088.00000000\\\",\\\"LMPCNT\\\":\\\"\\\",\\\"ZPRFLG\\\":\\\"\\\",\\\"ZNINVAMT\\\":\\\"\\\",\\\"ZNTRM\\\":\\\"\\\",\\\"ZUNIT\\\":\\\"1\\\",\\\"LIVESNO\\\":\\\"1\\\",\\\"PAYCLT\\\":\\\"\\\",\\\"PayBankInfor\\\":{\\\"BANKCODE\\\":\\\"\\\",\\\"BANKKEY\\\":\\\"\\\",\\\"BANKACCKEY\\\":\\\"\\\",\\\"BANKDESC\\\":\\\"\\\",\\\"BNKACTYP\\\":\\\"\\\",\\\"CURRCODE\\\":\\\"\\\",\\\"DATEFROM\\\":\\\"0\\\",\\\"TIMESUSE\\\":\\\"\\\"},\\\"FREQANN\\\":null,\\\"PMTANN\\\":null,\\\"FREQCPN\\\":\\\"\\\",\\\"PMTCPN\\\":\\\"\\\",\\\"COMMSFLAG\\\":\\\"\\\",\\\"CLMPMTFRQ\\\":\\\"\\\",\\\"ADVANCE\\\":\\\"\\\",\\\"ARREARS\\\":\\\"\\\",\\\"GUARPERD\\\":\\\"0\\\",\\\"WITHPROP\\\":\\\"\\\",\\\"WITHOPROP\\\":\\\"\\\",\\\"NOMLIFE\\\":\\\"\\\",\\\"DTHPERCN\\\":\\\"0\\\",\\\"DTHPERCO\\\":\\\"0\\\",\\\"Payment\\\":{\\\"PAYCLT02\\\":\\\"\\\",\\\"CLTYPE\\\":\\\"\\\",\\\"RGPYMOP\\\":\\\"\\\",\\\"REGPAYFREQ\\\":\\\"\\\",\\\"PRCNT\\\":\\\"100\\\",\\\"CLAIMCUR\\\":\\\"CNY\\\"},\\\"RSUNIN\\\":\\\"\\\",\\\"PRCAMTIND\\\":\\\"\\\",\\\"PRMFLST\\\":{\\\"VRTFND01\\\":\\\"0\\\",\\\"UALPRC01\\\":\\\"0\\\",\\\"VRTFND02\\\":\\\"0\\\",\\\"UALPRC02\\\":\\\"0\\\",\\\"VRTFND03\\\":\\\"0\\\",\\\"UALPRC03\\\":\\\"0\\\",\\\"VRTFND04\\\":\\\"0\\\",\\\"UALPRC04\\\":\\\"0\\\",\\\"VRTFND05\\\":\\\"0\\\",\\\"UALPRC05\\\":\\\"0\\\",\\\"VRTFND06\\\":\\\"0\\\",\\\"UALPRC06\\\":\\\"0\\\",\\\"VRTFND07\\\":\\\"0\\\",\\\"UALPRC07\\\":\\\"0\\\",\\\"VRTFND08\\\":\\\"0\\\",\\\"UALPRC08\\\":\\\"0\\\",\\\"VRTFND09\\\":\\\"0\\\",\\\"UALPRC09\\\":\\\"0\\\",\\\"VRTFND10\\\":\\\"0\\\",\\\"UALPRC10\\\":\\\"0\\\"},\\\"RGUFLST\\\":{\\\"ZAVRTFND01\\\":\\\"0\\\",\\\"ZNSPLAMT01\\\":\\\"0\\\",\\\"ZAVRTFND02\\\":\\\"0\\\",\\\"ZNSPLAMT02\\\":\\\"0\\\",\\\"ZAVRTFND03\\\":\\\"0\\\",\\\"ZNSPLAMT03\\\":\\\"0\\\",\\\"ZAVRTFND04\\\":\\\"0\\\",\\\"ZNSPLAMT04\\\":\\\"0\\\",\\\"ZAVRTFND05\\\":\\\"0\\\",\\\"ZNSPLAMT05\\\":\\\"0\\\",\\\"ZAVRTFND06\\\":\\\"0\\\",\\\"ZNSPLAMT06\\\":\\\"0\\\",\\\"ZAVRTFND07\\\":\\\"0\\\",\\\"ZNSPLAMT07\\\":\\\"0\\\",\\\"ZAVRTFND08\\\":\\\"0\\\",\\\"ZNSPLAMT08\\\":\\\"0\\\",\\\"ZAVRTFND09\\\":\\\"0\\\",\\\"ZNSPLAMT09\\\":\\\"0\\\",\\\"ZAVRTFND10\\\":\\\"0\\\",\\\"ZNSPLAMT10\\\":\\\"0\\\"},\\\"CovField1\\\":\\\"\\\",\\\"CovField2\\\":\\\"\\\",\\\"CovField3\\\":\\\"\\\",\\\"CovField4\\\":\\\"\\\",\\\"CovField5\\\":\\\"\\\"}]}}},\\\"OwnQuestionare\\\":{\\\"OwnQUESTSET\\\":\\\"\\\",\\\"OwnQuestionList\\\":{\\\"OwnQuestion\\\":[{\\\"QUESTIDF\\\":\\\"T01\\\",\\\"ANSWER\\\":\\\"N\\\"}]}},\\\"RELATION\\\":\\\"00\\\",\\\"OwnHeight\\\":\\\"\\\",\\\"OwnWeight\\\":\\\"\\\",\\\"BeneficiaryList\\\":{\\\"Beneficiary\\\":[]},\\\"FRTRecBank\\\":{\\\"BANKKEY02\\\":\\\"ABC_YL\\\",\\\"BANKACCKEY02\\\":\\\"621234567875432\\\",\\\"BANKDESC02\\\":\\\"无实点点\\\",\\\"BNKACTYP02\\\":\\\"CNY\\\",\\\"CURRCODE02\\\":null,\\\"DATEFROM01\\\":\\\"20210908\\\",\\\"DATETO\\\":\\\"0\\\"},\\\"SEQRecBank\\\":{\\\"BANKKEY03\\\":\\\"ABC_YL\\\",\\\"BANKACCKEY03\\\":\\\"621234567875432\\\",\\\"BANKDESC03\\\":\\\"无实点点\\\",\\\"BNKACTYP03\\\":\\\"AC01\\\",\\\"CURRCODE03\\\":\\\"CNY\\\",\\\"DATEFROM02\\\":\\\"20210908\\\",\\\"DATETO02\\\":\\\"0\\\"},\\\"TRANDATEX\\\":\\\"0\\\",\\\"BANKCODE01\\\":\\\"\\\",\\\"TCHQDATE\\\":\\\"0\\\",\\\"BANKDESC01R\\\":\\\"\\\",\\\"BANKDESC03R\\\":\\\"\\\",\\\"RFCODE\\\":\\\"\\\",\\\"RFNUM\\\":\\\"\\\",\\\"PAYTYPE\\\":\\\"\\\",\\\"DOCORIGAMT\\\":null,\\\"ZNOVRPAYOP\\\":\\\"N\\\",\\\"PackageInfo\\\":{\\\"PackageCode\\\":\\\"PK001628\\\",\\\"PackagePrem\\\":\\\"\\\",\\\"PackageSI\\\":\\\"\\\"},\\\"GeneralRemark\\\":{\\\"ALINE01\\\":\\\"\\\",\\\"ALINE02\\\":null},\\\"SACSCODE\\\":\\\"\\\",\\\"SACSTYPE\\\":\\\"\\\",\\\"CAMPAIGN\\\":\\\"CT210706\\\",\\\"ZNPRJTCD\\\":\\\"PT200120\\\",\\\"ZNEPOLFLG\\\":\\\"01\\\",\\\"ZNBNKODR\\\":\\\"621234567875432\\\",\\\"ZNDISPER\\\":\\\"1\\\",\\\"ZNSPCTYP\\\":\\\"\\\",\\\"ZNBILLNO\\\":\\\"\\\",\\\"ZNINVBNK\\\":\\\"\\\",\\\"VIP\\\":\\\"\\\",\\\"Buyerid\\\":\\\"\\\",\\\"ZAPOLFLD1\\\":\\\"\\\",\\\"ZAPOLFLD2\\\":\\\"\\\",\\\"ZAPOLFLD3\\\":\\\"\\\",\\\"ZAPOLFLD4\\\":\\\"\\\",\\\"ZAPOLFLD5\\\":\\\"1\\\",\\\"ZAPOLFLD6\\\":\\\"  50027228\\\",\\\"ZAPOLFLD7\\\":\\\"\\\",\\\"ZAPOLFLD8\\\":\\\"\\\",\\\"ZAPOLFLD9\\\":\\\"\\\",\\\"ZAPOLFLD10\\\":\\\"\\\",\\\"ZAPOLFLD11\\\":\\\"\\\",\\\"ZAPOLFLD12\\\":\\\"\\\",\\\"ZAPOLFLD13\\\":\\\"\\\",\\\"ZAPOLFLD14\\\":\\\"\\\",\\\"ZAPOLFLD15\\\":\\\"\\\",\\\"HIGHAMNTFlAG\\\":\\\"\\\",\\\"PREINSUREFLAG\\\":\\\"\\\",\\\"AMLFLAG\\\":\\\"\\\",\\\"NeedDoubleReport\\\":\\\"\\\",\\\"DoubleReportDESC\\\":\\\"\\\",\\\"netspotName\\\":\\\"\\\",\\\"custManager\\\":\\\"\\\",\\\"clerkName\\\":\\\"\\\",\\\"salesCode1\\\":\\\"\\\",\\\"salesName1\\\":\\\"\\\",\\\"salesCode2\\\":\\\"\\\",\\\"salesName2\\\":\\\"\\\",\\\"salesCode3\\\":\\\"\\\",\\\"salesName3\\\":\\\"\\\",\\\"Fatca\\\":{\\\"FatcaOwner\\\":{\\\"FatcaAppIsFatca\\\":\\\"\\\",\\\"FatcaAppNC\\\":\\\"\\\",\\\"FatcaAppSpecial\\\":\\\"\\\",\\\"FatcaAppBC\\\":\\\"\\\",\\\"FatcaAppSocialCode\\\":\\\"\\\",\\\"FatcaAppLC\\\":\\\"\\\",\\\"FatcaAppOaDetail\\\":\\\"\\\",\\\"FatcaAppOaCountry\\\":\\\"\\\",\\\"FatcaAppOaProvince\\\":\\\"\\\",\\\"FatcaAppOaCity\\\":\\\"\\\",\\\"FatcaAppOaZipCode\\\":\\\"\\\",\\\"FatcaAppPt1\\\":\\\"\\\",\\\"FatcaAppPv1\\\":\\\"\\\",\\\"FatcaAppPt2\\\":\\\"\\\",\\\"FatcaAppPv2\\\":\\\"\\\",\\\"FatcaAppPt3\\\":\\\"\\\",\\\"FatcaAppPv3\\\":\\\"\\\",\\\"FatcaAppPt4\\\":\\\"\\\",\\\"FatcaAppPv4\\\":\\\"\\\",\\\"FatcaAppPt5\\\":\\\"\\\",\\\"FatcaAppPv5\\\":\\\"\\\",\\\"FatcaAppFg\\\":\\\"\\\",\\\"FatcaAppFv\\\":\\\"\\\"},\\\"FatcaIns\\\":{\\\"FatcaInsIsFatca\\\":\\\"\\\",\\\"FatcaInsNC\\\":\\\"\\\",\\\"FatcaInsSpecial\\\":\\\"\\\",\\\"FatcaInsBC\\\":\\\"\\\",\\\"FatcaInsSocialCode\\\":\\\"\\\",\\\"FatcaInsLC\\\":\\\"\\\",\\\"FatcaInsOaDetail\\\":\\\"\\\",\\\"FatcaInsOaCountry\\\":\\\"\\\",\\\"FatcaInsOaProvince\\\":\\\"\\\",\\\"FatcaInsOaCity\\\":\\\"\\\",\\\"FatcaInsOaZipCode\\\":\\\"\\\",\\\"FatcaInsPt1\\\":\\\"\\\",\\\"FatcaInsPv1\\\":\\\"\\\",\\\"FatcaInsPt2\\\":\\\"\\\",\\\"FatcaInsPv2\\\":\\\"\\\",\\\"FatcaInsPt3\\\":\\\"\\\",\\\"FatcaInsPv3\\\":\\\"\\\",\\\"FatcaInsPt4\\\":\\\"\\\",\\\"FatcaInsPv4\\\":\\\"\\\",\\\"FatcaInsPt5\\\":\\\"\\\",\\\"FatcaInsPv5\\\":\\\"\\\",\\\"FatcaInsFg\\\":\\\"\\\",\\\"FatcaInsFv\\\":\\\"\\\"}},\\\"AsynchPay\\\":\\\"N\\\",\\\"MATURITYFLAG\\\":\\\"Y\\\"}}}", LocalDateTime.now());
    return "1";
    }
    @GetMapping("/lock")
    public String lock(){
//        DistributedLock lock = redissonLockService.getDistributedLock(TEST_LOCK);
        RLock lock = redissonClient.getLock(TEST_LOCK);
        try{
            boolean isLockSuccess = lock.tryLock(3,150, TimeUnit.SECONDS);
            if(!isLockSuccess){
                return "3";
            }
            Thread.sleep(10000);
            System.out.println("----------------------加锁成功了---------------------");
        }catch(InterruptedException e){
            System.out.println("------------------------报错了-------------------------");
          return "2";
        }finally{
            lock.forceUnlock();
        }
        return "1";
    }
}
