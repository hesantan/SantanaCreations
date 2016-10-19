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
package realestate.entities.zillow;

import java.util.Date;

/**
 *
 * @author Hector
 */
public class ZillowPropertyReport {

	private long zPid;
	private int taxAssessmentYear;
	private double taxAssessment;
	private int yearBuilt;
	private int lotSizeSquareFeet;
	private int finishedSquareFeet;
	private double bathrooms;
	private int bedrooms;
	private Date lastSoldDate;
	private long lastSoldPrice;
	private String lastSoldPriceCurrency;

	public long getZPid() {
		return zPid;
	}

	public void setZPid(long zPid) {
		this.zPid = zPid;
	}

	public int getTaxAssessmentYear() {
		return taxAssessmentYear;
	}

	public void setTaxAssessmentYear(int taxAssessmentYear) {
		this.taxAssessmentYear = taxAssessmentYear;
	}

	public double getTaxAssessment() {
		return taxAssessment;
	}

	public void setTaxAssessment(double taxAssessment) {
		this.taxAssessment = taxAssessment;
	}

	public int getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(int yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public int getLotSizeSquareFeet() {
		return lotSizeSquareFeet;
	}

	public void setLotSizeSquareFeet(int lotSizeSquareFeet) {
		this.lotSizeSquareFeet = lotSizeSquareFeet;
	}

	public int getFinishedSquareFeet() {
		return finishedSquareFeet;
	}

	public void setFinishedSquareFeet(int finishedSquareFeet) {
		this.finishedSquareFeet = finishedSquareFeet;
	}

	public double getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(double bathrooms) {
		this.bathrooms = bathrooms;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public Date getLastSoldDate() {
		return lastSoldDate;
	}

	public void setLastSoldDate(Date lastSoldDate) {
		this.lastSoldDate = lastSoldDate;
	}

	public void setLastSoldPrice(long lastSoldPrice, String lastSoldPriceCurrency) {
		this.lastSoldPrice = lastSoldPrice;
		this.lastSoldPriceCurrency = lastSoldPriceCurrency;
	}

	public long getLastSoldPrice() {
		return lastSoldPrice;
	}

	public String getLastSoldPriceCurrency() {
		return lastSoldPriceCurrency;
	}
}
