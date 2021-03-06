package cn.bjfu.calculator.service;

import cn.bjfu.calculator.model.EnvironmentalSilver;
import cn.bjfu.calculator.util.CalculatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalculatorService {
    @Autowired
    private CalculatorUtils calculatorUtils;
    public List<Map<String, String>> getQingyangByDay(String date) throws Exception {
        //先挑选出清扬
        List<EnvironmentalSilver> environmentalSilvers = calculatorUtils.importExcel("E:\\jisuanqi\\text.xlsx");
        List<EnvironmentalSilver> qingyang = getShuzhong(environmentalSilvers, "青杨");
        //挑选出时间
        List<EnvironmentalSilver> shijianList = new ArrayList();
        for(EnvironmentalSilver q : qingyang){
            if(q.getCollectTime().contains(date)){
                shijianList.add(q);
            }
        }
        //返回个集合
        List<Map<String, String>> listMap = new ArrayList<>();
        for(EnvironmentalSilver list:shijianList){
             Map map = new HashMap();
             map.put(list.getCollectTime(),list.getE());
             listMap.add(map);
        }
        return listMap;
    }

    public List<EnvironmentalSilver> getShuzhong(List<EnvironmentalSilver> list,String shuzhong){
        List<EnvironmentalSilver> shuzhongList = new ArrayList<>();
        for(EnvironmentalSilver e:list){
            if(shuzhong.equals(e.getShuzhou())){
                shuzhongList.add(e);
            }
        }
        return shuzhongList;
    }
}
