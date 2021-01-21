package com.jurepinjuh.demo.jobs;

import com.jurepinjuh.demo.Models.Purchase;
import com.jurepinjuh.demo.Repository.JpaPurchaseRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TotalPurchasedJob extends QuartzJobBean {

    private Logger log= LoggerFactory.getLogger(TotalPurchasedJob.class);

    private final JpaPurchaseRepository purchaseRepository;

    public TotalPurchasedJob(JpaPurchaseRepository purchaseRepository){
        this.purchaseRepository=purchaseRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Iterable<Purchase> purchases=purchaseRepository.findAll();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat printFormat=new SimpleDateFormat("yyyy-MM-dd");
        double totalToday=0;
        for (Purchase purchase:
             purchases) {
            if(fmt.format(purchase.getDateOfPurchase()).equals(fmt.format(new Date()))){
                totalToday=totalToday+purchase.getPrice();
            }
        }
      log.info("Total income from purchases:"+totalToday+" on date:"+printFormat.format(new Date()));
    }
}
