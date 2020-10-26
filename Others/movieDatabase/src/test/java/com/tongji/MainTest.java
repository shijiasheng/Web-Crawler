package com.tongji;

import com.tongji.MyApplication;
import com.tongji.mapper.ProductdataMapper;
import com.tongji.model.Productdata;
import com.tongji.model.ProductdataExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class)
public class MainTest
{
    @Autowired
    ProductdataMapper mapper;

    @Test
    public void test()
    {
        ProductdataExample productdataExample = new ProductdataExample();
        long count = mapper.countByExample(productdataExample);
        System.out.println(count);

//        ProductdataExample productdataExample = new ProductdataExample();
//        List<Productdata> productdata = mapper.selectByExample(productdataExample);
//        Productdata temp = new Productdata();
//        int count = 0;
//        for (Productdata productdatum : productdata)
//        {
//            ++count;
//            System.out.println(count);
//            temp.setId(productdatum.getId());
//            temp.setRunTime(changeTime(productdatum.getRunTime()));
//            mapper.updateByPrimaryKeySelective(temp);
//        }
    }

    /*
    2 hours and 1 minute
    1 hour and 1 minute
    1 hour and 10 minutes
    2 hours and 27 minutes
    30 minutes
    1 hour
    1 minute
    45sec
     */
    public static String changeTime(String runTime)
    {
        String result = "0";

        if (runTime == null)
        {
            return "0";
        }
        else if ("1 hour".equals(runTime))
        {
            result = "3600";
        }
        else if ("1 minute".equals(runTime))
        {
            result = "60";
        }
        else if (Pattern.matches("^.{1,2}sec$", runTime))
        {
            return runTime.replaceFirst("sec", "");
        }
        else
        {
            /*
                只剩下以下几种情况:
                2 hours and 1 minute
                1 hour and 1 minute
                1 hour and 10 minutes
                2 hours and 27 minutes
                30 minutes
             */
            int hour = 0;
            String pattern = "^.*hour.* and.*$";
            if (Pattern.matches(pattern, runTime))
            {
                //说明前面有小时
                int ch = runTime.charAt(1) - '0';

                if (ch >= 0 && ch <= 9)
                    hour = Integer.parseInt(runTime.substring(0, 2));
                else
                    hour=Integer.parseInt(runTime.substring(0, 1));
            }

            String[] strings = runTime.split(" ");
            System.out.println(Arrays.toString(strings));
            int minute=Integer.parseInt(strings[strings.length - 2]);
            int temp = 3600 * hour + 60 * minute;
            return temp+"";
        }
//        最后转换时间单位为秒
        return result;
    }

}
