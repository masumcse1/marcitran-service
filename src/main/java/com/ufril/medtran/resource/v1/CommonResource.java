package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.ContactDTO;
import com.ufril.medtran.dto.common.EmailSubscriptionDTO;
import com.ufril.medtran.dto.common.Message;
import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.exception.BadRequestException;
import com.ufril.medtran.helper.CommonHelper;
import com.ufril.medtran.helper.MessageHelper;
import com.ufril.medtran.helper.ResourceValidationHelper;
import com.ufril.medtran.persistence.domain.account.User;
import com.ufril.medtran.persistence.domain.dispatch.PCRLog;
import com.ufril.medtran.persistence.service.DispatchService;
import com.ufril.medtran.persistence.service.UserService;
import com.ufril.medtran.service.MailSenderService;
import com.ufril.medtran.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController(value = "commonResourceV1")
@RequestMapping(value = { "/v1/", "/oauth2/v1/" })
@Api(value = "common", description = "Miscellaneous API")
public class CommonResource {

	private static Logger logger = Logger.getLogger(CommonResource.class);

	private final CommonHelper commonHelper;
	private final MessageHelper messageHelper;
	private final ResourceValidationHelper validationHelper;
	private final VelocityEngine velocityEngine;

	@Autowired
	private UserService userService;
	@Autowired
	private MailSenderService mailService;
	@Autowired
	DispatchService dispatchService;

	@Autowired
	public CommonResource(VelocityEngine velocityEngine,
						  MessageHelper messageHelper,
						  ResourceValidationHelper validationHelper,
						  CommonHelper commonHelper) {
		this.velocityEngine = velocityEngine;
		this.messageHelper = messageHelper;
		this.validationHelper = validationHelper;
		this.commonHelper = commonHelper;
	}

	@RequestMapping(value = "/common/privacy-policy", method = RequestMethod.GET)
	@ApiOperation(value = "Retrieves a user credit points associated with the username)", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "", response = Response.class),
			@ApiResponse(code = 404, message = "Unable to find User credit points", response = Response.class) })
	public ResponseEntity<?> getPrivacyPolicy() {
		Map<String, Object> model = new HashMap<>();
		final String templateLocation = "templates/privacy/privacyPolicy.vm";
		String text = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, templateLocation, "UTF-8", model);
		text = text.replaceAll("(\r\n|\n)", "<br />");
		text = text.replaceAll("\t", "");
		return new ResponseEntity<>(new Response(StatusType.OK, text),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/commons/uploads/s3", method = RequestMethod.POST,
			produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	@ApiOperation(value = "Upload images/docs file into s3 bucket and get the url", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "", response = Response.class),
			@ApiResponse(code = 404, message = "Upload failed", response = Response.class),
			@ApiResponse(code = 413, message = "Payload Too Large", response = Response.class),
			@ApiResponse(code = 500, message = "Access denied", response = Response.class), })
	public ResponseEntity<?> uploadFileS3Bucket(
			@RequestParam("file") MultipartFile multipartFile,
			Principal principal, Locale locale) {
		Message message = new Message();
		if (!multipartFile.isEmpty()) {
//			String uploadFileUrl = commonHelper.uploadFileToS3Bucket(multipartFile, principal.getName(), locale);
			message.setCode(200);
//			message.setMessage(uploadFileUrl);
		} else {
			throw new BadRequestException(messageHelper.getMessage(
					"message.uploadFailed", locale));
		}

		return new ResponseEntity<>(new Response(StatusType.OK, message),
				HttpStatus.OK);
	}


	@ApiOperation(
			value = "Upload images/docs/file",
			response = Response.class
	)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "", response = Response.class),
			@ApiResponse(code = 404, message = "Upload failed", response = Response.class),
			@ApiResponse(code = 413, message = "Payload Too Large", response = Response.class),
			@ApiResponse(code = 500, message = "Access denied", response = Response.class),
	})
	@RequestMapping(
			value = "/common/upload",
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
	)
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, Locale locale) {

		//Locale locale
		// 1. upload file in temp then copy to files folder
		// 2. return file path in server
		logger.debug("Uploading file: " + file.getOriginalFilename());

		String message = "";
		String name = file.getOriginalFilename();
		File upfile = new File(Utils.getFilePath() + File.separator + name);
		logger.debug("Upload Path: " + upfile.getAbsolutePath());

		if (!file.isEmpty()) {
			try (BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(upfile))) {

				FileCopyUtils.copy(file.getInputStream(), stream);
				stream.close();
				message =Utils.getBaseUrl()+ Utils.getRelativeFilePath(name);
			} catch (Exception e) {
				throw new BadRequestException(e.getMessage());
			}
		} else {
			throw new
					BadRequestException(messageHelper.buildMessage(HttpStatus.BAD_REQUEST,
					"message.uploadFailed", null, locale).getMessage());
		}
		//locale

	 return new ResponseEntity<>(new Response(StatusType.OK, message), HttpStatus.OK);
	 }

	@RequestMapping(value = "/common/getfile/{filename}", method = RequestMethod.GET)
	@ApiOperation(
			value = "Retrieve images/docs/file form server",
			response = FileSystemResource.class
	)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "", response = Response.class),
			@ApiResponse(code = 404, message = "File Not Found", response = Response.class),
			@ApiResponse(code = 500, message = "Access Denied", response = Response.class)
	})
	public FileSystemResource retriveFile(@PathVariable("filename") String fileName, Locale locale) throws Exception {

		logger.debug("Retrieve file: " + fileName);

		File file = new File(Utils.getFilePath() + File.separator + fileName);
		logger.debug("File path: " + file.getAbsolutePath());

		FileSystemResource res = new FileSystemResource(file);
		if (!res.exists()) {
			throw new BadRequestException(messageHelper.buildMessage(HttpStatus.BAD_REQUEST,
					"message.fileNotFound", null, locale).getMessage());
		}

		logger.debug("file size: " + String.valueOf(res.contentLength()));

		return res;
	}

