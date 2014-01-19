package org.azizkhani.model.personel;


import java.util.Set;

import org.azizkhani.model.BaseEntity;
import org.azizkhani.model.baseInfo.BaseInformation;
import org.azizkhani.model.location.Location;

 

public class Personel extends BaseEntity  {
	
    private String personCode;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String nationalityNumber;
    private String idNumber;
    private String birthDate;
    private boolean gender;
    private boolean maritalStatus;
    private String licenseNumber;
    private String phoneNumber;
    private String mobile;
    private String address;
    private String accountNumber;
    private String description;
	
	private Location location;
	private BaseInformation bank;
	private BaseInformation educationLevel;
    private BaseInformation licenseType;
    private Set<BaseInformation> actors;
	private OrganizationInfo organizationInfo;


	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName(){
		return this.firstName+" "+this.lastName;
	}

	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}


	public String getNationalityNumber() {
		return nationalityNumber;
	}
	public void setNationalityNumber(String nationalityNumber) {
		this.nationalityNumber = nationalityNumber;
	}


	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}


	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}


	public boolean isMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(boolean maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}


	public BaseInformation getBank() {
		return bank;
	}
	public void setBank(BaseInformation bank) {
		this.bank = bank;
	}


	public BaseInformation getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(BaseInformation educationLevel) {
		this.educationLevel = educationLevel;
	}


	public BaseInformation getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(BaseInformation licenseType) {
		this.licenseType = licenseType;
	}


	public OrganizationInfo getOrganizationInfo() {
		return organizationInfo;
	}
	public void setOrganizationInfo(OrganizationInfo organizationInfo) {
		this.organizationInfo = organizationInfo;
	}
	
	public Set<BaseInformation> getActors() {
		return actors;
	}
	public void setActors(Set<BaseInformation> actors) {
		this.actors = actors;
	}
	
}
