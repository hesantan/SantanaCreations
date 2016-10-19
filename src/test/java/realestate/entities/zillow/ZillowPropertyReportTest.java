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

package realestate.entities.zillow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Test methods for ZillowPropertyReportTest
 *
 * @author Hector
 * @since 10/17/2016
 */
public class ZillowPropertyReportTest {

	private ZillowPropertyReport zillowPropertyReport;

	public ZillowPropertyReportTest() {
		zillowPropertyReport = new ZillowPropertyReport();
	}

	@Before
	public void setUp() {
		zillowPropertyReport = new ZillowPropertyReport();
		zillowPropertyReport.setZPid(12345678);
		zillowPropertyReport.setTaxAssessmentYear(2016);
		zillowPropertyReport.setTaxAssessment(1234567.0);
		zillowPropertyReport.setYearBuilt(1990);
		zillowPropertyReport.setLotSizeSquareFeet(1234);
		zillowPropertyReport.setFinishedSquareFeet(5678);
		zillowPropertyReport.setBathrooms(1.2);
		zillowPropertyReport.setBedrooms(5);
		zillowPropertyReport.setLastSoldDate(new Date());
		zillowPropertyReport.setLastSoldPrice(125000, "USD");
	}

	@Test
	public void initTest() {
		Assert.assertEquals(zillowPropertyReport.getZPid(), 12345678);
		Assert.assertEquals(zillowPropertyReport.getTaxAssessmentYear(), 2016);
		Assert.assertTrue(zillowPropertyReport.getTaxAssessment() == 1234567.0);
		Assert.assertEquals(zillowPropertyReport.getYearBuilt(), 1990);
		Assert.assertEquals(zillowPropertyReport.getLotSizeSquareFeet(), 1234);
		Assert.assertEquals(zillowPropertyReport.getFinishedSquareFeet(), 5678);
		Assert.assertTrue(zillowPropertyReport.getBathrooms() == 1.2);
		Assert.assertEquals(zillowPropertyReport.getBedrooms(), 5);
		Assert.assertNotNull(zillowPropertyReport.getLastSoldDate());
		Assert.assertEquals(zillowPropertyReport.getLastSoldPrice(), 125000);
		Assert.assertEquals(zillowPropertyReport.getLastSoldPriceCurrency(), "USD");
	}
}