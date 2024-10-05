package com.example.RedisAPI.service;

import com.example.RedisAPI.dto.PaymentMethodResponseDto;
import com.example.RedisAPI.model.PaymentMethod;
import com.example.RedisAPI.model.User;
import com.example.RedisAPI.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PaymentMethodsUserService {
    private final UserRepository userRepository;

    @Cacheable(value = "user_payment_methods", key = "#userId")
    public List<PaymentMethodResponseDto> getPaymentMethodsToUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not exists"));

        if (user.getPaymentMethods().isEmpty()) {
            throw new IllegalArgumentException("User does not have any payment method");
        }

        return user.getPaymentMethods().stream()
                .map(pm -> new PaymentMethodResponseDto(pm.getType(), pm.getDescription()))
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "user_payment_methods", key = "#userId")
    public void addPaymentMethods(Long userId, PaymentMethod paymentMethod) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not exists"));

        user.addPaymentMethod(paymentMethod);
        userRepository.save(user);
    }

    private List<PaymentMethodResponseDto> defaultMethods() {
        return List.of(new PaymentMethodResponseDto("Cash", "Money"));
    }
}


