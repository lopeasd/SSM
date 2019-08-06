package com.review.bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 用来测试注入的属性为集合类型，list map properties等
 * @author shixiaofei
 */
public class ComplexObject {
    private Properties pros;
    private List someList;
    private Map map;
    private Set set;

    public void setPros(Properties pros) {
        this.pros = pros;
    }

    public void setSomeList(List list) {
        this.someList = list;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "ComplexObject{" +
                "pros=" + pros +
                ", someList=" + someList +
                ", map=" + map +
                ", set=" + set +
                '}';
    }
}
