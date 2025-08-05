package com.xk.exapmleFolder.application.model;

import lombok.Data;

// ✅ 使用者列表查詢 Request
@Data
public class UserListQueryRequest {
    private String keyword;
}