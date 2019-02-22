package com.example.yp_skiresort.Dao;
import java.util.*;
import com.example.yp_skiresort.Entity.SkiResort;
import org.springframework.stereotype.Repository;

@Repository
public interface SkiResortDao {
    public abstract List<SkiResort> getResortListByCountry(String country);
    public abstract List<SkiResort> getResortListByPriceRange(int max, int min);
    public abstract List<SkiResort> getResortListBySlopeRating(int rating);
    public abstract List<SkiResort> getResortListByPartialName(String resortName);
}