/*
	 @RequestMapping(
	 value = "/common/uploadData",
	 method = RequestMethod.POST,
	 produces = {MediaType.APPLICATION_JSON_VALUE}
	 )
	 @ApiOperation(
	 value = "Post bytes of images/docs/file for provider subscription",
	 response = Response.class
	 )
	 @ApiResponses(value = {
	 @ApiResponse(code = 200, message = "", response = Response.class),
	 @ApiResponse(code = 404, message = "Upload failed", response =
	 Response.class),
	 @ApiResponse(code = 500, message = "Access denied", response =
	 Response.class)
	 })
	 public ResponseEntity<?> uploadFile(
	 @RequestParam("name") String filename,
	 @RequestParam("data") byte[] file, Locale locale ) {
	 logger.debug("Uploading file: " + filename);

	 if ( filename == null || filename.isEmpty()) {
	 throw new
	 BadRequestException(messageHelper.buildMessage(HttpStatus.BAD_REQUEST,
	 "message.uploadFailed", null, locale).getMessage());
	 }

	 String message = "";
	 String name = filename;
	 File upfile = new File(Utils.getFilePath() + File.separator + name);
	 logger.debug("Upload Path: " + upfile.getAbsolutePath());

	 if (file != null && file.length != 0) {
	 try (BufferedOutputStream stream = new BufferedOutputStream(
	 new FileOutputStream(upfile))) {
	 // Read from byte array stream
	 FileCopyUtils.copy(new ByteArrayInputStream(file), stream);
	 stream.close();
	 message = Utils.getBaseUrl()+Utils.getRelativeFilePath(name);
	 } catch (Exception e) {
	 throw new
	 BadRequestException(messageHelper.buildMessage(HttpStatus.BAD_REQUEST,
	 "message.uploadFailed", null, locale).getMessage());
	 }
	 } else {
	 throw new
	 BadRequestException(messageHelper.buildMessage(HttpStatus.BAD_REQUEST,
	 "message.uploadFailed", null, locale).getMessage());
	 }

	 return new ResponseEntity<>(new Response(StatusType.OK, message),
	 HttpStatus.OK);
	 }
*/

	@RequestMapping(value = "/common/email-subscriptions", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Subscribe your email subscription mail, to get email about various offers etc.", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "", response = Response.class),
			@ApiResponse(code = 404, message = "Email subscription failed", response = Response.class),
			@ApiResponse(code = 500, message = "Access denied", response = Response.class) })
	public ResponseEntity<?> emailSubscription(
			@Valid @RequestBody EmailSubscriptionDTO subscriptionDTO,
			Locale locale) {
		// STEP 1: check if the email already exist
//		validationHelper.isEmailSubscriptionFound(subscriptionDTO.getEmail(), locale);
		// STEP 2: save the data
//		commonService.subscribeWitEmail(subscriptionDTO.getName(), subscriptionDTO.getEmail());
		// STEP 3: build success message
		Message message = messageHelper.buildMessage200("message.emailSubscriptionSuccess", locale);
		return new ResponseEntity<>(new Response(StatusType.OK, message), HttpStatus.OK);
	}


	@RequestMapping(value = "/common/contactus", method = RequestMethod.POST,
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Save informtaion for contact us",
			notes = "Save contact us information", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "", response = Response.class),
			@ApiResponse(code = 404, message = "data was saved successfull", response = Response.class),
			@ApiResponse(code = 500, message = "Access denied", response = Response.class) })
	public ResponseEntity<?> contactUs(
			@Valid @RequestBody ContactDTO contactDTO, Locale locale) {
//		commonService.saveContact(contactDTO);
		Message message = messageHelper.buildMessage200(
				"message.contactSuccess", locale);
		return new ResponseEntity<>(new Response(StatusType.OK, message),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/common/sendpcr/{dispatchId}/{email}", method = RequestMethod.GET)
	public ResponseEntity<?> sendPCRReport(@PathVariable("dispatchId") int dispatchId, @PathVariable("email") String email) {
		PCRLog pcrLog = dispatchService.getPCRByDispatchId(dispatchId);

		mailService.sendPcrReport(pcrLog, email);
		return new ResponseEntity<>(new Response(StatusType.OK, "PCR sent via email"),
				HttpStatus.OK);
	}

}
