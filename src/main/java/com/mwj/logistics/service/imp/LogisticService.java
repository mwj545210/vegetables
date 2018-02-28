package com.mwj.logistics.service.imp;

import com.mwj.logistics.dao.LogisticDao;
import com.mwj.logistics.entry.Logistic;
import com.mwj.logistics.service.ILogisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by MWJ on 2017/12/25.
 */
@Service
public class LogisticService implements ILogisticService{


    @Resource
    private LogisticDao logisticDao;

    @Override
    public Logistic addLogistics(Logistic logistic) {
        return logisticDao.save(logistic);
    }

    @Override
    public List<Logistic> findAllLogistics() {
        return logisticDao.findAll();
    }

    @Override
    public void delLogisticsByIds(List<Logistic> logistics) {
        logisticDao.delete(logistics);
    }

    @Override
    public Logistic findLogisticById(int id) {
        return logisticDao.findOne(id);
    }
}
