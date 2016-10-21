/*
 * Copyright 2016 Santana Creations.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package realestate.serialization.zillow;

import common.serialization.interfaces.IXmlDeserializer;
import org.w3c.dom.Document;
import realestate.datatransferobjects.ZillowResponseBody;
import realestate.datatransferobjects.ZillowResponseMessage;
import realestate.datatransferobjects.ZillowResponseRequest;
import realestate.datatransferobjects.ZillowWebServiceResponse;

/**
 * XML Deserializer for ZillowWebServiceResponse
 *
 * @author Hector
 * @since 10/20/2016
 */
public class ZillowWebServiceResponseXmlDeserializer implements IXmlDeserializer<ZillowWebServiceResponse> {
	
	@Override
	public ZillowWebServiceResponse deserialize(Document xmlDocument) {
		ZillowWebServiceResponse zillowWebServiceResponse = new ZillowWebServiceResponse();
		
		ZillowResponseRequest zillowResponseRequest = new ZillowResponseRequest();
		ZillowResponseMessage zillowResponseMessage = new ZillowResponseMessage();
		ZillowResponseBody zillowResponseBody = new ZillowResponseBody();
		
		zillowWebServiceResponse.setRequest(zillowResponseRequest);
		zillowWebServiceResponse.setMessage(zillowResponseMessage);
		zillowWebServiceResponse.setBody(zillowResponseBody);
		
		return zillowWebServiceResponse;
	}
}
