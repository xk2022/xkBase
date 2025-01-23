package com.xk.exapmleFolder.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.xk.exapmleFolder.application.model.DemoItemDTO;
import com.xk.exapmleFolder.application.model.DemoResponseDTO;
import com.xk.exapmleFolder.domain.model.demo.DemoAggregate;
import com.xk.exapmleFolder.domain.model.demo.DemoPO;

import lombok.experimental.UtilityClass;

/**
 * 📌 OrderMapper（DTO 與 Domain 轉換工具）
 * 
 * - **DTO <-> Domain Model** 轉換
 * - **避免不必要的 `new DemoMapper()`**
 * - **確保 JPA 關聯管理正確**
 * 
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 something note here.
 */
@UtilityClass
public class DemoMapper {
	
    /**
     * 📌 `DemoItemDTO` → `DemoPO`（轉換成 Domain）
     */
    public static DemoPO toDomain(DemoItemDTO dto, DemoAggregate order) {
        return new DemoPO(dto.getProductId(), dto.getQuantity(), dto.getPrice(), order);
    }

    /**
     * 📌 `DemoPO` → `DemoItemDTO`（轉換成 DTO）
     */
    public static DemoItemDTO toDTO(DemoPO domain) {
        return new DemoItemDTO(domain.getProductId(), domain.getQuantity(), domain.getPrice());
    }

    /**
     * 📌 `List<DemoItemDTO>` → `List<DemoPO>`（批量轉換）
     */
    public static List<DemoPO> toDomainList(List<DemoItemDTO> dtoList, DemoAggregate order) {
        return dtoList.stream()
                .map(dto -> toDomain(dto, order)) // ✅ 呼叫 `toDomain()` 避免重複代碼
                .collect(Collectors.toList());
    }

    /**
     * 📌 `List<DemoPO>` → `List<DemoItemDTO>`（批量轉換）
     */
    public static List<DemoItemDTO> toDTOList(List<DemoPO> domainList) {
        return domainList.stream()
                .map(DemoMapper::toDTO) // ✅ 呼叫 `toDTO()` 避免重複代碼
                .collect(Collectors.toList());
    }
    
    /**
     * 📌 `DemoAggregate` → `DemoResponseDTO`（回應 DTO）
     */
    public static DemoResponseDTO toResponseDTO(DemoAggregate order) {
        return DemoResponseDTO.builder()
                .orderId(order.getId())
                .status(order.getStatus().name())
                .items(toDTOList(order.getItems())) // ✅ 呼叫 `toDTOList()`
                .build();
    }
    
}
