package com.thomas.tmgank;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.thomas.tmgank.dao.CollectionDao;
import com.thomas.tmgank.model.Collection;
import com.thomas.tmgank.util.TimeKit;

import java.util.Date;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {

        super(Application.class);
    }

    public void testAdd(){
        CollectionDao dao= new CollectionDao(mContext);
        dao.insert(new Collection("a.com", "haha", new Date()));
    }

    public void testdelete(){
        CollectionDao dao= new CollectionDao(mContext);
        dao.delete("a.com");
    }

    public void testData(){
        Date d=TimeKit.getLastdayDate(new Date(2015,0,2));
        System.out.println("时间：" + 00 + "是：" + TimeKit.toDate(d));
        for(int i=0;i<10;i++){
            d =TimeKit.getLastdayDate(d);
            System.out.println("时间："+i+"是："+TimeKit.toDate(d));
        }


    }

    public void testSelect(){
        CollectionDao dao=new CollectionDao(mContext);
        List<Collection> list =dao.select();
        System.out.println("集合大小："+list.size());
    }
}