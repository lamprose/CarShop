const app = {
  state: {
    keepId:'',
    keepPassword:'',
    tempRoles:''
  },

  mutations: {
    SET_KEEPUSER:(state,keepUser)=>{
      state.keepId = keepUser.id
      state.keepPassword = keepUser.password
    },
    SET_TEMP_ROLES:(state,roles)=>{
      state.tempRoles=roles
    }
  },

  actions: {
    KeepUser({ commit }, keepUser) {
      const id = keepUser.id.trim()
      return new Promise((resolve, reject) => {
        commit("SET_KEEPUSER",{id:id,password:keepUser.password})
      })
    },
    ClearUser({ commit }) {
      return new Promise((resolve, reject) => {
        commit("SET_KEEPUSER",{id:'',password:''})
      })
    },
    SetTempRoles({commit},roles){
      return new Promise((resolve, reject) => {
        commit("SET_TEMP_ROLES",roles)
      })
    },
    ClearTempRoles({commit}){
      return new Promise((resolve, reject) => {
        commit("SET_TEMP_ROLES",'')
      })
    }
  }
}

export default app
