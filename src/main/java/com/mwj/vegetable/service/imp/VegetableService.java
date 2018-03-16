package com.mwj.vegetable.service.imp;

import com.mwj.vegetable.dao.VegetableDao;
import com.mwj.vegetable.entry.Vegetable;
import com.mwj.vegetable.service.IVegetableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by MWJ on 2017/12/25.
 */
@Service
public class VegetableService implements IVegetableService {


    @Resource
    private VegetableDao vegetableDao;

    @Override
    public Vegetable addVegetables(Vegetable vegetable) {
        return vegetableDao.save(vegetable);
    }

    @Override
    public List<Vegetable> findAllVegetables() {
        return vegetableDao.findAll();
    }

    @Override
    public void delVegetablesById(int vegetableId) {
        vegetableDao.delete(vegetableId);
    }

    @Override
    public Vegetable findVegetableById(int id) {
        return vegetableDao.findOne(id);
    }

    @Override
    public List<Vegetable> findAllByQuery(Vegetable vegetable) {
        List<Vegetable> vegetables;
        if (vegetable.getDealType() == null){
            vegetables = vegetableDao.findAllByNameAndCode("%"+vegetable.getVegetableName()+"%","%"+vegetable.getVegetableCode()+"%");
        }else {
            vegetables = vegetableDao.findAllByNameAndCodeAndType("%"+vegetable.getVegetableName()+"%","%"+vegetable.getVegetableCode()+"%",vegetable.getDealType());
        }
        return vegetables;
    }

}
