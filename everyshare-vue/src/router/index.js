import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/goods/Index')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login/Index')
  },
]

const router = new VueRouter({
  routes
})

export default router
