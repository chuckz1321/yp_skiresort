package com.example.yp_skiresort.Dao;
import org.springframework.stereotype.Repository;

@Repository
public interface SkiResortDao {
    public abstract void getResortListByCountry(String country);
    public abstract void getResortListByPriceRange(int max, int min);
    public abstract void getResortListBySlopeRating(int rating);
    public abstract void getResortListByPartialName(String resortName);
}
