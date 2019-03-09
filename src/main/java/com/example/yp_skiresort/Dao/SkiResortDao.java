package com.example.yp_skiresort.Dao;
import java.util.*;
import com.example.yp_skiresort.Entity.SkiResort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SkiResortDao {
    public abstract List<SkiResort> getSkiResortListByCountry(String country);
    public abstract List<SkiResort> getResortListByPriceRange(@Param("max") int max, @Param("min") int min);
    public abstract List<SkiResort> getResortListBySlopeRating(int rating);
    public abstract List<SkiResort> getResortListByPartialName(String resortName);
}
