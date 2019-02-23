package com.example.yp_skiresort.Mapper;

import java.util.*;
import com.example.yp_skiresort.Entity.SkiResort;

public interface SkiResortMapper  {
    public abstract List<SkiResort> getSkiResortListByCountry(String country);
    public abstract List<SkiResort> getResortListByPriceRange(int max, int min);
    public abstract List<SkiResort> getResortListBySlopeRating(int rating);
    public abstract List<SkiResort> getResortListByPartialName(String resortName);
}
