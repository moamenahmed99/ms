package com.moamenprojects.cards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moamenprojects.cards.constants.CardsConstants;
import com.moamenprojects.cards.dto.ResponseDto;
import com.moamenprojects.cards.service.ICardsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class CardsController {

    private ICardsService iCardsService;

    @PostMapping("/create")   
    ResponseEntity<ResponseDto> createCard(@Valid @RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                      String mobileNumber){
        iCardsService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }
    

}
