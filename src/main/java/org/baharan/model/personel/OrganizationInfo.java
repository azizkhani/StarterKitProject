package org.baharan.model.personel;

import java.io.Serializable;
import java.util.Date;

import org.baharan.model.BaseEntity;
import org.baharan.model.organization.OrganizationStructure;

public class OrganizationInfo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8951867660193937378L;
	private String className;
	private Integer classificationId;
	private Long userId;
	private String personCode;
	private String inspectionSpecialCode;
	private Long coordinationCode;
	private String membershipDate;
	private String plaqueNumber;
	private String veteranPercentage;
	private String frontPeriod;
	private String captivityPeriod;
	private String degreeDate;
	private String individualEvaluationScore;
	private String organizationEvaluationId;
	private String retirementDate;
	private Date createDate;
	private Integer membershipTypeId;
	private Integer mainCategoryId;
	private Integer cautionCategoryId;
	private Integer soldieringStatusId;
	private Integer degreeId;
	private Integer degreeSalaryBasisId;
	private Long evidenceSalaryBasisId;
	private Integer evidenceEducationNameId;
	private Long studySectionId;
	private Integer organizationPositionId;
	private Long servicePlaceId;
	private OrganizationStructure organizationStructure;
	private Long missionPlaceId;
	private Long organizationJobId;
	private Integer positiveYears;
	private Integer nagativeYears;
	private Long personalFileLocationId;
	private Long responsibilityFileLocationId;
	private Integer nationalityId;
	private Integer commandeStatusId;
	private Integer sendStatus;
	private Long jobUserId;
	private String Status;
	private Boolean isRetireMentChangeStatus = Boolean.FALSE;
	
	
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getClassificationId() {
		return classificationId;
	}
	public void setClassificationId(Integer classificationId) {
		this.classificationId = classificationId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	public String getInspectionSpecialCode() {
		return inspectionSpecialCode;
	}
	public void setInspectionSpecialCode(String inspectionSpecialCode) {
		this.inspectionSpecialCode = inspectionSpecialCode;
	}
	public Long getCoordinationCode() {
		return coordinationCode;
	}
	public void setCoordinationCode(Long coordinationCode) {
		this.coordinationCode = coordinationCode;
	}
	public String getMembershipDate() {
		return membershipDate;
	}
	public void setMembershipDate(String membershipDate) {
		this.membershipDate = membershipDate;
	}
	public String getPlaqueNumber() {
		return plaqueNumber;
	}
	public void setPlaqueNumber(String plaqueNumber) {
		this.plaqueNumber = plaqueNumber;
	}
	public String getVeteranPercentage() {
		return veteranPercentage;
	}
	public void setVeteranPercentage(String veteranPercentage) {
		this.veteranPercentage = veteranPercentage;
	}
	public String getFrontPeriod() {
		return frontPeriod;
	}
	public void setFrontPeriod(String frontPeriod) {
		this.frontPeriod = frontPeriod;
	}
	public String getCaptivityPeriod() {
		return captivityPeriod;
	}
	public void setCaptivityPeriod(String captivityPeriod) {
		this.captivityPeriod = captivityPeriod;
	}
	public String getDegreeDate() {
		return degreeDate;
	}
	public void setDegreeDate(String degreeDate) {
		this.degreeDate = degreeDate;
	}
	public String getIndividualEvaluationScore() {
		return individualEvaluationScore;
	}
	public void setIndividualEvaluationScore(String individualEvaluationScore) {
		this.individualEvaluationScore = individualEvaluationScore;
	}
	public String getOrganizationEvaluationId() {
		return organizationEvaluationId;
	}
	public void setOrganizationEvaluationId(String organizationEvaluationId) {
		this.organizationEvaluationId = organizationEvaluationId;
	}
	public OrganizationStructure getOrganizationStructure() {
		return organizationStructure;
	}
	public void setOrganizationStructure(OrganizationStructure organizationStructure) {
		this.organizationStructure = organizationStructure;
	}
	public String getRetirementDate() {
		return retirementDate;
	}
	public void setRetirementDate(String retirementDate) {
		this.retirementDate = retirementDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getMembershipTypeId() {
		return membershipTypeId;
	}
	public void setMembershipTypeId(Integer membershipTypeId) {
		this.membershipTypeId = membershipTypeId;
	}
	public Integer getMainCategoryId() {
		return mainCategoryId;
	}
	public void setMainCategoryId(Integer mainCategoryId) {
		this.mainCategoryId = mainCategoryId;
	}
	public Integer getCautionCategoryId() {
		return cautionCategoryId;
	}
	public void setCautionCategoryId(Integer cautionCategoryId) {
		this.cautionCategoryId = cautionCategoryId;
	}
	public Integer getSoldieringStatusId() {
		return soldieringStatusId;
	}
	public void setSoldieringStatusId(Integer soldieringStatusId) {
		this.soldieringStatusId = soldieringStatusId;
	}
	public Integer getDegreeId() {
		return degreeId;
	}
	public void setDegreeId(Integer degreeId) {
		this.degreeId = degreeId;
	}
	public Integer getDegreeSalaryBasisId() {
		return degreeSalaryBasisId;
	}
	public void setDegreeSalaryBasisId(Integer degreeSalaryBasisId) {
		this.degreeSalaryBasisId = degreeSalaryBasisId;
	}
	public Long getEvidenceSalaryBasisId() {
		return evidenceSalaryBasisId;
	}
	public void setEvidenceSalaryBasisId(Long evidenceSalaryBasisId) {
		this.evidenceSalaryBasisId = evidenceSalaryBasisId;
	}
	public Integer getEvidenceEducationNameId() {
		return evidenceEducationNameId;
	}
	public void setEvidenceEducationNameId(Integer evidenceEducationNameId) {
		this.evidenceEducationNameId = evidenceEducationNameId;
	}
	public Long getStudySectionId() {
		return studySectionId;
	}
	public void setStudySectionId(Long studySectionId) {
		this.studySectionId = studySectionId;
	}
	public Integer getOrganizationPositionId() {
		return organizationPositionId;
	}
	public void setOrganizationPositionId(Integer organizationPositionId) {
		this.organizationPositionId = organizationPositionId;
	}
	public Long getServicePlaceId() {
		return servicePlaceId;
	}
	public void setServicePlaceId(Long servicePlaceId) {
		this.servicePlaceId = servicePlaceId;
	}
	public Long getMissionPlaceId() {
		return missionPlaceId;
	}
	public void setMissionPlaceId(Long missionPlaceId) {
		this.missionPlaceId = missionPlaceId;
	}
	public Long getOrganizationJobId() {
		return organizationJobId;
	}
	public void setOrganizationJobId(Long organizationJobId) {
		this.organizationJobId = organizationJobId;
	}
	public Integer getPositiveYears() {
		return positiveYears;
	}
	public void setPositiveYears(Integer positiveYears) {
		this.positiveYears = positiveYears;
	}
	public Integer getNagativeYears() {
		return nagativeYears;
	}
	public void setNagativeYears(Integer nagativeYears) {
		this.nagativeYears = nagativeYears;
	}
	public Long getPersonalFileLocationId() {
		return personalFileLocationId;
	}
	public void setPersonalFileLocationId(Long personalFileLocationId) {
		this.personalFileLocationId = personalFileLocationId;
	}
	public Long getResponsibilityFileLocationId() {
		return responsibilityFileLocationId;
	}
	public void setResponsibilityFileLocationId(Long responsibilityFileLocationId) {
		this.responsibilityFileLocationId = responsibilityFileLocationId;
	}
	public Integer getNationalityId() {
		return nationalityId;
	}
	public void setNationalityId(Integer nationalityId) {
		this.nationalityId = nationalityId;
	}
	public Integer getCommandeStatusId() {
		return commandeStatusId;
	}
	public void setCommandeStatusId(Integer commandeStatusId) {
		this.commandeStatusId = commandeStatusId;
	}
	public Integer getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}
	public Long getJobUserId() {
		return jobUserId;
	}
	public void setJobUserId(Long jobUserId) {
		this.jobUserId = jobUserId;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Boolean getIsRetireMentChangeStatus() {
		return isRetireMentChangeStatus;
	}
	public void setIsRetireMentChangeStatus(Boolean isRetireMentChangeStatus) {
		this.isRetireMentChangeStatus = isRetireMentChangeStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
