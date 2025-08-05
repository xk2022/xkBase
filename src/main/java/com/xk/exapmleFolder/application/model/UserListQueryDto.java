package com.xk.exapmleFolder.application.model;

import java.util.List;

// ✅ 查詢結果 Response / DTO
public class UserListQueryDto {
    private List<UserSummaryDto> users;
    private long totalCount;
}