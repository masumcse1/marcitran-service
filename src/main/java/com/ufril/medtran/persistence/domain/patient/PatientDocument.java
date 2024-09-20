package com.ufril.medtran.persistence.domain.patient;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patientDocument")
public class PatientDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Patients patient;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date documentDate;
	@ManyToOne
	private DocumentType documentType;
	private String directiveType;
	private String providedBy;
	private String comments;
	@Lob
	private String files;
	private boolean extractImages;
	private boolean convertToGrayScale;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getDirectiveType() {
		return directiveType;
	}

	public void setDirectiveType(String directiveType) {
		this.directiveType = directiveType;
	}

	public String getProvidedBy() {
		return providedBy;
	}

	public void setProvidedBy(String providedBy) {
		this.providedBy = providedBy;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public boolean isExtractImages() {
		return extractImages;
	}

	public void setExtractImages(boolean extractImages) {
		this.extractImages = extractImages;
	}

	public boolean isConvertToGrayScale() {
		return convertToGrayScale;
	}

	public void setConvertToGrayScale(boolean convertToGrayScale) {
		this.convertToGrayScale = convertToGrayScale;
	}

	public Patients getPatient() {
		return patient;
	}

	public void setPatient(Patients patient) {
		this.patient = patient;
	}
}
