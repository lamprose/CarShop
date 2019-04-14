const app = {
  state: {
    refreshCart:false,
    tempRoles:''
  },

  mutations: {
    SET_TEMP_ROLES:(state,roles)=>{
      state.tempRoles=roles
    },
    CHANGE_REFRESH_CART:(state)=>{
      state.refreshCart=!state.refreshCart
    },
  },

  actions: {
    SetTempRoles({commit},roles){
      return new Promise((resolve, reject) => {
        commit("SET_TEMP_ROLES",roles)
        resolve()
      })
    },
    ClearTempRoles({commit}){
      return new Promise((resolve, reject) => {
        commit("SET_TEMP_ROLES",'')
        resolve()
      })
    },
    ChangeRefreshCart({commit}){
      return new Promise((resolve, reject) => {
        commit("CHANGE_REFRESH_CART")
        resolve()
      })
    }
  }
}

export default app
