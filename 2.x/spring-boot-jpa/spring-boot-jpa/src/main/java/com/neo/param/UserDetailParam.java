package com.neo.param;


import com.neo.model.Address;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

public class UserDetailParam {
    private String userId;
    private Integer minAge;
    private Integer maxAge;
    private String realName;
    private String introduction;
    private String city;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
