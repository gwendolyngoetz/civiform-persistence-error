package models;

import io.ebean.annotation.DbJson;

import javax.persistence.*;

import play.data.validation.Constraints;

@Entity
@Table(name = "applicants")
public class Applicant {
    @Id
    public Long id;

    @Constraints.Required
    @DbJson

    private String object;

    private ApplicantData applicantData;

    public ApplicantData getApplicantData() {
        // Can get past the error if markAsDirty is called manually, but looking for a
        // more generalized way to do this

        // io.ebean.DB.markAsDirty(this);

        if (applicantData == null && (object != null && !object.isEmpty())) {
            applicantData = new ApplicantData(object);
        } else if (applicantData == null) {
            applicantData = new ApplicantData();
        }
        return applicantData;

    }

    @PrePersist
    @PreUpdate
    public void synchronizeObject() {
        this.object = getApplicantData().asJsonString();
    }
}
