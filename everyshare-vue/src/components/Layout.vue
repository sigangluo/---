<template>
  <el-container>
    <el-header>
      <el-menu mode="horizontal" :default-active="'1'">
        <el-menu-item index="1"><router-link to="/">首页</router-link></el-menu-item>
        <el-menu-item index="2"><router-link to="/">购物车</router-link></el-menu-item>
        <div class="right" v-if="this.$store.state.username === null">
          <el-button @click="$router.push('/login')">登录</el-button>
          <el-button @click="$router.push('/')">注册</el-button>
        </div>
        <div class="right" v-else>
          <span>{{this.$store.state.username}}</span>
          <span>{{this.$store.state.role}}</span>
          <el-button @click="handleLogout">退出登录</el-button>
        </div>
      </el-menu>
      
    </el-header>


    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: "Layout",
  data() {
    return {
      username: null,
      role: null
    };
  },
  computed() {
  },
  mounted() {
    this.$store.commit("handleCurrentUser")
  },
  methods: {
    handleLogout(){
      this.$http.get("/api/user/logout").then(res=>{
        this.$store.commit("handleCurrentUser")
      })
    }
  }
};
</script>

<style scoped>
* {
  color: black;
  margin: 0;
  padding: 0;
}
body {
  background: #e9eef3;
}

.el-main {
  padding: 0;
}

.el-header {
  background: #409eff;
  padding: 0;
  color: black;
}

.el-menu-item:hover {
  outline-style: none;
}
.pd0 {
  width: 200px;
  padding: 0;
  margin: 0;
}
a {
  text-decoration: none;
  color: #000;
  line-height: 60px;
}
/*设置点击后的样式 */
.router-link-active {
  text-decoration: none;
  color: red;
}
.el-menu--horizontal > .el-menu-item {
  color: black;
}
.right{
  float: right;
  margin-right: 5px;
}
</style>
