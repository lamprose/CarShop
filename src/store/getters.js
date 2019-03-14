const getters = {
  user:state => state.user,
  id: state=> state.user.id,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  introduction: state => state.user.introduction,
  status: state => state.user.status,
  roles: state => state.user.roles,
  addRouters: state => state.permission.addRouters,
  keepId: state => state.app.keepId,
  keepPassword: state => state.app.keepPassword,
}
export default getters
