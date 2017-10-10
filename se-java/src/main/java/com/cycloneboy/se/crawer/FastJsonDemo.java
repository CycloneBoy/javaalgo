package com.cycloneboy.se.crawer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cycloneboy.se.crawer.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CycloneBoy on 2017/10/10.
 */
public class FastJsonDemo {
    public static void main(String[] args) {
        Person person1 = new Person("小明",23);

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(new Person("张三",20));

        System.out.println(JSON.toJSON(person1));
        System.out.println(JSON.toJSON(list));

        Person person2 = JSON.parseObject("{\"name\":\"李明\",\"age\":19}",Person.class);
        System.out.printf("name: %s , age:%d " ,person2.getName(),person2.getAge());
        System.out.println();

        String str = "[{\"name\":\"小花\",\"age\":23},{\"name\":\"李四\",\"age\":20}]";
        List<Person> list1 = JSON.parseArray(str,Person.class);
        for(Person person : list1){
            System.out.println(person.getName() + ":" + person.getAge());
        }

        JSONObject jsonObject = JSON.parseObject("{\"name\":\"小明\",\"age\":23}");
        System.out.printf("name: %s, age: %d\n",jsonObject.getString("name"),
                jsonObject.getBigInteger("age"));

        System.out.println();

        JSONArray jsonArray = JSON.parseArray("[{\"name\":\"小明\",\"age\":23},{\"name\":\"张三\",\"age\":20}]");
        for(int i = 0;i<jsonArray.size();i++){
            JSONObject temp = jsonArray.getJSONObject(i);
            System.out.printf("name: %s, age: %d\n",temp.getString("name"),
                    temp.getBigInteger("age"));
        }

        System.out.println();
        for(Object object : jsonArray){
            System.out.println(object.toString());
        }
    }
}
