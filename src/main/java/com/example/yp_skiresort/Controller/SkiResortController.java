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
        //svc.getResortListByPartialName("aarjaeng");
        //svc.getResortListByPriceRange(100,0);
        //svc.getResortListBySlopeRating(Float.parseFloat("2.0"));
        svc.getResortListByMultipleConditions("aarjaeng",2000, 0,2,"Canada");
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
        if(subqueries.length == 1){
            String[] queryParts = subqueries[0].split("=");
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
                case "sloperating":
                    if (!queryParts[1].equals("")){
                        resorts = svc.getResortListBySlopeRating(Integer.parseInt(queryParts[1]));
                    }
                    break;
            }
        }
        else{
            String country = "";
            int max = -1;
            int min = -1;
            String resortName = "";
            float rating = 0;
            for(String subquery:subqueries){
                String[] queryParts = subquery.split("=");
                switch(queryParts[0]){
                    case "country":
                        if( !queryParts[1].equals("") ) {
                            country = queryParts[1];
                        }
                        break;
                    case "price":
                        if( !queryParts[1].equals("") ) {
                            String[] range_end = queryParts[1].split("=");
                            max = Integer.parseInt(range_end[1]);
                            min = Integer.parseInt(range_end[0]);
                        }
                        break;
                    case "resortname":
                        if( !queryParts[1].equals("") ){
                            resortName = queryParts[1];
                        }
                        break;
                    case "sloperating":
                        if( !queryParts[1].equals("") ){
                            rating = Float.parseFloat(queryParts[1]);
                        }
                        break;

                }
            }
            resorts = svc.getResortListByMultipleConditions(resortName,max,min,rating,country);
        }


        ResponseMessage<List<SkiResort>> message = new ResponseMessage<List<SkiResort>>();
        message.setResponseBody(resorts);
        return resorts;
    }
}
