package com.ufril.medtran.resource.v1;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "PaymentResourceV1")
@RequestMapping(value = { "/v1/", "/oauth2/v1/" })
@Api(value = "Payment")
public class PaymentResource {
    private static Logger logger = Logger.getLogger(FuelPurchaseLogResource.class);

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Value("${stripe.checkout.successUrl}")
    private String successUrl;

    @Value("${stripe.checkout.cancelUrl}")
    private String cancelUrl;

    @RequestMapping(value = "/payment/create-checkout-session", method = RequestMethod.POST)
    public String createCheckoutSession() throws StripeException {
        Stripe.apiKey = stripeApiKey;

        ProductCreateParams product_params = ProductCreateParams.builder().setName("Monthly Payment").build();
        Product product = Product.create(product_params);

        PriceCreateParams price_params =
                PriceCreateParams
                        .builder()
                        .setProduct(product.getId())
                        .setUnitAmount(100L)
                        .setCurrency("usd")
                        .build();
        Price price = Price.create(price_params);

        SessionCreateParams params =
                SessionCreateParams
                        .builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .addLineItem(
                                SessionCreateParams.LineItem
                                        .builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setSuccessUrl(successUrl)
                        .setCancelUrl(cancelUrl)
                        .build();

        Session session = Session.create(params);
        return session.getUrl();
    }
}
