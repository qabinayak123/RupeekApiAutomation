package com.rupeek.RestAssured.testcases;

import com.rupeek.RestAssured.API.GetCustomerApi;
import com.rupeek.RestAssured.setUp.TestSetup;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetUserApi extends TestSetup {
    @DataProvider(name = "body")
    public Object[] body() throws IOException {
        JsonPath path = JsonPath.from(new File("src\\test\\resources\\testdata\\testData.json"));
        List<HashMap<String, Object>> maps = path.getList("testDataList");
        Object[] hashMaps = maps.toArray();
        return hashMaps;
    }
    @Test(dataProvider="body")
    public void getCustomer(HashMap<String, Object> map){
        testLevelLogger.get().assignAuthor("Binayak Dash");
        testLevelLogger.get().assignCategory("Sanity");
        Response Authenticateresponse= GetCustomerApi.authenticate();
        Assert.assertEquals(Authenticateresponse.getStatusCode(),200);
        String token=Authenticateresponse.path("token");
        Response getAllCustomerResponse=GetCustomerApi.GetCustomers(token);
        Assert.assertEquals(getAllCustomerResponse.getStatusCode(),200);
        Response getCustomerwithPhone=GetCustomerApi.GetCustomerswithPhone(token,map.get("phone").toString());
        Assert.assertEquals(getCustomerwithPhone.getStatusCode(),map.get("StatusCode"));
        Assert.assertEquals(getCustomerwithPhone.path("first_name"),map.get("firstName"));
    }

}
