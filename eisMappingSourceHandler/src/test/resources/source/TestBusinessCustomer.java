/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.crm.domain;

import com.exigen.ipb.base.annotations.CanExtend;
import com.exigen.ipb.base.annotations.Lookup;
import com.exigen.ipb.base.datatypes.DataTypeFieldLengthConstants;
import com.exigen.ipb.base.diff.MergeTransient;
import com.exigen.ipb.crm.domain.validation.*;
import com.exigen.ipb.crm.domain.validation.group.*;
import com.exigen.ipb.crm.domain.validation.group.businesscustomer.EINPattern;
import com.exigen.ipb.crm.domain.validation.group.businesscustomer.EINRequired;
import com.exigen.ipb.crm.domain.validation.group.businesscustomer.SSNPattern;
import com.exigen.ipb.crm.domain.validation.group.businesscustomer.SSNRequired;

import com.exigen.ipb.crm.domain.validation.group.BusinessInfoGroup;
import com.exigen.ipb.crm.domain.validation.lookup.ValidLookupCodes;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.*;

/**
 * Business customer entity class.
 *
 * @author Aliaksei Kozich
 * @since 6.1
 */
@CanExtend
@Entity
@DiscriminatorValue(CustomerAndLeadDtypes.BUSINESS_CUSTOMER)
public class BusinessCustomer extends Customer<BusinessDetails> implements SicNaicsHolder {

    private static final long serialVersionUID = 1L;

