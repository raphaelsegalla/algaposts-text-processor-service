package com.algaworks.algaposts.text.processor.service.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TextProcessorData {

    private String postId;
    private String postBody;

}
