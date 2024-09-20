package com.ufril.medtran.dto.patient;

import java.util.Date;

public class PatientDocumentDTO {

	private int id;
	private int patientId;
	private Date documentDate;
	private String documentType;
	private String directiveType;
	private String providedBy;
	private String comments;
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

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
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

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
}
