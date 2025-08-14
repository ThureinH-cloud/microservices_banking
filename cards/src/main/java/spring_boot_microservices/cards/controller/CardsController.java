package spring_boot_microservices.cards.controller;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_boot_microservices.cards.constants.CardsConstants;
import spring_boot_microservices.cards.dto.CardsContactInfoDto;
import spring_boot_microservices.cards.dto.CardsDto;
import spring_boot_microservices.cards.dto.ResponseDto;
import spring_boot_microservices.cards.service.ICardService;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class CardsController {
    private ICardService cardsService;
    private CardsContactInfoDto cardsContactInfoDto;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam String mobileNumber) {
        cardsService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCard(@RequestParam String mobileNumber) {
        CardsDto cardsDto= cardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam String mobileNumber) {
        boolean deleteCard = cardsService.deleteCard(mobileNumber);
        if (deleteCard) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }
    }
    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDto> fetchContactInfo() {
        return  ResponseEntity.status(HttpStatus.OK).body(cardsContactInfoDto);
    }
}
