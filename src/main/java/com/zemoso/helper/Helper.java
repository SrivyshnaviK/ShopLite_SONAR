package com.zemoso.helper;

import com.zemoso.entity.Ordered;
import com.zemoso.service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Helper {
    private OrderedService orderedService;
    @Autowired
    public Helper(OrderedService orderedService){
        this.orderedService=orderedService;}
    public void saveToOrdered(int pid,int uid){
        Ordered ordered=new Ordered(pid,uid);
        orderedService.save(ordered);
    }
}
