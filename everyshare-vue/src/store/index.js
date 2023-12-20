import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    username : null,
    role : null
  },
  getters: {
  },
  mutations: {
    handleCurrentUser(state){
      axios.get("/api/user/current").then((res) => {
        console.log(res)
        if(res.data.data === null) {
          state.username = null
          state.role = null
        } else {
          state.username = res.data.data.name
          state.role = res.data.data.role
        }
      })
    }
  },
  actions: {
  },
  modules: {
  }
})
