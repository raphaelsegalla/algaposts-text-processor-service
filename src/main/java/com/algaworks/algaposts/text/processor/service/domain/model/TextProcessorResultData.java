package com.algaworks.algaposts.text.processor.service.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TextProcessorResultData {

    private String postId;
    private Long wordCount;
    private BigDecimal calculatedValue;

}
