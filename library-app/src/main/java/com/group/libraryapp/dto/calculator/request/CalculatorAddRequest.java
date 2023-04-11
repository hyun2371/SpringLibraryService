package com.group.libraryapp.dto.calculator.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class CalculatorAddRequest {
    private final int number1;
    private final int number2;

}
