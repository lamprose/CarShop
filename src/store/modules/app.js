const app = {
  state: {
    keepId:'',
    keepPassword:''
  },

  mutations: {
    SET_KEEPUSER:(state,keepUser)=>{
      state.keepId = keepUser.id
      state.keepPassword = keepUser.password
    },
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
  }
}

export default app
