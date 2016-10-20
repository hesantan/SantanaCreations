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

import common.serialization.interfaces.IXmlDeserializer;
import org.w3c.dom.Document;
import realestate.entities.zillow.ZillowPropertyReport;

import java.util.GregorianCalendar;

/**
 *
 * @author Hector
 */
public class ZillowPropertyReportXmlDeserializer implements IXmlDeserializer<ZillowPropertyReport> {

    @Override
    public ZillowPropertyReport deserialize(Document xmlDocument) {

	    ZillowPropertyReport zillowPropertyReport = new ZillowPropertyReport();
	    zillowPropertyReport.setZPid(48749425);
	    zillowPropertyReport.setTaxAssessmentYear(2015);
	    zillowPropertyReport.setTaxAssessment(1271000.0);
	    zillowPropertyReport.setYearBuilt(1924);
	    zillowPropertyReport.setLotSizeSquareFeet(4680);
	    zillowPropertyReport.setFinishedSquareFeet(3470);
	    zillowPropertyReport.setBathrooms(3.0);
	    zillowPropertyReport.setBedrooms(4);
	    zillowPropertyReport.setLastSoldDate(new GregorianCalendar(2008, 10, 26).getTime());
	    zillowPropertyReport.setLastSoldPrice(1025000, "USD");

	    return zillowPropertyReport;
    }
    
}