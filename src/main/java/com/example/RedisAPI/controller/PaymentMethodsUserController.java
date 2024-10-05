package com.example.RedisAPI.controller;

import com.example.RedisAPI.dto.PaymentMethodResponseDto;
import com.example.RedisAPI.model.PaymentMethod;
import com.example.RedisAPI.service.PaymentMethodsUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/payment-methods")
@AllArgsConstructor
public class PaymentMethodsUserController {

    private final PaymentMethodsUserService paymentMethodsUserService;

    @GetMapping
    public ResponseEntity<List<PaymentMethodResponseDto>> getPaymentMethods(@PathVariable Long userId) {
        List<PaymentMethodResponseDto> paymentMethods = paymentMethodsUserService.getPaymentMethodsToUserById(userId);
        return new ResponseEntity<>(paymentMethods, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addPaymentMethod(@PathVariable Long userId, @RequestBody PaymentMethod paymentMethod) {
        paymentMethodsUserService.addPaymentMethods(userId, paymentMethod);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
