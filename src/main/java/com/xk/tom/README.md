📌 完整 DDD 專案架構 (建議)
arduino
Copy
Edit
com.xk.tom
┣ controller                // Interface 層 (API/UI)
┃   ┣ api
┃   ┃   ┣ OrderRestController.java
┃   ┃   ┣ ImportOrderRestController.java
┃   ┃   ┗ ExportOrderRestController.java
┃   ┗ web                   // 如果有 Web UI, GraphQL, gRPC
│
┣ application               // Application 層 (用例協調, DTO, Cmd, Query)
┃   ┣ usecase
┃   ┃   ┣ ImportOrderFindUseCase.java
┃   ┃   ┣ ImportOrderManageUseCase.java
┃   ┃   ┣ ExportOrderFindUseCase.java
┃   ┃   ┗ ExportOrderManageUseCase.java
┃   ┣ dto
┃   ┃   ┣ ImportOrderRequestDto.java
┃   ┃   ┣ ImportOrderResponseDto.java
┃   ┃   ┣ ExportOrderRequestDto.java
┃   ┃   ┗ ExportOrderResponseDto.java
┃   ┣ model
┃   ┃   ┣ ImportOrderCmd.java
┃   ┃   ┣ SaveImportOrderCmd.java
┃   ┃   ┗ ImportOrderQueryDto.java
┃   ┗ mapper
┃       ┣ ImportOrderMapper.java       // Dto ↔ Entity
┃       ┗ ExportOrderMapper.java
│
┣ domain                    // Domain 層 (業務邏輯核心)
┃   ┣ model
┃   ┃   ┣ entity
┃   ┃   ┃   ┣ ImportOrderEntity.java   // 聚合根 (狀態邏輯)
┃   ┃   ┃   ┗ ExportOrderEntity.java
┃   ┃   ┣ vo
┃   ┃   ┃   ┣ ContainerNumber.java     // 值物件
┃   ┃   ┃   ┗ DeliveryLocation.java
┃   ┃   ┣ bo
┃   ┃   ┃   ┣ ImportOrderBo.java
┃   ┃   ┃   ┗ ExportOrderBo.java
┃   ┃   ┗ enums
┃   ┃       ┣ OrderStatus.java
┃   ┃       ┗ OrderType.java
┃   ┣ service
┃   ┃   ┣ OrderService.java
┃   ┃   ┣ ImportOrderService.java
┃   ┃   ┗ ExportOrderService.java
┃   ┗ repository
┃       ┣ OrderRepository.java         // Domain interface → 操作 Entity
┃       ┣ ImportOrderRepository.java
┃       ┗ ExportOrderRepository.java
│
┣ infrastructure             // 基礎設施層 (技術實作)
┃   ┣ persistence
┃   ┃   ┣ po
┃   ┃   ┃   ┣ OrderPo.java
┃   ┃   ┃   ┣ ImportOrderPo.java
┃   ┃   ┃   ┗ ExportOrderPo.java
┃   ┃   ┣ repository
┃   ┃   ┃   ┣ SpringDataImportOrderJpaRepository.java
┃   ┃   ┃   ┣ SpringDataExportOrderJpaRepository.java
┃   ┃   ┃   ┗ OrderRepositoryImpl.java // Entity ↔ Po 轉換
┃   ┃   ┗ mapper
┃   ┃       ┣ ImportOrderPersistenceMapper.java  // Entity ↔ Po
┃   ┃       ┗ ExportOrderPersistenceMapper.java
┃   ┣ config                // Spring Bean Config, DB, Security
┃   ┗ external              // 其他外部系統整合 (API, MQ, File Storage)
│
┗ common                    // 共用模組 (跨 domain)
┣ base                  // BaseEntity, BaseDto...
┣ util                  // XkBeanUtils, DateUtils...
┗ exception             // 全域例外
📌 說明
controller (介面層 / UI 層)

提供 API (REST/GraphQL/gRPC)

只呼叫 Application UseCase，不直接用 Domain

application (應用層)

用例（UseCase）：定義系統要做什麼

DTO / Cmd / Query：資料交換物件

Mapper：Dto ↔ Entity 的轉換

domain (領域層，核心)

Entity：聚合根（業務狀態更新邏輯放這裡）

VO：不可變值物件（例如 ContainerNumber, Location）

BO：封裝商業計算結果

Service：處理跨 Entity 的業務規則

Repository：介面定義（操作 Entity，不依賴技術）

infrastructure (基礎設施層)

PO：資料表映射物件（純粹持久化，無邏輯）

JPA Repository：Spring Data JPA 的 DAO

RepositoryImpl：Adapter，把 Entity ↔ Po 轉換

Mapper：Persistence Mapper，負責 DB ↔ Entity 的轉換

Config / External：Spring Bean 配置、外部 API、MQ 整合

common (共用)

放通用工具、基底類別、例外處理

✅ 總結
完整的 DDD 專案架構至少包含 4 大層：

controller (UI)

application (用例層)

domain (業務邏輯核心)

infrastructure (基礎設施)

並且要把 Entity 與 PO 拆開，Repository 面向 Entity，PO 只存在於 Infrastructure。