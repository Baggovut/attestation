package ru.skypro.socks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.socks.dto.Operation;
import ru.skypro.socks.dto.SocksProperties;
import ru.skypro.socks.service.SocksService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
@Validated
public class SocksController {
    private final SocksService socksService;

    @PostMapping("/income")
    public ResponseEntity<?> socksIncome(@RequestBody @Valid SocksProperties properties) {
        socksService.socksIncome(properties);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/outcome")
    public ResponseEntity<?> socksOutcome(@RequestBody @Valid SocksProperties properties) {
        socksService.socksOutcome(properties);

        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<Integer> getSocksCount(@RequestParam("color") String color, @RequestParam("operation") Operation operation, @RequestParam("cottonPart") @Min(0) @Max(100) Integer cottonPart) {
        int socksCount = socksService.getSocksCount(color, operation, cottonPart);

        return ResponseEntity.ok().body(socksCount);
    }
}
