package com.xk.adm.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xk.adm.application.dto.NotificationDTO;
import com.xk.common.base.BaseResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `AdmNotificationController` - 負責管理 **系統通知 API**
 * 
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 * 
 * @author yuan Created on 2025/02/25.
 */
@Tag(name = "Notification Management", description = "系統通知管理")
@RestController
@RequestMapping("/api/adm/notification")
@RequiredArgsConstructor
@Slf4j
public class AdmNotificationController {

//    private final AdmNotificationUseCase admNotificationUseCase;

    @Operation(summary = "獲取所有通知", description = "查詢所有系統通知")
    @GetMapping("/all")
    public BaseResult<List<NotificationDTO>> getAllNotifications() {
//        return BaseResult.success(admNotificationUseCase.getAllNotifications(), "成功");
        return null;
    }

    @Operation(summary = "根據類型獲取通知", description = "查詢特定類型的通知")
    @GetMapping("/{type}")
    public BaseResult<List<NotificationDTO>> getNotificationsByType(@PathVariable String type) {
//        return BaseResult.success(admNotificationUseCase.getNotificationsByType(type), "成功");
        return null;
    }

    @Operation(summary = "新增通知", description = "新增一條系統通知")
    @PostMapping("/create")
    public BaseResult<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO) {
//        return BaseResult.success(admNotificationUseCase.createNotification(notificationDTO), "成功");
        return null;
    }

    @Operation(summary = "更新通知", description = "更新現有的系統通知")
    @PutMapping("/update")
    public BaseResult<NotificationDTO> updateNotification(@RequestBody NotificationDTO notificationDTO) {
//        return BaseResult.success(admNotificationUseCase.updateNotification(notificationDTO), "成功");
        return null;
    }

    @Operation(summary = "刪除通知", description = "根據 ID 刪除通知")
    @DeleteMapping("/delete/{id}")
    public BaseResult<Boolean> deleteNotification(@PathVariable String id) {
//        return BaseResult.success(admNotificationUseCase.deleteNotification(id), "成功");
        return null;
    }
}