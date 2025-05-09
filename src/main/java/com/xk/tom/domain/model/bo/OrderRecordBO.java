package com.xk.tom.domain.model.bo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class OrderRecordBO {

    private Long orderRecordId;
    private Boolean isExport;
    private Boolean isImport;

}
