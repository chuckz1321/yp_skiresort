package com.example.yp_skiresort.Controller;

import com.example.yp_skiresort.Entity.ResponseMessage;
import com.example.yp_skiresort.Entity.TUser;
import com.example.yp_skiresort.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value="welcome", notes="welcome by url id")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseMessage welcome(@PathVariable Long id) {
        ResponseMessage<String> rm = new ResponseMessage<>();
        rm.setHttpCode("200");
        rm.setResponseBody("welcome " + id + "to ski test");
        return rm;
    }

    @ApiOperation(value="Test", notes="dbtest")
    @RequestMapping(value="/getAllUser", method= RequestMethod.GET)
    public ResponseMessage welcome() {
        ResponseMessage<List<TUser>> rm = new ResponseMessage<>();
        rm.setHttpCode("200");
        rm.setResponseBody(userService.getAllUser());
        return rm;
    }


}