    @Embedded
    @Valid
    @SicOrNaicsRequired(message="{crm00092}", groups = {SicNaicsGroup.class})
    private BusinessDetails details = new BusinessDetails();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="BusinessCustomerEntityType", joinColumns = @JoinColumn(name="customer_id"))
    @ValidLookupCodes(lookupName = "BusinessEntityType", message = "{BusinessCustomer.businessEntityType.lookup}", groups = {CustomerGroup.class, LeadGroup.class, RelationshipGroup.class})
    private Set<String> entityType = new LinkedHashSet<>();

    /**
     * Indicator to signify customer is a group sponsor
     */
    @Size(Length=DataTypeFieldLengthConstants.NAME_TYPE_FIELD_LENGHT)
    private Boolean groupSponsorInd = false;

    private Integer numberOfEmployees;

    @Column(length = DataTypeFieldLengthConstants.DESC_TYPE_FIELD_LENGHT)
    @Size(max = 255, message = "{crm00032}", groups = {CustomerGroup.class, LeadGroup.class, RelationshipGroup.class})
    private String description;

    private Boolean useAsReference;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="ReferenceCategory", joinColumns = @JoinColumn(name="customer_id"))
    @Column(name="referenceCategory")
    @ValidLookupCodes(lookupName = "ReferenceCategory", message = "{BusinessCustomer.referenceCategory.lookup}", groups = {CustomerGroup.class, LeadGroup.class, RelationshipGroup.class} )
    private Set<String> referenceCategories = new LinkedHashSet<String>();

    @Column(length = DataTypeFieldLengthConstants.DESC_TYPE_FIELD_LENGHT)
    @Size(max = 255, message = "{crm00032}", groups = {CustomerGroup.class, LeadGroup.class, RelationshipGroup.class})
    private String referenceComment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @OrderBy("id")
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = FK_NAME)
    @ForeignKey(name = "FK_CUSTOMER_CUSTOMER_DIVISION")
    @Valid
    private List<Division> divisions = new LinkedList<>();

    private Boolean associateDivisions = false;

    @Column(length = DataTypeFieldLengthConstants.CODE_TYPE_FIELD_LENGHT)
    private String sourceOfExchangeRate;

    @Column(length = DataTypeFieldLengthConstants.NAME_TYPE_FIELD_LENGHT)
    private String rateDateIntake;

    @Column(length = DataTypeFieldLengthConstants.NAME_TYPE_FIELD_LENGHT)
    private String rateDatePayment;

    @MergeTransient
    @Override
    public BusinessDetails getDetails() {
        return details;
    }

    @Override
    public void setDetails(BusinessDetails details) {
        this.details = details;
    }

    @Transient
    @Override
    public String getDisplayValue() {
        return getDetails().getLegalName();
    }

    public Boolean getGroupSponsorInd() {
        return groupSponsorInd;
    }

    public void setGroupSponsorInd(Boolean groupSponsorInd) {
        this.groupSponsorInd = groupSponsorInd;
    }

    public List<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }

    /**
     * At least one Legal Address is required.
     *
     * @return Legal address
     *
     * @author avoitau
     * @since 6.1
     */
    @MergeTransient
    public AddressEntity getLegalAddress() {
        for (AddressEntity custAddress : getDetails().getCommunicationInfo().getAddressInfos()) {
            if (AddressTypeConstants.NON_INDV_LEGAL.equals(custAddress.getAddressTypeCd())) {
                return custAddress;
            }
        }
        return null;
    }

    @NotBlank.List(value = {
            @NotBlank(message = "{crm00084}", groups = {CustomerGroup.EINRequired.class, EINRequired.class}),
            @NotBlank(message = "{crm00085}", groups = {CustomerGroup.SSNRequired.class, SSNRequired.class})
    })
    @Pattern.List(value = {
            @Pattern(regexp = "^\\s*(\\d{2}-\\d{7})?\\s*$", message = "{crm00086}", groups = {CustomerGroup.EINPattern.class, EINPattern.class, RelationshipGroup.EINPattern.class}),
            @Pattern(regexp = "^\\s*(\\d{3}-\\d{2}-\\d{4})?\\s*$", message = "{crm00086}", groups = {CustomerGroup.SSNPattern.class, SSNPattern.class, RelationshipGroup.SSNPattern.class})
    })
    public String getLegalId() {
        return details.getLegalId();
    }

    public void setLegalId(String legalId) {
        details.setLegalId(legalId);
    }


    @NotBlank(message="{crm00081}", groups = {CustomerGroup.class, LeadGroup.class, RelationshipGroup.class})
    @Size(max = 100, message="{crm00005}", groups = {CustomerGroup.class, LeadGroup.class, RelationshipGroup.class})
    public String getLegalName() {
        return details.getLegalName();
    }

    public void setLegalName(String legalName) {
        details.setLegalName(legalName);
    }

    @Size(max = 100, message="{crm00005}", groups = {CustomerGroup.class, LeadGroup.class, RelationshipGroup.class})
    public String getLegalNamePhonetic() {
        return details.getLegalNamePhonetic();
    }

    public void setLegalNamePhonetic(String legalNamePhonetic) {
        details.setLegalNamePhonetic(legalNamePhonetic);
    }

    @NotNull(message="{crm00088}", groups = {BusinessInfoGroup.class})
    @Past(message="{crm00087}", groups = {CustomerGroup.class, LeadGroup.class, RelationshipGroup.class})
    public Date getDateStarted() {
        return details.getDateStarted();
    }

    public void setDateStarted(Date dateStarted) {
        details.setDateStarted(dateStarted);
    }

    @Size(max = 100, message="{crm00005}", groups = {CustomerGroup.class, LeadGroup.class, RelationshipGroup.class})
    public String getDbaName() {
        return details.getDbaName();
    }

    public void setDbaName(String dbaName) {
        details.setDbaName(dbaName);
    }

    @Lookup("NonIndividualType")
    @NotBlank(message="{crm00080}", groups = {CustomerGroup.class})
    @ValidLookupCodes(lookupName = "NonIndividualType", message = "{BusinessCustomer.nonIndividualType.lookup}", groups = {CustomerGroup.class, LeadGroup.class} )
    public String getBusinessType() {
        return details.getBusinessType();
    }

    public void setBusinessType(String businessType) {
        details.setBusinessType(businessType);
    }

    @Lookup("SourceOfExchangeRate")
    @ValidLookupCodes(lookupName = "SourceOfExchangeRate", message = "{BusinessCustomer.sourceOfExchangeRate.lookup}", groups = {CustomerGroup.class, LeadGroup.class} )
    public String getSourceOfExchangeRate() {
        return this.sourceOfExchangeRate;
    }

    public void setSourceOfExchangeRate(String sourceOfExchangeRate) {
        this.sourceOfExchangeRate = sourceOfExchangeRate;
    }

    @Lookup("ProcessExchangeRateDateIntake")
    @ValidLookupCodes(lookupName = "ProcessExchangeRateDateIntake", message = "{BusinessCustomer.processExchangeRateDateIntake.lookup}", groups = {CustomerGroup.class, LeadGroup.class} )
    public String getRateDateIntake() {
        return this.rateDateIntake;
    }

    public void setRateDateIntake(String rateDateIntake) {
        this.rateDateIntake = rateDateIntake;
    }

    @Lookup("ProcessExchangeRateDatePayment")
    @ValidLookupCodes(lookupName = "ProcessExchangeRateDatePayment", message = "{BusinessCustomer.processExchangeRateDatePayment.lookup}", groups = {CustomerGroup.class, LeadGroup.class})
    public String getRateDatePayment() {
        return this.rateDatePayment;
    }

    public void setRateDatePayment(String rateDatePayment) {
        this.rateDatePayment = rateDatePayment;
    }

    @Range(min = 0, max = 99, message="{crm00116}", groups = {CustomerGroup.class, LeadGroup.class, RelationshipGroup.class})
    public Integer getNumberOfContinuous() {
        return details.getNumberOfContinuous();
    }

    public void setNumberOfContinuous(Integer numberOfContinuous) {
        details.setNumberOfContinuous(numberOfContinuous);
    }

    @Range(min = 0, max = 99999999, message="{crm00116}", groups = {CustomerGroup.class, LeadGroup.class})
    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    @MergeTransient
    public Set<String> getEntityType() {
        return entityType;
    }

    public void setEntityType(Set<String> entityType) {
        this.entityType = entityType;
    }

    // SIC (Standard Industrial Classification)

    @Override
    @NotBlank(message="", groups = {SicCodeGroup.class})
    public String getSicDivision() {
        return details.getSicDivision();
    }

    @Override
    public void setSicDivision(String sicDivision) {
        details.setSicDivision(sicDivision);
    }

    @Override
    @NotBlank(message="", groups = {SicCodeGroup.class})
    public String getSicIndustry() {
        return details.getSicIndustry();
    }

    @Override
    public void setSicIndustry(String sicIndustry) {
        details.setSicIndustry(sicIndustry);
    }

    @Override
    @NotBlank(message = "{crm00089}", groups = {SicCodeGroup.class})
    @ValidSicCode(groups = {CustomerGroup.class,LeadGroup.class})
    @Size(max = 20, message = "{crm00028}", groups = {CustomerGroup.class,LeadGroup.class})
    public String getSicCode() {
        return details.getSicCode();
    }

    @Override
    public void setSicCode(String SICCode) {
        details.setSicCode(SICCode);
    }



    // NAICS (North American Industry Classification System)

    @Override
    @NotBlank(message="", groups = {NaicsCodeGroup.class})
    public String getNaicsSector() {
        return details.getNaicsSector();
    }

    @Override
    public void setNaicsSector(String naicsSector) {
        details.setNaicsSector(naicsSector);
    }

    @Override
    @NotBlank(message="", groups = {NaicsCodeGroup.class})
    public String getNaicsSubSector() {
        return details.getNaicsSubSector();
    }

    @Override
    public void setNaicsSubSector(String naicsSubSector) {
        details.setNaicsSubSector(naicsSubSector);
    }

    @Override
    @NotBlank(message="", groups = {NaicsCodeGroup.class})
    public String getNaicsIndustryGroup() {
        return details.getNaicsIndustryGroup();
    }

    @Override
    public void setNaicsIndustryGroup(String naicsIndustryGroup) {
        details.setNaicsIndustryGroup(naicsIndustryGroup);
    }

    @Override
    @NotBlank(message = "{crm00090}", groups = {NaicsCodeGroup.class})
    @ValidNaicsCode(groups = {CustomerGroup.class, LeadGroup.class})
    @Size(max = 20, message = "{crm00028}", groups = {CustomerGroup.class, LeadGroup.class})
    public String getNaicsCode() {
        return details.getNaicsCode();
    }

    @Override
    public void setNaicsCode(String NAICSCode) {
        details.setNaicsCode(NAICSCode);
    }

    public Boolean getUseAsReference() {
        return useAsReference;
    }

    public Set<String> getReferenceCategories() {
        return referenceCategories;
    }

    public String getReferenceComment() {
        return referenceComment;
    }

    public void setUseAsReference(Boolean useAsReference) {
        this.useAsReference = useAsReference;
    }

    public void setReferenceCategories(Set<String> referenceCategories) {
        this.referenceCategories = referenceCategories;
    }

    public void setReferenceComment(String referenceComment) {
        this.referenceComment = referenceComment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAssociateDivisions() {
        return associateDivisions;
    }

    public void setAssociateDivisions(Boolean associateDivisions) {
        this.associateDivisions = associateDivisions;
    }
}
