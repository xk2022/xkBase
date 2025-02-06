package com.xk.upms.controller.api;

import static com.xk.common.base.BaseController.LOGGER;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xk.common.base.BaseController;
import com.xk.upms.domain.model.po.UpmsRolePermission;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `UpmsRoleController` - 負責管理 **使用者權限 API**
 * 
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 * 
 * @author hank Created on 2025/02/04.
 * @author hank Updated on 2025/02/04 
 */
@RestController
@RequestMapping("/admin/upms/manage/role")
@RequiredArgsConstructor // ✅ 使用建構子注入，減少 @Autowired
@Tag(name = "UpmsUser Management", description = "提供 UpmsUser 的管理功能，包括新增、查詢、更新和刪除。")
@Slf4j
public class UpmsRoleController extends BaseController {
	
	
	/**
     * 查詢 角色 首頁
     */
	@GetMapping()
	public String index(Model model) {
		return null;
	}
	
	 @GetMapping("/{id}")
	    public String list(@PathVariable Long id, Model model) {
//	        this.info(model, this.getClass().getAnnotation(RequestMapping.class).value()[0]+"/detail");
//	        model.addAttribute("fragmentName", "detail");
//
//	        LOGGER.info("正在取得角色 ID 的資訊: {}", id);
//	        model.addAttribute("role", upmsRoleService.findById(id));
//	        LOGGER.info("正在取得角色 ID 的使用者: {}", id);
//	        model.addAttribute("page_list", upmsUserRoleService.getUsers(id));
//
//	        LOGGER.info("正在取得角色 ID 的權限: {}", id);
//	        model.addAttribute("entity", new UpmsRolePermission());
//
//	        model.addAttribute("btn_systems", upmsSystemService.list());
	        return ADMIN_INDEX;
	    }

	    /**
	     * 新增/修改 角色 Create/Update
	     */
	    @PostMapping("/save")
	    public String post( RedirectAttributes attributes) {
//	        UpmsRoleSaveResp result;
//	        if (resources.getId() == null) {
//	            result = upmsRoleService.create(resources);
//	        } else {
//	            result = upmsRoleService.update(resources.getId(), resources);
//	        }
//	        upmsRolePermissionService.checkCountSameWithPermission(result.getId());
//
//	        if (result == null) {
//	            attributes.addFlashAttribute("message", "操作失敗");
//	        } else {
//	            attributes.addFlashAttribute("message", "操作成功");
//	        }
//	        return REDIRECT_ADDR;
	    	return null;
	    }

	    /**
	     * 刪除 角色 Delete
	     */
//	    @RequiresPermissions("upms:role:delete")
	    @GetMapping("/delete/{ids}")
	    public String delete(@PathVariable("ids") String ids, RedirectAttributes attributes) {
//	        upmsRoleService.deleteByPrimaryKeys(ids);
//	        attributes.addFlashAttribute("message", "刪除成功");
//	        return REDIRECT_ADDR;
	    	return null;
	    }

	    /**
	     * 角色權限
	     */
//	    @RequiresPermissions("upms:role:permission")
	    @GetMapping("/permission/{id}")
	    public String permission(@PathVariable("id") long id, Model model) {
//	        model.addAttribute("role", upmsRoleService.selectByPrimaryKey(id));
	        return "/manage/role/permission.jsp";
	    }

//	    @RequiresPermissions("upms:role:permission")
	    @PostMapping("/permission/{id}")
	    public Object permission(@PathVariable("id") long id, HttpServletRequest request) {

//	        String[] checkBoxValues = request.getParameterValues("permissions");
//	        String systemCode = request.getParameter("systemCode");
////	        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
//	        upmsRolePermissionService.rolePermission(id, systemCode, checkBoxValues);
////	        return new UpmsResult(UpmsResultConstant.SUCCESS, result);
//	        return REDIRECT_ADDR + "/" + id;
	    	return null;
	    }

}
