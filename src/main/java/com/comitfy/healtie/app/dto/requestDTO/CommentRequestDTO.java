package com.comitfy.healtie.app.dto.requestDTO;

import com.comitfy.healtie.util.common.BaseDTO;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class CommentRequestDTO extends BaseDTO {

    private String content;

    private UUID parenUuid;


}
