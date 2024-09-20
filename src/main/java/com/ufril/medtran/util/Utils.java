package com.ufril.medtran.util;

//import com.google.maps.api.GoogleMapApiClient;
//import com.google.maps.distance.DistResult;
import com.ufril.medtran.dto.common.Message;
import com.ufril.medtran.exception.BadRequestException;
import com.ufril.medtran.helper.MessageHelper;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.Random;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @author moin
 */
@Component
public class Utils {
    private static final Logger logger = Logger.getLogger(Utils.class);

 //  private static final String UPLOAD_DIR = "C:/users/uploads/";
   //private static final String UPLOAD_DIR = System.getProperty("user.home")+ "/uploads/";


    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUM = "0123456789";
    private static final String ALPHA_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA_NUM_SPECIAL = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+";

    public static Pageable buildPageSpecification(Integer page, Integer size) {
        //return new PageRequest(pageIndex, pageSize, sortByLastNameAndFirstNameDesc());
        if ((page == null)) {
            page = 0;
        } else if (page >= 1) {
            page = page - 1;
        } else {
            page = 0;
        }

        if (size == null) {
            size = 10;
        } else if (size < 1) {
            size = 10;
        }
        return new PageRequest(page, size);
    }

    public static Message buildMessage(MessageSource messageSource, int code, String key, Locale locale) {
        Message message = new Message();
        message.setCode(code);
        message.setMessage(messageSource.getMessage(key, null, locale));
        return message;
    }


    /**
     * Get an AlphaNumeric String of length 'len'
     *
     * @param len
     * @return
     */
    public static String getAlphaNumeric(final int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int ndx = (int) (Math.random() * ALPHA_NUM.length());
            sb.append(ALPHA_NUM.charAt(ndx));
        }
        return sb.toString();
    }

    public static String getAlpha(final int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int ndx = (int) (Math.random() * ALPHA.length());
            sb.append(ALPHA.charAt(ndx));
        }
        return sb.toString();
    }

    public static String getNumeric(final int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int ndx = (int) (Math.random() * NUM.length());
            sb.append(NUM.charAt(ndx));
        }
        return sb.toString();
    }

    /**
     * Get an AlphaNumeric Special character String of length 'len'
     *
     * @param len
     * @return
     */
    public static String getRandomValue(final int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int ndx = (int) (Math.random() * ALPHA_NUM_SPECIAL.length());
            sb.append(ALPHA_NUM_SPECIAL.charAt(ndx));
        }
        return sb.toString();
    }

    /**
     * return a random number which can me 'min' or 'max' or in between any value
     * @param min
     * @param max
     * @return
     */

    public static int getRandomNumber(final int min, final int max) {
        Random ran = new Random();
        return ran.nextInt(max) + min;
    }

    public static double getMileFromKilometer(double kilometer) {
        return (kilometer * 0.62137);
    }

    public static double roundOff2(Double value) {
        //return Math.round(value * 100.0) / 100.0;
        //return new BigDecimal(value).round(new MathContext(5)).doubleValue();
        return new BigDecimal(value.toString()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static String getBaseUrl() {
        String baseUrl;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        // System.out.println(baseUrl);
        return baseUrl;
    }

    
    public static String getImageUrlGet(String imageUrl) {
//    	if (StringUtils.hasText(imageUrl)) {
//            imageUrl = Utils.getBaseUrl() + imageUrl;
//        }
        return imageUrl;
    }

    public static String getImageUrlSet(String imageUrl) {
//    	if (StringUtils.hasText(imageUrl)) {
//            imageUrl = imageUrl.replaceAll(Utils.getBaseUrl(), "");
//        }
    	return imageUrl;
    }

    private String getAppUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
    }


    public static String getFilePath() {
        // spring-boot app is running form jar, so create a folder manually named files
        String root = System.getProperty("user.home");

        String path = Paths.get(root, "files").toString();
        File f = new File(path);
        if (!f.exists()) { f.mkdir(); }

//        String fpath = "file:"+ root + File.separator + "tmpFiles/";
//        System.out.println(fpath);

        return f.getAbsolutePath();
    }

    public static String getRelativeFilePath(String name) {
        return "/files/" + name;
    }


   // private static final String UPLOAD_DIR = "C:/uploads/";
 /*   public static String saveFile(MultipartFile file) throws IOException {
        logger.debug("Uploading file: " + file.getOriginalFilename());
        if (file != null && !file.isEmpty()) {
            Path uploadPath = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
           Path uploadDirPath = Paths.get(UPLOAD_DIR);
         //  Path uploadPath = Paths.get(Utils.getFilePath() + file.getOriginalFilename());
          //  Path uploadDirPath = Paths.get(Utils.getFilePath());
            if (!Files.exists(uploadDirPath)) {
                Files.createDirectories(uploadDirPath);  // this will create the directory if it doesn't exist
            }
            Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
            return uploadPath.toString();
        }
        return null;
            }*/
    public static Path getFile(String filePath) {
        return Paths.get(filePath);
    }


    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }



    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }
}
