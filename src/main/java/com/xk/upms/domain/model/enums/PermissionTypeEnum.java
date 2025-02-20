package com.xk.upms.domain.model.enums;

/**
 * PermissionTypeEnum 類型(1:目錄,2:菜單,3:按鈕)
 * Created by hank on 2025/02/18
 * @author hank
 */
public enum PermissionTypeEnum {
	API("API"),//目錄
	BUTTON("BUTTON"),//按鈕
	MENU("MENU");//菜單
	
	private final String permissionType;
	
	PermissionTypeEnum(String permissionType){
		this.permissionType = permissionType;
	}
	
	public String getPermissionType() {
		return permissionType;
	}
	
	public static PermissionTypeEnum fromString(String value) {
		for(PermissionTypeEnum action :PermissionTypeEnum.values()) {
			if(action.name().equalsIgnoreCase(value)) {
				return action;
			}
		}
		throw new IllegalArgumentException("Invalid PermissionTypeEnum action: " + value);
	}
	
	  /**
     * Get the number of PermissionTypeEnum enums.
     * @return the size of PermissionTypeEnum values
     */
    public static int size() {
        return PermissionTypeEnum.values().length;
    }
	
}
