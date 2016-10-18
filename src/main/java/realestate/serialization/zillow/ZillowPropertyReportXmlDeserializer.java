/*
 * Copyright 2016 Santana Creations.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package realestate.serialization.zillow;

import org.w3c.dom.Document;
import realestate.entities.zillow.ZillowPropertyReport;
import santanacommon.serialization.interfaces.IXmlDeserializer;

import java.util.Date;

/**
 *
 * @author Hector
 */
public class ZillowPropertyReportXmlDeserializer implements IXmlDeserializer<ZillowPropertyReport> {

    @Override
    public ZillowPropertyReport deserialize(Document xmlDocument) {
	    ZillowPropertyReport zillowPropertyReport = new ZillowPropertyReport();
	    zillowPropertyReport.zPid = 12345678;
	    zillowPropertyReport.taxAssessmentYear = 2015;
	    zillowPropertyReport.taxAssessment = 1234567.0;
	    zillowPropertyReport.yearBuilt = 1990;
	    zillowPropertyReport.lotSizeSquareFeet = 1234;
	    zillowPropertyReport.finishedSquareFeet = 5678;
	    zillowPropertyReport.bathrooms = 1.2;
	    zillowPropertyReport.bedrooms = 4;
	    zillowPropertyReport.lastSoldDate = new Date();
	    zillowPropertyReport.lastSoldPrice = 125000;
	    zillowPropertyReport.lastSoldPriceCurrency = "USD";

	    return zillowPropertyReport;
    }
    
}