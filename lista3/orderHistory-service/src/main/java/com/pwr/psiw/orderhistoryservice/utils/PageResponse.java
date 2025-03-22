package com.pwr.psiw.orderhistoryservice.utils;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Stronicowana odpowiedź zawierająca listę elementów oraz linki HATEOAS.")
public record PageResponse<T>(
        @Schema(description = "Aktualna strona", example = "1")
        int currentPage,

        @Schema(description = "Liczba elementów na stronie", example = "10")
        int pageSize,

        @Schema(description = "Całkowita liczba stron", example = "5")
        int totalPages,

        @Schema(description = "Lista obiektów na danej stronie")
        List<T> content,

        @Schema(description = "Czy jest to ostatnia strona", example = "false")
        boolean last,

        @Schema(description = "Czy istnieje kolejna strona", example = "true")
        boolean hasNext
) {}
