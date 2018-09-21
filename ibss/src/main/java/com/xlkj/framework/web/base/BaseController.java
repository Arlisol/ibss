package com.xlkj.framework.web.base;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xlkj.project.domain.User;

/**
 * 这是一个controller的积累，方便以后扩展
 * @author debao.wang
 *
 */
public class BaseController {
	/**
	 * 添加操作成功的提示消息
	 */
	protected static final String ADD_SUCCEED = "添加成功！";
	/**
	 * 添加提交成功的提示消息
	 */
	protected static final String REFER_SUCCEED = "提交成功！";
	/**
	 * 添加操作失败的提示消息
	 */
	protected static final String ADD_FAIL = "添加失败！";
	/**
	 * 删除操作成功的提示消息
	 */
	protected static final String DELETE_SUCCEED = "删除成功！";
	/**
	 * 删除操作失败的提示消息
	 */
	protected static final String DELETE_FAIL = "删除失败！";
	/**
	 * 删除操作成功的提示消息
	 */
	protected static final String SUBMIT_SUCCEED = "提交成功！";
	/**
	 * 删除操作失败的提示消息
	 */
	protected static final String SUBMIT_FAIL = "提交失败！";
	/**
	 * 编辑操作成功的提示消息
	 */
	protected static final String EDIT_SUCCEED = "编辑成功！";
	/**
	 * 编辑操作失败的提示消息
	 */
	protected static final String EDIT_FAIL = "编辑失败！";
	/**
	 * 修改操作成功的提示消息
	 */
	protected static final String MODIFY_SUCCEED = "修改成功！";
	/**
	 * 修改操作失败的提示消息
	 */
	protected static final String MODIFY_FAIL = "修改失败！";
	/**
	 * 导入操作成功的提示消息
	 */
	protected static final String IMPORT_SUCCEED = "导入成功！";
	/**
	 * 导入操作失败的提示消息
	 */
	protected static final String IMPORT_FAIL = "导入失败！";
	/**
	 * 导出操作成功的提示消息
	 */
	protected static final String EXPORT_SUCCEED = "导出成功！";
	/**
	 * 导出操作失败的提示消息
	 */
	protected static final String EXPORT_FAIL = "导出失败！";
	/**
	 * 保存操作成功的提示消息
	 */
	protected static final String SAVE_SUCCEED = "保存成功！";
	/**
	 * 保存操作失败的提示消息
	 */
	protected static final String SAVE_FAIL = "保存失败！";
	/**
	 * 设置操作成功的提示消息
	 */
	protected static final String SET_SUCCEED = "设置成功！";
	/**
	 * 设置操作失败的提示消息
	 */
	protected static final String SAET_FAIL = "设置失败！";
	/**
	 * 取消操作成功的提示消息
	 */
	protected static final String CALCEL_SUCCEED = "取消成功！";
	/**
	 * 取消操作失败的提示消息
	 */
	protected static final String CANCEL_FAIL = "取消失败！";
	/**
	 * 撤销操作成功的提示消息
	 */
	protected static final String REVOKE_SUCCEED = "撤销成功！";
	/**
	 * 取消操作失败的提示消息
	 */
	protected static final String REVOKE_FAIL = "撤销失败！";
	/**
	 * 上传操作成功的提示消息
	 */
	protected static final String UPLOAD_SUCCEED = "上传成功！";
	/**
	 * 上传操作失败的提示消息
	 */
	protected static final String UPLOAD_FAIL = "上传失败！";
	/**
	 * 审核操作成功的提示消息
	 */
	protected static final String AUDIT_SUCCEED = "审核成功！";
	/**
	 * 审核操作失败的提示消息
	 */
	protected static final String AUDIT_FAIL = "审核失败！";
	/**
	 * 核算操作成功的提示消息
	 */
	protected static final String CHECK_SUCCEED = "核算成功！";
	/**
	 * 核算操作失败的提示消息
	 */
	protected static final String CHECK_FAIL = "核算失败！";
	/**
	 * 确认操作成功的提示消息
	 */
	protected static final String SURE_SUCCEED = "确认成功！";
	/**
	 * 确认操作失败的提示消息
	 */
	protected static final String SURE_FAIL = "确认失败！";
	/**
	 * 分配操作成功的提示消息
	 */
	protected static final String ALLOCATE_SUCCEED = "分配成功！";
	/**
	 * 分配操作失败的提示消息
	 */
	protected static final String ALLOCATE_FAIL = "分配失败！";
	/**
	 * 领用操作成功的提示消息
	 */
	protected static final String GET_SUCCEED = "领用成功！";
	/**
	 * 领用操作失败的提示消息
	 */
	protected static final String GET_FAIL = "领用失败！";
	/**
	 * 注册操作成功的提示消息
	 */
	protected static final String REGISTER_SUCCEED = "注册成功！";
	/**
	 * 注册操作失败的提示消息
	 */
	protected static final String REGISTER_FAIL = "注册失败！";
	/**
	 * 申请操作成功的提示消息
	 */
	protected static final String APPLICATION_SUCCEED = "申请成功！";
	/**
	 * 申请操作失败的提示消息
	 */
	protected static final String APPLICATION_FAIL = "申请失败！";
	/**
	 * 充值操作成功的提示消息
	 */
	protected static final String RECHARGE_SUCCEED = "充值成功！";
	/**
	 * 充值操作失败的提示消息
	 */
	protected static final String RECHARGE_FAIL = "充值失败！";
	
	/**
	 * 获取当前用户
	 * @param session
	 * @return
	 */
	protected User getCurrentUser(HttpSession session) {
		User user = (User)session.getAttribute("user");
		return user;
	}
	
	/**
	 * 设置操作完成的结果
	 * @param model
	 * @param status	成功：true；失败：false
	 * @param message	结果信息
	 */
	public void setResult(Model model, boolean status, String message) {
		model.addAttribute("status", status);
		model.addAttribute("message", message);
	}
	
	/**
	 * 设置操作完成的重定向结果
	 * @param model
	 * @param status	成功：true；失败：false
	 * @param message	结果信息
	 */
	public void setRedirectResult(RedirectAttributes redirectAttributes, boolean status, String message) {
		redirectAttributes.addAttribute("status", status);
		redirectAttributes.addAttribute("message", message);
	}
}
