package com.example.yp_skiresort.Controller;

import com.example.yp_skiresort.Entity.RequestQuery;
import com.example.yp_skiresort.Entity.ResponseMessage;
import com.example.yp_skiresort.Entity.SkiResort;
import com.example.yp_skiresort.service.impl.SkiResortSvcImpl;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SkiResortController {
    @Autowired
    SkiResortSvcImpl svc;

    @RequestMapping(value="/test",method=RequestMethod.GET)
    @ResponseBody
    public ResponseMessage<List<SkiResort>> test(@RequestParam("country") String country){
        svc.getResortListByPartialName("aarjaeng");
        svc.getResortListByPriceRange(100,0);
        svc.getResortListBySlopeRating(Float.parseFloat("2.0"));
        ResponseMessage message = new ResponseMessage();
        message.setResponseBody(svc.getSkiResortListByCountry(country));
        message.setHttpCode("200");
        return message;
    }

    @ApiOperation(value="search ", notes="search skiresort information ")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<SkiResort> processQuery(@RequestBody RequestQuery inputQuery){
        String query = inputQuery.getQuery();
        List<SkiResort> resorts = new ArrayList<SkiResort>();
        String[] subqueries = query.split("&");
        for(String subquery: subqueries){
            String[] queryParts = subquery.split("=");
            switch(queryParts[0]){
                case "country":
                    if( !queryParts[1].equals("") ) {
                        resorts = svc.getSkiResortListByCountry(queryParts[1]);
                    }
                    break;
                case "price":
                    if( !queryParts[1].equals("") ){
                        String[] range_end = queryParts[1].split("-");
                        int min = Integer.parseInt(range_end[0]);
                        int max = Integer.parseInt(range_end[1]);
                        resorts = svc.getResortListByPriceRange(max,min);
                    }
                    break;
                case "resortname":
                    if (!queryParts[1].equals("")){
                        resorts = svc.getResortListByPartialName(queryParts[1]);
                    }
                    break;
                case "sloperate":
                    if (!queryParts[1].equals("")){
                        resorts = svc.getResortListBySlopeRating(Integer.parseInt(queryParts[1]));
                    }
                    break;
            }
        }

        ResponseMessage<List<SkiResort>> message = new ResponseMessage<List<SkiResort>>();
        message.setResponseBody(resorts);
        return resorts;
    }
}
