<template>
    <el-card class="ResultCard-container">
      <div class="picture">
          <el-image
            :src="'/api/picture/file/' + item.masterPicture"
            style="width: 100%; height: 300px"
            fit="fill"
          ></el-image>
      </div>
      <div class="fieldAll">
          <Field name="名称" :value="item.name"></Field>
          <Field name="价格" :value="item.price"></Field>
          <Field name="邮费" :value="item.postalFee"></Field>
          <Field name="库存" :value="item.count"></Field>
          <Field name="销量" :value="item.saleCount"></Field>
      </div>
      <el-row>
          <el-button type="text" @click="getDetail">详情</el-button>
      </el-row>
    </el-card>
</template>

<script>
import Field from './Field.vue'
export default {
  props: ['item'],
  components: {
    Field
  },
  methods: {
    getDetail(){
      
    }
  }
}
</script>

<style>
  .ResultCard-container {
    /*background-color: rgba(191, 191, 191, 0.2);*/
    background-color: #EDEDED;
    display: inline-block;
    position: relative;
    padding: 0;
    margin: 20px;
    border-radius:5%;
  }
  .picture img{
    border: 1px dashed #ccc;
  }
  .fieldAll {
    margin: 5px auto;
  }
  .cardCheckBox{
    margin-bottom: 10px;
  }
</style>
