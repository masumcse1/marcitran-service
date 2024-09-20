package com.ufril.medtran.helper;

//import com.amazonaws.s3.api.AmazonS3Service;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectResult;

//import BadRequestException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author moin
 */
@Component
@PropertySource("classpath:aws.properties")
public class CommonHelper {

	private static Logger logger = Logger.getLogger(CommonHelper.class);

	@Value("${aws.s3.endpoint}")
	private String endpointS3;
	@Value("${aws.s3.bucket-name}")
	private String bucketName;
	@Value("${aws.s3.image-prefix}")
	private String imagePrefix;
	@Value("${aws.s3.file-prefix}")
	private String filePrefix;
	@Value("${aws.s3.default-prefix}")
	private String defaultPrefix;

//    @Autowired
//    private AmazonS3Service amazonS3Service;


//	public String uploadFileToS3Bucket(MultipartFile multipartFile, String username, Locale locale) {
//
//		StringBuilder key = new StringBuilder();
//
//		ObjectMetadata objectMetadata = new ObjectMetadata();
//		objectMetadata.setContentType(multipartFile.getContentType());
//		logger.debug("Data Length : " + multipartFile.getSize());
//		objectMetadata.setContentLength(multipartFile.getSize());
//		//byte[] resultByte = DigestUtils.md5(inputStream);
//		//String streamMD5 = new String(Base64.encodeBase64(resultByte));
//		//logger.debug("Stream MD5 : "+ streamMD5);
//		//objectMetadata.setContentMD5(streamMD5);
//		logger.debug("Uploading file: " + multipartFile.getOriginalFilename());
//		String originalFilename = multipartFile.getOriginalFilename();
//		// 2. get image formet from file name
//		String fileFormat = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//		String fileName = originalFilename.substring(originalFilename.lastIndexOf("/") + 1, originalFilename.lastIndexOf("."));
//
//		Pattern imagePattern = Pattern.compile("((png|jpg|jpeg|PNG|JPG|JPEG)$)");
//		Matcher imageMatcher = imagePattern.matcher(fileFormat);
//
//		Pattern docPattern = Pattern.compile("((doc|docx|pdf|DOC|DOCX|PDF)$)");
//		Matcher docMatcher = docPattern.matcher(fileFormat);
//
//		if (imageMatcher.matches()) {
//			key.append(imagePrefix).append("/");
//		} else if (docMatcher.matches()) {
//			key.append(filePrefix).append("/");
//		} else {
//			key.append(defaultPrefix).append("/");
//		}
//
//		key.append(username).append("/").append(fileName).append("_").append(Calendar.getInstance().getTimeInMillis())
//				.append(".").append(fileFormat);
//
//		InputStream stream;
//		try {
//			stream = multipartFile.getInputStream();
//		} catch (IOException e) {
//			logger.error(e.getMessage());
//			throw new BadRequestException(e.getMessage());
//		}
//		PutObjectResult putObjectResult = amazonS3Service.putObject(bucketName, key.toString(), stream, objectMetadata);
//		StringBuilder returnUrl = new StringBuilder().append("http://").append(bucketName).append(".")
//				.append(endpointS3).append("/").append(key);
//		return returnUrl.toString();
//	}
}
