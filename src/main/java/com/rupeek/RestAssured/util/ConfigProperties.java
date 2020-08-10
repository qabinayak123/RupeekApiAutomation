package com.rupeek.RestAssured.util;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ 
	"file:src/test/resources/propertyFiles/config.Properties" // mention the property file name
})
public interface ConfigProperties extends Config{
	
	@Key("baseURI")
	public String getBaseURI();
	
	@Key("basePath")
	public String getBasePath();
	
	@Key("GetcustomerAPIEndPoint")
	public String getGetcustomerAPIEndPoint();
	
	@Key("GetSpecificCustomerEndpoint")
	public String getGetSpecificCustomerEndpoint();

	@Key("AuthenticateEndpoint")
	public String getAuthenticateEndpoint();
}
