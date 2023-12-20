<template>


    <el-dialog
        title="登录界面"
        :visible="true"
        width="30%"
        :show-close="false"
        center>
      <el-form :model="ruleForm" status-icon ref="ruleForm" label-width="100px">
        <el-form-item label="用户名" prop="username">
          <el-input type="text" v-model="ruleForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="$router.push('/')">返回</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>



</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      ruleForm: {
        username: '',
        password: ''
      }
    }
  },
  methods:{
    submitForm(){
      let data = new FormData();
      data.append('username',this.ruleForm.username);
      data.append('password',this.ruleForm.password);
      this.$http.post("/api/user/login",data).then(res=>{
        console.log(res)
        this.$store.commit("handleCurrentUser")
        this.$router.push("/")
      })
    }

  }

}
</script>

<style scoped>


.el-dialog{
  background: #fff !important;
}
::v-deep .el-dialog--center{
  margin-top: 25vh !important;
  box-shadow: 10px 10px 30px;
  opacity: 0.8;


}


</style>
