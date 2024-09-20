package com.ufril.medtran.web;

import com.google.gson.Gson;
import com.ufril.medtran.util.EncodingUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author moin
 */
@Controller
//@RequestMapping(value = "web/payment")
public class PaymentController {

    private static Logger logger = Logger.getLogger(PaymentController.class);

    // TODO the purpose of this controller is to handle paypal success payment post
    // TODO data and save necessary info after successful payment.
    // We will work on this later when we have enough time

    @RequestMapping(value = "/rsp-success", method = RequestMethod.POST)
    public void subscribeWebPlatform(HttpServletRequest request, HttpServletResponse response,
            @RequestParam Map<String, String> allRequestParams) {

        try {
            logger.debug(" Inside subscribeWebPlatform ");
            logger.debug(" Query String : " + request.getQueryString());

//        var post_data = req.body;
            // var ref = req.query.ref;
            String ref = allRequestParams.get("ref");
            logger.debug("Ref : "+ref);

            //console.log(ref);

//        post_data.subscribeType = req.query.subscribeType;

            //post_data.serviceType = req.query.serviceType;
//        post_data.referrer = encodeURIComponent(req.query.referrer);
            String referrer = EncodingUtil.encodeURIComponent(allRequestParams.get("referrer"));
            allRequestParams.put("referrer", referrer);
//        console.log(post_data);

//        var response = JSON.stringify(post_data);
            Gson gson = new Gson();
            String json = gson.toJson(allRequestParams);
            logger.debug("json data : "+ json);
            String retUrl = EncodingUtil.encodeURIComponent(ref) + "?response=" + json;
            response.sendRedirect(retUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
