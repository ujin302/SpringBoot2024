package com.example.tosstest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
    private IamportClient iamportClient;
    private String apiKey = "6223325658865157";
    private String secretKey = "Xeuj7dJCkaCSLIDuwASTrxzY1kgShP3nP5HctKgn0SD3vqYdD8h0XqdOcq2LW1mGvz1FprFCpFcHesNh";
 
    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, secretKey);
    }

    @PostMapping("/order/payment")
    public ResponseEntity<String> paymentComplete(@Login SessionUser sessionUser, @RequestBody List<OrderSaveDto> orderSaveDtos) throws IOException {
        String orderNumber = String.valueOf(orderSaveDtos.get(0).getOrderNumber());
        try {
            Long userId = sessionUser.getUserIdNo();
            paymentService.saveOrder(userId, orderSaveDtos);
            log.info("결제 성공 : 주문 번호 {}", orderNumber);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            log.info("주문 상품 환불 진행 : 주문 번호 {}", orderNumber);
            String token = refundService.getToken(apiKey, secretKey);
            refundService.refundWithToken(token, orderNumber, e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
 
 
    @PostMapping("/payment/validation/{imp_uid}")
    @ResponseBody
    public IamportResponse<Payment> validateIamport(@PathVariable String imp_uid) {
        IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
        log.info("결제 요청 응답. 결제 내역 - 주문 번호: {}", payment.getResponse().getMerchantUid());
        return payment;
    }
}
