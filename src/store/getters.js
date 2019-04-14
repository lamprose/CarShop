const getters = {
  user:state => state.user,
  id: state=> state.user.id,
  name: state => state.user.name,
  sex:state => state.user.sex,
  phone:state => state.user.phone,
  loc:state => state.user.loc,
  role: state => state.user.role,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  status: state => state.user.status,
  secretStatus:state => state.user.secretStatus,
  tempRoles: state => state.app.tempRoles,
  refreshCart: state => state.app.refreshCart,
  userSessions:state => state.chat.sessions.filter(session => session.user.name.includes(state.chat.filterKey)),
  nowSession: state => state.chat.sessions.find(session => session.key === state.chat.currentSessionId),
  currentSessionId: state => state.chat.currentSessionId,
  filterKey: state => state.chat.filterKey,
  update:state => state.chat.update
}
export default getters
