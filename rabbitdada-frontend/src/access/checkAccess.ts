import AccessEnum from "@/access/accessEnum";

/**
 * 检查权限
 * @param loginUser
 * @param needAccess
 */
const checkAccess = (
  loginUser: API.LoginUserVO,
  needAccess = AccessEnum.NOT_LOGIN
) => {
  // 获取当前登录用户具有的权限（如果没有 loginUser，则表示未登录）
  const loginUserAccess = loginUser?.userRole ?? AccessEnum.NOT_LOGIN;
  if (needAccess === AccessEnum.NOT_LOGIN) {
    return true;
  }
  // 如果用户要登录才能访问
  if (needAccess === AccessEnum.USER) {
    // 如果用户没登录，那么表示无权限
    if (loginUserAccess === AccessEnum.NOT_LOGIN) {
      return false;
    }
  }
  // 如果管理员才能访问
  if (needAccess === AccessEnum.ADMIN) {
    // 如果不是管理员，表示无权限
    if (loginUserAccess !== AccessEnum.ADMIN) {
      return false;
    }
  }
  return true;
};

export default checkAccess;
